package ru.rmntim.web.controller;

import ru.rmntim.web.model.Point;
import ru.rmntim.web.model.ValidationResult;
import ru.rmntim.web.ValidationException;
import ru.rmntim.web.model.PointDto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Set;


public class ValidationController {
    private final Validator validator;

    public ValidationController() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public ValidationResult validateAndCreatePoint(String jsonBody) {
        try {
            if (jsonBody == null || jsonBody.isEmpty()) {
                return ValidationResult.error("Missing input");
            }

            Map<String, String> params = parseJson(jsonBody);

 
            PointDto dto = new PointDto(params.get("x"), params.get("y"), params.get("r"));
            Set<ConstraintViolation<PointDto>> violations = validator.validate(dto);
            if (!violations.isEmpty()) {
                String msg = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
                return ValidationResult.error(msg);
            }

            return ValidationResult.success();

        } catch (Exception e) {
            return ValidationResult.error("Invalid input format: " + e.getMessage());
        }
    }


    public Point createPoint(String jsonBody) throws ValidationException {
        if (jsonBody == null || jsonBody.isEmpty()) {
            throw new ValidationException("Missing input");
        }

        Map<String, String> params;

        if (jsonBody.trim().startsWith("{")) {
            params = parseJson(jsonBody);
        } else {
            params = splitQuery(jsonBody);
        }

    
        PointDto dto = new PointDto(params.get("x"), params.get("y"), params.get("r"));
        Set<ConstraintViolation<PointDto>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            String msg = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
            throw new ValidationException(msg);
        }

        double x = new BigDecimal(dto.getX()).doubleValue();
        double y = new BigDecimal(dto.getY()).doubleValue();
        double r = new BigDecimal(dto.getR()).doubleValue();
        
        return new Point(x, y, r);
    }

    private Map<String, String> parseJson(String json) throws ValidationException {
        Map<String, String> params = new HashMap<>();

        try {
            json = json.trim().replaceAll("\\s+", "");

            String xValue = extractJsonValue(json, "x");
            String yValue = extractJsonValue(json, "y");
            String rValue = extractJsonValue(json, "r");

            if (xValue == null || yValue == null || rValue == null) {
                throw new ValidationException("Missing required fields in JSON");
            }

            params.put("x", xValue);
            params.put("y", yValue);
            params.put("r", rValue);

        } catch (Exception e) {
            throw new ValidationException("Invalid JSON format: " + e.getMessage());
        }

        return params;
    }

    private String extractJsonValue(String json, String key) {
        String keyPattern = "\"" + key + "\"";
        int keyStart = json.indexOf(keyPattern);
        if (keyStart == -1) return null;

        int colonIndex = json.indexOf(":", keyStart + keyPattern.length());
        if (colonIndex == -1) return null;

        int valueStart = colonIndex + 1;
        while (valueStart < json.length() && Character.isWhitespace(json.charAt(valueStart))) {
            valueStart++;
        }

        if (valueStart >= json.length()) return null;

        int valueEnd = valueStart;

        if (json.charAt(valueStart) == '"') {
            valueStart++;
            valueEnd = json.indexOf('"', valueStart);
            if (valueEnd == -1) return null;
        } else {
            while (valueEnd < json.length() &&
                    (Character.isDigit(json.charAt(valueEnd)) ||
                    json.charAt(valueEnd) == '.' ||
                    json.charAt(valueEnd) == '-' ||
                    json.charAt(valueEnd) == '+')) {
                valueEnd++;
            }
        }

        if (valueEnd <= valueStart) return null;
        return json.substring(valueStart, valueEnd);
    }

    private Map<String, String> splitQuery(String query) {
        return Arrays.stream(query.split("&"))
                .map(pair -> pair.split("="))
                .collect(
                        Collectors.toMap(
                                pairParts -> URLDecoder.decode(pairParts[0], StandardCharsets.UTF_8),
                                pairParts -> URLDecoder.decode(pairParts[1], StandardCharsets.UTF_8),
                                (a, b) -> b,
                                HashMap::new
                        )
                );
    }

}

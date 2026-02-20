package ru.rmntim.web.controller;

import ru.rmntim.web.model.HitResult;
import ru.rmntim.web.model.Point;
import ru.rmntim.web.model.ValidationResult;
import ru.rmntim.web.model.NotFoundResult;
import ru.rmntim.web.model.MethodNotAllowedResult;
import ru.rmntim.web.HitCalculator;
import ru.rmntim.web.ValidationException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class HitController {
    
    private final HitCalculator hitCalculator;
    private final ValidationController validationController;
    
    public HitController() {
        this.hitCalculator = new HitCalculator();
        this.validationController = new ValidationController();
    }
    
    public Object processFastCgiRequest() {
        try {
            String requestMethod = System.getProperty("REQUEST_METHOD");
        //    if (requestMethod == null) {
         //       requestMethod = System.getenv("REQUEST_METHOD");
        //    }
            if (requestMethod == null || !"POST".equalsIgnoreCase(requestMethod)) {
                return new MethodNotAllowedResult("неверный тип запроса");
            }

            String requestUri = System.getProperty("REQUEST_URI");
            if (requestUri == null) {
                requestUri = System.getenv("REQUEST_URI");
            }
            if (requestUri == null || !(requestUri.equals("/api/hit") || requestUri.equals("/api/hit/"))) {
                return new NotFoundResult("неверный путь запроса");
            }

            String jsonBody = readRequestBody();
            Point point = validationController.createPoint(jsonBody);
            Instant startTime = Instant.now();
            boolean result = hitCalculator.calculate(point.getX(), point.getY(), point.getR());
            Instant endTime = Instant.now();
            
            return new HitResult(
                ChronoUnit.NANOS.between(startTime, endTime),
                LocalDateTime.now(),
                result
            );
            
        } catch (ValidationException e) {
            return ValidationResult.error(e.getMessage());
        } catch (IOException e) {
            return ValidationResult.error("Error reading request body: " + e.getMessage());
        }
    }
    
    public Object processRequest(String jsonBody) {
        try {
            Point point = validationController.createPoint(jsonBody);
            Instant startTime = Instant.now();
            boolean result = hitCalculator.calculate(point.getX(), point.getY(), point.getR());
            Instant endTime = Instant.now();
            
            return new HitResult(
                ChronoUnit.NANOS.between(startTime, endTime),
                LocalDateTime.now(),
                result
            );
            
        } catch (ValidationException e) {
            return ValidationResult.error(e.getMessage());
        }
    }
    
    private String readRequestBody() throws IOException {
        var inputStream = System.in;
        var body = new StringBuilder();
        
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            body.append(new String(buffer, 0, bytesRead, StandardCharsets.UTF_8));
        }
        
        return body.toString();
    }
}

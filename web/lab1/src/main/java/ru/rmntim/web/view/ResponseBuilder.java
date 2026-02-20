package ru.rmntim.web.view;

import ru.rmntim.web.model.HitResult;
import ru.rmntim.web.model.ValidationResult;
import ru.rmntim.web.model.NotFoundResult;
import ru.rmntim.web.model.MethodNotAllowedResult;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;


public class ResponseBuilder {
    
    private final String httpResponseTemplate;
    private final String httpErrorTemplate;
    private final String httpNotFoundTemplate;
    private final String httpMethodNotAllowedTemplate;
    private final String resultJsonTemplate;
    private final String errorJsonTemplate;
    
    public ResponseBuilder() {
        this.httpResponseTemplate = """
                HTTP/1.1 200 OK
                Content-Type: application/json
                Content-Length: %d
                
                %s
                """;
        
        this.httpErrorTemplate = """
                HTTP/1.1 400 Bad Request
                Content-Type: application/json
                Content-Length: %d
                
                %s
                """;
        
        this.httpNotFoundTemplate = """
                HTTP/1.1 404 Not Found
                Content-Type: application/json
                Content-Length: %d
                
                %s
                """;
        
        this.httpMethodNotAllowedTemplate = """
                HTTP/1.1 405 Method Not Allowed
                Content-Type: application/json
                Content-Length: %d
                
                %s
                """;
        
        
        this.resultJsonTemplate = """
                {
                    "time": "%s",
                    "now": "%s",
                    "result": %b
                }
                """;
        
        this.errorJsonTemplate = """
                {
                    "now": "%s",
                    "reason": "%s"
                }
                """;
    }
    

    public String buildSuccessResponse(HitResult hitResult) {
        String json = buildSuccessJson(hitResult);
        return String.format(httpResponseTemplate, 
            json.getBytes(StandardCharsets.UTF_8).length + 2, 
            json);
    }
    
    public String buildErrorResponse(ValidationResult validationResult) {
        String json = buildErrorJson(validationResult);
        return String.format(httpErrorTemplate, 
            json.getBytes(StandardCharsets.UTF_8).length + 2, 
            json);
    }
    
    public String buildErrorResponse(String errorMessage) {
        String json = buildErrorJson(errorMessage);
        return String.format(httpErrorTemplate, 
            json.getBytes(StandardCharsets.UTF_8).length + 2, 
            json);
    }
    
    public String buildNotFoundResponse(NotFoundResult notFoundResult) {
        String json = buildErrorJson(notFoundResult.getErrorMessage());
        return String.format(httpNotFoundTemplate, 
            json.getBytes(StandardCharsets.UTF_8).length + 2, 
            json);
    }
    
    public String buildMethodNotAllowedResponse(MethodNotAllowedResult methodNotAllowedResult) {
        String json = buildErrorJson(methodNotAllowedResult.getErrorMessage());
        return String.format(httpMethodNotAllowedTemplate, 
            json.getBytes(StandardCharsets.UTF_8).length + 2, 
            json);
    }
    
    
    
    
    
    public String buildSuccessJson(HitResult hitResult) {
        return String.format(resultJsonTemplate, 
            hitResult.getExecutionTime(),
            hitResult.getTimestamp(),
            hitResult.isResult());
    }
    
    public String buildErrorJson(ValidationResult validationResult) {
        return String.format(errorJsonTemplate, 
            validationResult.getTimestamp(),
            validationResult.getErrorMessage());
    }
    
    public String buildErrorJson(String errorMessage) {
        return String.format(errorJsonTemplate, 
            LocalDateTime.now(),
            errorMessage);
    }
}

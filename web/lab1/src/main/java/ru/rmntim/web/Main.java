package ru.rmntim.web;

import com.fastcgi.FCGIInterface;
import ru.rmntim.web.controller.HitController;
import ru.rmntim.web.model.HitResult;
import ru.rmntim.web.model.ValidationResult;
import ru.rmntim.web.model.NotFoundResult;
import ru.rmntim.web.model.MethodNotAllowedResult;
import ru.rmntim.web.view.ResponseBuilder;

public class Main {
    
    private final HitController hitController;
    private final ResponseBuilder responseBuilder;
    
    public Main() {
        this.hitController = new HitController();
        this.responseBuilder = new ResponseBuilder();
    }

    public static void main(String[] args) {
        var main = new Main();
        main.run();
    }
    
    public void run() {
        var fcgi = new FCGIInterface();
        while (fcgi.FCGIaccept() >= 0) {
            try {
                Object result = hitController.processFastCgiRequest();
                if (result instanceof HitResult) {
                    HitResult hitResult = (HitResult) result;
                    String response = responseBuilder.buildSuccessResponse(hitResult);
                    System.out.println(response);
                } else if (result instanceof ValidationResult) {
                    ValidationResult validationResult = (ValidationResult) result;
                    String response = responseBuilder.buildErrorResponse(validationResult);
                    System.out.println(response);
                } else if (result instanceof NotFoundResult) {
                    NotFoundResult notFoundResult = (NotFoundResult) result;
                    String response = responseBuilder.buildNotFoundResponse(notFoundResult);
                    System.out.println(response);
                } else if (result instanceof MethodNotAllowedResult) {
                    MethodNotAllowedResult methodNotAllowedResult = (MethodNotAllowedResult) result;
                    String response = responseBuilder.buildMethodNotAllowedResponse(methodNotAllowedResult);
                    System.out.println(response);
                }
            } catch (Exception e) {
                String response = responseBuilder.buildErrorResponse("Internal server error: " + e.getMessage());
                System.out.println(response);
            }
        }
    }
}
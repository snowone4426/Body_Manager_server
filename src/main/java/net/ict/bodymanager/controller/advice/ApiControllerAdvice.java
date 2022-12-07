package net.ict.bodymanager.controller.advice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> badRequest(Exception e){
        e.printStackTrace();
        Map<String, Object> result = Map.of("message", "insufficuent request data");
        return ResponseEntity.badRequest().body(result);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Map<String, Object>> serverError(Exception e){
        e.printStackTrace();
        Map<String, Object> result = Map.of("message", "server error");
        return ResponseEntity.internalServerError().body(result);
    }

}

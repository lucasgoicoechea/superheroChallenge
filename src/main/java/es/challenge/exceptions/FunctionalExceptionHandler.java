package es.challenge.exceptions;


import es.challenge.aspectFunctions.Counter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@RestController
@ControllerAdvice
public class FunctionalExceptionHandler extends ResponseEntityExceptionHandler {

   
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Authentication failed functional");
        response.put("status", HttpStatus.UNAUTHORIZED.value());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
    
    @ExceptionHandler({ResponseStatusException.class})
    @Counter
    protected ResponseEntity<?> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getReason());
        response.put("status", ex.getRawStatusCode());
        return ResponseEntity.status(ex.getStatus()).body(response);
    }


}


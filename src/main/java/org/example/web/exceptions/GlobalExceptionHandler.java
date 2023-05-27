package org.example.web.exceptions;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends DefaultErrorAttributes {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Map<String, Object>> handle(MethodArgumentNotValidException ex, WebRequest request) {
        List<ConstraintsViolationError> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ConstraintsViolationError(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        return ofType(request, HttpStatus.BAD_REQUEST, ex.getMessage(), null, validationErrors);
    }

    private ResponseEntity<Map<String, Object>> ofType(WebRequest request, HttpStatus status, String message,
                                                       String key, List<ConstraintsViolationError> validationErrors) {
        Map<String, Object> attributes = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        attributes.put("STATUS", status.value());
        attributes.put("ERROR", status);
        attributes.put("MESSAGE", message);
        attributes.put("ERRORS", validationErrors);
        attributes.put("ERROR_KEY", key);
        attributes.put("PATH", ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(attributes, status);
    }

}
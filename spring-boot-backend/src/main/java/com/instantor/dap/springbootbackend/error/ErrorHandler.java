package com.instantor.dap.springbootbackend.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ErrorHandler {
    private final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleRequestNotSupported(Exception ex, WebRequest request) {
        logger.error("Error message" , ex);
        return ResponseEntity.status(500).body("Server error, try again");
    }

}

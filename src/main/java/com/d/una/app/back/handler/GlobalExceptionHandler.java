package com.d.una.app.back.handler;

import com.d.una.app.back.domain.ResponseDto;
import com.d.una.app.back.exception.BusinessRuleException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles exceptions about type BusinessRuleException.
     * Logs the error message and returns a response for this specific exception.
     *
     * @param e The BusinessRuleException instance.
     * @return Response entity containing error details.
     */
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ResponseDto<Object>> handleBusinessRuleException(BusinessRuleException e) {
        return new ResponseDto<>(e.getStatus(), e.getMessage(), e.getErrorCode()).of();
    }
}

package com.exchange.currency.api.v1;

import com.exchange.currency.api.model.ApiErrorModel;
import com.exchange.currency.exception.CurrencyAmountValidationException;
import com.exchange.currency.exception.CurrencyPairNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CurrencyPairNotFoundException.class)
    public ResponseEntity<ApiErrorModel> handelCurrencyPairNotFoundException(CurrencyPairNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorModel().errorMessage(e.getMessage()));
    }

    @ExceptionHandler(CurrencyAmountValidationException.class)
    public ResponseEntity<ApiErrorModel> handelCurrencyAmountValidationException(CurrencyAmountValidationException e) {
        return ResponseEntity.unprocessableEntity().body(new ApiErrorModel().errorMessage(e.getMessage()));
    }
}

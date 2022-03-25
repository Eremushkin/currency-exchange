package com.exchange.currency.exception;

public class CurrencyAmountValidationException extends IllegalArgumentException {

    public CurrencyAmountValidationException(String message) {
        super(message);
    }
}

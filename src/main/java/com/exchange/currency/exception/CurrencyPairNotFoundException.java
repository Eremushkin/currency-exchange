package com.exchange.currency.exception;

import org.webjars.NotFoundException;

public class CurrencyPairNotFoundException extends NotFoundException {

    public CurrencyPairNotFoundException(String currencyPair) {
        super("Currency pair " + currencyPair + " not found");
    }
}

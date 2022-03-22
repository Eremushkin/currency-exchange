package com.exchange.currency.service.dto;

import java.math.BigDecimal;

public record ExchangeInformationDto(
        BigDecimal spendAmount,
        CurrencyName spendCurrency,
        BigDecimal purchaseAmount,
        CurrencyName purchaseCurrency
) {
}

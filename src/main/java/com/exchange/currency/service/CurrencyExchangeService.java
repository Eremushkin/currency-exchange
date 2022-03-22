package com.exchange.currency.service;

import com.exchange.currency.service.dto.ExchangeInformationDto;

public interface CurrencyExchangeService {

    ExchangeInformationDto calculateCurrencyAmount(ExchangeInformationDto requestAmountDto);
}

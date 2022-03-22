package com.exchange.currency.convertor;

import com.exchange.currency.api.model.ExchangeInformationModel;
import com.exchange.currency.service.dto.CurrencyName;
import com.exchange.currency.service.dto.ExchangeInformationDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class ExchangeInformationModelToDtoConvertor implements Convertor<ExchangeInformationModel, ExchangeInformationDto> {

    @Override
    public ExchangeInformationDto convert(ExchangeInformationModel exchangeInformationModel) {
        return new ExchangeInformationDto(
                Optional.ofNullable(exchangeInformationModel.getSpendAmount())
                        .map(Double::parseDouble)
                        .map(BigDecimal::valueOf)
                        .orElse(null),
                CurrencyName.valueOf(exchangeInformationModel.getSpendCurrency().toUpperCase()),
                Optional.ofNullable(exchangeInformationModel.getPurchaseAmount())
                        .map(Double::parseDouble)
                        .map(BigDecimal::valueOf)
                        .orElse(null),
                CurrencyName.valueOf(exchangeInformationModel.getPurchaseCurrency().toUpperCase())
        );
    }
}

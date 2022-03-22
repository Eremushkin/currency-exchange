package com.exchange.currency.service;

import com.exchange.currency.config.CurrencyRateProperties;
import com.exchange.currency.service.dto.ExchangeInformationDto;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.math.BigDecimal;
import java.math.MathContext;

@Component
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    private final CurrencyRateProperties currencyRateProperties;

    public CurrencyExchangeServiceImpl(CurrencyRateProperties currencyRateProperties) {
        this.currencyRateProperties = currencyRateProperties;
    }

    @Override
    public ExchangeInformationDto calculateCurrencyAmount(ExchangeInformationDto requestAmountDto) {
        BigDecimal rate = getRate(requestAmountDto);
        if (requestAmountDto.spendAmount() == null) {
            if (requestAmountDto.purchaseAmount() == null) {
                throw new IllegalArgumentException("If spend amount is NULL, purchase amount should be NOT NULL");
            }

            BigDecimal spendAmountResult = BigDecimal.valueOf(1.0 + currencyRateProperties.getMargin())
                    .multiply(rate)
                    .multiply(requestAmountDto.purchaseAmount());

            return new ExchangeInformationDto(
                    spendAmountResult,
                    requestAmountDto.spendCurrency(),
                    requestAmountDto.purchaseAmount(),
                    requestAmountDto.purchaseCurrency()
            );
        } else {
            if (requestAmountDto.purchaseAmount() != null) {
                throw new IllegalArgumentException("spend amount and purchase amount cannot be NOT NULL the same time");
            }
            BigDecimal purchaseAmountResult = requestAmountDto.spendAmount()
                    .divide(rate, MathContext.DECIMAL64)
                    .multiply(BigDecimal.valueOf(1.0 - currencyRateProperties.getMargin()));

            return new ExchangeInformationDto(
                    requestAmountDto.spendAmount(),
                    requestAmountDto.spendCurrency(),
                    purchaseAmountResult,
                    requestAmountDto.purchaseCurrency()
            );
        }
    }

    private BigDecimal getRate(ExchangeInformationDto requestAmountDto) {
        String currencyPair = requestAmountDto.purchaseCurrency().name() + "-" + requestAmountDto.spendCurrency().name();

        assert currencyRateProperties != null;
        assert currencyRateProperties.getRateMap() != null;
        if (!currencyRateProperties.getRateMap().containsKey(currencyPair)) {
            String reverseCurrencyPair =
                    requestAmountDto.spendCurrency().name() + "-" + requestAmountDto.purchaseCurrency().name();
            if (currencyRateProperties.getRateMap().containsKey(reverseCurrencyPair)) {
                return BigDecimal.ONE.divide(
                        BigDecimal.valueOf(currencyRateProperties.getRateMap().get(reverseCurrencyPair)),
                        MathContext.DECIMAL64);
            }

            throw new NotFoundException("Currency pair " + currencyPair + " not found");
        }

        return BigDecimal.valueOf(currencyRateProperties.getRateMap().get(currencyPair));
    }
}

package com.exchange.currency.service;

import com.exchange.currency.config.CurrencyRateProperties;
import com.exchange.currency.service.dto.CurrencyName;
import com.exchange.currency.service.dto.ExchangeInformationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CurrencyExchangeServiceImplTest {

    private static final CurrencyName CURRENCY_1 = CurrencyName.GRB;
    private static final CurrencyName CURRENCY_2 = CurrencyName.EUR;
    private static final String CURRENCY_PAIR = CURRENCY_1 + "-" + CURRENCY_2;
    private static final Map<String, Double> RATE_MAP = new HashMap<>();

    static {
        RATE_MAP.put(CURRENCY_PAIR, 2.0);
    }

    private final CurrencyRateProperties properties = new CurrencyRateProperties();
    private final CurrencyExchangeService service = new CurrencyExchangeServiceImpl(properties);

    @BeforeEach
    void setUp() {
        properties.setRateMap(RATE_MAP);
        properties.setMargin(0.5);
    }

    @Test
    public void spendCurrencyAmountShouldCalculateCorrect() {
        //GIVEN
        ExchangeInformationDto dto =
                new ExchangeInformationDto(null, CURRENCY_2, BigDecimal.ONE, CURRENCY_1);
        //WHEN
        ExchangeInformationDto actualDto = service.calculateCurrencyAmount(dto);

        //THEN
        assertNotNull(actualDto);
        assertEquals(0, BigDecimal.valueOf(3.0).compareTo(actualDto.spendAmount()));
        assertEquals(CURRENCY_2, actualDto.spendCurrency());
        assertEquals(0, BigDecimal.ONE.compareTo(actualDto.purchaseAmount()));
        assertEquals(CURRENCY_1, actualDto.purchaseCurrency());
    }

    @Test
    public void spendCurrencyAmountShouldCalculateCorrectForReversePair() {
        //GIVEN
        properties.setMargin(0.0);
        ExchangeInformationDto dto =
                new ExchangeInformationDto(null, CURRENCY_1, BigDecimal.ONE, CURRENCY_2);
        //WHEN
        ExchangeInformationDto actualDto = service.calculateCurrencyAmount(dto);

        //THEN
        assertNotNull(actualDto);
        assertEquals(0, BigDecimal.valueOf(0.5).compareTo(actualDto.spendAmount()));
        assertEquals(CURRENCY_1, actualDto.spendCurrency());
        assertEquals(0, BigDecimal.ONE.compareTo(actualDto.purchaseAmount()));
        assertEquals(CURRENCY_2, actualDto.purchaseCurrency());
    }

    @Test
    public void purchaseCurrencyAmountShouldCalculateCorrect() {
        //GIVEN
        ExchangeInformationDto dto =
                new ExchangeInformationDto(BigDecimal.valueOf(2), CURRENCY_2, null, CURRENCY_1);
        //WHEN
        ExchangeInformationDto actualDto = service.calculateCurrencyAmount(dto);

        //THEN
        assertNotNull(actualDto);
        assertEquals(0, BigDecimal.valueOf(2.0).compareTo(actualDto.spendAmount()));
        assertEquals(CURRENCY_2, actualDto.spendCurrency());
        assertEquals(0, BigDecimal.valueOf(0.5).compareTo(actualDto.purchaseAmount()));
        assertEquals(CURRENCY_1, actualDto.purchaseCurrency());
    }

    @Test
    public void purchaseCurrencyAmountShouldCalculateCorrectForReversePair() {
        //GIVEN
        properties.setMargin(0.0);
        ExchangeInformationDto dto =
                new ExchangeInformationDto(BigDecimal.valueOf(2), CURRENCY_1, null, CURRENCY_2);
        //WHEN
        ExchangeInformationDto actualDto = service.calculateCurrencyAmount(dto);

        //THEN
        assertNotNull(actualDto);
        assertEquals(0, BigDecimal.valueOf(2.0).compareTo(actualDto.spendAmount()));
        assertEquals(CURRENCY_1, actualDto.spendCurrency());
        assertEquals(0, BigDecimal.valueOf(4.0).compareTo(actualDto.purchaseAmount()));
        assertEquals(CURRENCY_2, actualDto.purchaseCurrency());
    }
}
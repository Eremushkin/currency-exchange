package com.exchange.currency.convertor;

import com.exchange.currency.api.model.ExchangeInformationModel;
import com.exchange.currency.service.dto.ExchangeInformationDto;
import com.exchange.currency.util.BigDecimalUtil;
import org.springframework.stereotype.Component;

@Component
public class ExchangeInformationDtoToModelConvertor implements Convertor<ExchangeInformationDto, ExchangeInformationModel> {

    @Override
    public ExchangeInformationModel convert(ExchangeInformationDto currencyAmountDto) {
        ExchangeInformationModel model = new ExchangeInformationModel();
        model.setSpendAmount(BigDecimalUtil.toString2DecimalPlaces(currencyAmountDto.spendAmount()));
        model.setSpendCurrency(currencyAmountDto.spendCurrency().name());
        model.setPurchaseAmount(BigDecimalUtil.toString2DecimalPlaces(currencyAmountDto.purchaseAmount()));
        model.setPurchaseCurrency(currencyAmountDto.purchaseCurrency().name());
        return model;
    }
}

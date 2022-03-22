package com.exchange.currency.api.v1;

import com.exchange.currency.api.V1Api;
import com.exchange.currency.api.model.ExchangeInformationModel;
import com.exchange.currency.convertor.ExchangeInformationDtoToModelConvertor;
import com.exchange.currency.convertor.ExchangeInformationModelToDtoConvertor;
import com.exchange.currency.service.CurrencyExchangeService;
import com.exchange.currency.service.dto.ExchangeInformationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController implements V1Api {

    private final ExchangeInformationDtoToModelConvertor dtoToModelConvertor;
    private final ExchangeInformationModelToDtoConvertor modelToDtoConvertor;
    private final CurrencyExchangeService currencyExchangeService;

    public CurrencyExchangeController(
            ExchangeInformationDtoToModelConvertor dtoToModelConvertor,
            ExchangeInformationModelToDtoConvertor modelToDtoConvertor,
            CurrencyExchangeService currencyExchangeService) {
        this.dtoToModelConvertor = dtoToModelConvertor;
        this.modelToDtoConvertor = modelToDtoConvertor;
        this.currencyExchangeService = currencyExchangeService;
    }

    @Override
    public ResponseEntity<ExchangeInformationModel> exchange(ExchangeInformationModel exchangeRequest) {
        ExchangeInformationDto resultDto =
                currencyExchangeService.calculateCurrencyAmount(modelToDtoConvertor.convert(exchangeRequest));
        return ResponseEntity.ok(dtoToModelConvertor.convert(resultDto));
    }
}

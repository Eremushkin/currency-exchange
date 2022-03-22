package com.exchange.currency.api.v1;

import com.exchange.currency.api.model.ExchangeInformationModel;
import com.exchange.currency.service.dto.CurrencyName;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyExchangeControllerTest {

    private static final String EXCHANGE_URI = "/v1/exchange";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void exchangeEndpointShouldReturnStatus200ForCorrectBody() throws Exception {
        //GIVEN
        ExchangeInformationModel bodyModel = new ExchangeInformationModel()
                .spendAmount("1.12")
                .spendCurrency(CurrencyName.EUR.name())
                .purchaseCurrency(CurrencyName.GRB.name());
        //WHEN
        mvc.perform(post(EXCHANGE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bodyModel)))
        //THEN
                .andExpect(status().isOk());
    }
}
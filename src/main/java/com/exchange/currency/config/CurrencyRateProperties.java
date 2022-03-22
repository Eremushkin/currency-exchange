package com.exchange.currency.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "config.currency")
public class CurrencyRateProperties {

    private Map<String, Double> rateMap = new HashMap<>();
    private Double margin;

    public Map<String, Double> getRateMap() {
        return rateMap;
    }

    public void setRateMap(Map<String, Double> rateMap) {
        this.rateMap = rateMap;
    }

    public Double getMargin() {
        return margin;
    }

    public void setMargin(Double margin) {
        this.margin = margin;
    }
}

package com.exchange.currency.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BigDecimalUtil {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat();

    static {
        DECIMAL_FORMAT.setMaximumFractionDigits(2);
        DECIMAL_FORMAT.setMinimumFractionDigits(0);
    }

    public static String toString2DecimalPlaces(BigDecimal value) {
        return DECIMAL_FORMAT.format(value);
    }
}

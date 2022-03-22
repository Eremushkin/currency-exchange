package com.exchange.currency.convertor;

public interface Convertor<From, To> {

    To convert(From from);
}

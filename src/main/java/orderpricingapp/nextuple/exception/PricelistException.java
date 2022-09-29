package com.orderPricingApp.springboot.exception;

public class PricelistException extends Exception{

    public PricelistException() {super();}

    public PricelistException(String msg)
    {
        super(msg);
    }
}

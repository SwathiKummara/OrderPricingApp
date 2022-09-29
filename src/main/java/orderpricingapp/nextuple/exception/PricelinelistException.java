package com.orderPricingApp.springboot.exception;


public class PricelinelistException extends Exception{

    public PricelinelistException(){
        super();
    }
    public PricelinelistException(String message){
        super(message);
    }
}
package com.taopaokuzi.train.common.exception;
public class BusinessException extends RuntimeException{
    private BusinessExceptionEnum e;

    public BusinessExceptionEnum getE() {
        return e;
    }

    public void setE(BusinessExceptionEnum e) {
        this.e = e;
    }

    public BusinessException(BusinessExceptionEnum e) {
        this.e = e;
    }
}

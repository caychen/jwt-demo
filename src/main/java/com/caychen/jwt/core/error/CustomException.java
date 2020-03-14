package com.caychen.jwt.core.error;

/**
 * @Author: Caychen
 * @Date: 2020-03-11
 * @Describe:
 */
public class CustomException extends Exception {

    private GlobalCode code;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(GlobalCode code){
        this.code = code;
    }

    public GlobalCode getCode() {
        return code;
    }
}

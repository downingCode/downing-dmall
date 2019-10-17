package com.downing.exception;

/**
 * @author downing
 * @descript
 */
public class LogicException extends RuntimeException{

    private Integer code;
    private String message;

    public LogicException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

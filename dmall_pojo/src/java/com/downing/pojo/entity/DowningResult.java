package com.downing.pojo.entity;

import java.io.Serializable;

/**
 * @author downing
 * @descript 结果集封装
 */
public class DowningResult implements Serializable {

    private Integer code;

    private String message;

    private Object data;

    public DowningResult() {
        this.code = 200;
        this.message = "操作成功";
    }

    public DowningResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public DowningResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

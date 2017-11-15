package com.mesen.vo;

import java.io.Serializable;

/**
 * Created by maosheng on 2017/11/15
 */
public class PageVo<T> implements Serializable{
    private String code = "0";
    private String msg;
    private T data;

    public PageVo() {
        super();
    }

    public PageVo(T data) {

        this.data = data;
    }

    public PageVo(String code, String msg) {

        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

package com.mesen.vo;

import java.io.Serializable;

/**
 * Created by maosheng on 2017/11/15
 */
public class PageVo<T> implements Serializable{
    private String code = "-1";
    private String serverId ;//TODO 获取serverId
    private String msg = "服务器异常";
    private T data;

    public PageVo() {
        super();
    }

    public PageVo(T data) {
        this.code = "0";
        this.msg = "OK!";
        this.data = data;
    }

    public PageVo(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public PageVo(String code, String serverId, String msg) {
        this.code = code;
        this.serverId = serverId;
        this.msg = msg;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
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

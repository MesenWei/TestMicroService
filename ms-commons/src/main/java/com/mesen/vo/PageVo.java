package com.mesen.vo;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * TODO 是否设置成单例模式，单例模式有什么好处；是否用spring管理；serverId自动获取。
 *
 * Created by maosheng on 2017/11/15
 */
public class PageVo<T> implements Serializable{
    private String serverId ;
    private String code = "-1";
    private String msg = "服务器异常";
    private T data;
    private Long timestamp ;

    public PageVo() {
        super();
        this.timestamp = System.currentTimeMillis();
    }

    public PageVo(T data) {
        this.code = "0";
        this.msg = "OK!";
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public PageVo(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.timestamp = System.currentTimeMillis();
    }

    public PageVo(String serverId, String code, String msg) {
        this.serverId = serverId;
        this.code = code;
        this.msg = msg;
        this.timestamp = System.currentTimeMillis();
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}

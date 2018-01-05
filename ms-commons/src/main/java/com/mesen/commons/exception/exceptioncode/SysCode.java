package com.mesen.commons.exception.exceptioncode;

/**
 * Created by SysCode on 2017/12/25.
 */
public enum SysCode {
    SERVER_DOEN(-1,"服务器宕机！"),
    TIMEOUT(-2,"线程执行超时！"),
    UNKNOW(-3,"未知异常！"),
    OPTIONAL_NOT_NULL(-4,"参数错误！");

    Integer code;
    String msg;

    SysCode(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SysCode setMsg(String msg) {
        this.msg = msg;

        return this;
    }

    public String toString(){
        return new StringBuffer().append(getCode()).append("#").append(getMsg()).toString();
    }
}

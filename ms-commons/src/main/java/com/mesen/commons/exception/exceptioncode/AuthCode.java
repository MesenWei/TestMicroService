package com.mesen.commons.exception.exceptioncode;

/**
 * Created by AuthCode on 2017/12/25.
 */
public enum AuthCode {
    MOBILEPHONENUMERROR(100001,"手机号码错误"),
    SIMPLEMSGCODEERROR(100002,"短信验证码错误");

    Integer code;
    String msg;

    AuthCode(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public AuthCode setMsg(String msg) {
        this.msg = msg;

        return this;
    }

    public String toString(){
        return new StringBuffer().append(getCode()).append("#").append(getMsg()).toString();
    }
}

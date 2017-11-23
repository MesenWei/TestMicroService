package com.mesen.commons.fallbackfactory;

import com.mesen.commons.exception.MyRuntimeException;
import com.mesen.vo.PageVo;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * FallbackFactoryBase作为所有FallbackFactory实现类的父类，用来处理一些简单而频繁的操作。
 *
 * 使用Optional作为参数，是为了防止msg为null的情况。
 *
 * Created by maosheng on 2017/11/22
 */
public class ExceptionAutowired {
    public static String getServerId(Optional<Throwable> optional){
        Throwable throwable = optional.get();

        if(isAutoException(throwable))
            return null;

        return splitExceptionMsg(throwable.getMessage())[0];
    }

    private static boolean isAutoException(Throwable throwable){
        if(!(throwable instanceof MyRuntimeException))
            return true;

        return false;
    }

    public static PageVo autoWired(Optional<Throwable> optional){
        Throwable throwable = optional.get();

        if(isAutoException(throwable))
            return serverDown();

        String[] split = splitExceptionMsg(throwable.getMessage());

        if(ArrayUtils.isEmpty(split) || split.length < 3)
            throw new MyRuntimeException("异常信息中必须包含三部分内容且用#隔开：服务ID#异常code#异常描述");

        return new PageVo(
                StringUtils.isNotBlank(split[0]) ? split[0] : null,
                StringUtils.isNotBlank(split[1]) ? split[1] : null,
                StringUtils.isNotBlank(split[2]) ? split[2] : null);
    }

    private static PageVo serverDown(){
        return new PageVo("unknown","-1","未知服务器宕机！");
    }

    private static String[] splitExceptionMsg(String msg){
        if(StringUtils.isBlank(msg))
            return null;

        return msg.split("#");
    }

    public static void main (String[] org0){
        String msg = "micro-server2-hystrix-user#100001#这是一个bugggggg er";
        String msgbad = "micro-server2-hystrix-user#100001";

        //MyRuntimeException exception = new MyRuntimeException(msg);
        //MyRuntimeException exception = new MyRuntimeException(msgbad);

        RuntimeException exception = new RuntimeException(msg);

        ExceptionAutowired exceptionAutowired = new ExceptionAutowired();

        PageVo pageVo = exceptionAutowired.autoWired(Optional.of(exception));

        System.out.println(pageVo);
    }
}

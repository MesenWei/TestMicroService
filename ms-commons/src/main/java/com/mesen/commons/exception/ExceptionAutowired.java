package com.mesen.commons.exception;

import com.mesen.util.UrlUtil;
import com.mesen.vo.PageVo;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import feign.RetryableException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Optional;

/**
 * 使用Optional作为参数，是为了防止msg为null的情况。
 *
 * Created by maosheng on 2017/11/22
 */
@Configuration
public class ExceptionAutowired {
    @Autowired
    private Environment environment ;
    private String serverId ;

    public String getServerId(){
        if(StringUtils.isBlank(serverId))
            this.serverId = environment.getProperty("spring.application.name");

        return this.serverId;
    }

    public PageVo autoWired(Optional<Throwable> optional){
        try {
            throw optional.get();
        } catch (RetryableException e){
            return serverDown(e);
        } catch (HystrixTimeoutException e) {
            return feignHystrixTimeout(e);
        } catch (MyRuntimeException e) {
            return customizeException(e);
        } catch (MyException e) {
            return customizeException(e);
        } catch (Throwable e) {
            e.printStackTrace();
            return unDisposeException();
        }
    }

    private PageVo serverDown(RetryableException e){
        String msg = e.getMessage();
        String host = UrlUtil.getHost(Optional.of(msg));
        return new PageVo(getServerId(),"-1",host + "服务器宕机！");
    }

    private PageVo feignHystrixTimeout(HystrixTimeoutException e){
        return new PageVo(getServerId(),"-2","线程执行超时！");
    }

    private PageVo customizeException(MyException e){
        return customizeException(e,null);
    }

    private PageVo customizeException(MyRuntimeException e){
        return customizeException(null,e);
    }

    private PageVo customizeException(MyException e1,MyRuntimeException e2){
        Throwable throwable = null != e1 ? e1 : e2;

        String[] split = splitExceptionMsg(throwable.getMessage());

        if(ArrayUtils.isEmpty(split) || split.length < 3)
            throw new MyRuntimeException("异常信息中必须包含三部分内容且用#隔开：服务ID#异常code#异常描述");

        return new PageVo(
                StringUtils.isNotBlank(split[0]) ? split[0] : null,
                StringUtils.isNotBlank(split[1]) ? split[1] : null,
                StringUtils.isNotBlank(split[2]) ? split[2] : null);
    }

    private PageVo unDisposeException(){
        return new PageVo(getServerId(),"-3","一个controller未捕获的未知异常！");
    }

    private String[] splitExceptionMsg(String msg){
        if(StringUtils.isBlank(msg))
            return null;

        return msg.split("#");
    }

    public MyStackTraceElement getStackTraceElement(MyException e){
        return getStackTraceElement(e,null);
    }

    public MyStackTraceElement getStackTraceElement(Throwable e){
        return getStackTraceElement(null,e);
    }

    private MyStackTraceElement getStackTraceElement(MyException e1,Throwable e2){
        StackTraceElement[] st ;
        if(null == e1 && null != e2)
            st = e2.getStackTrace();
        else if(null != e1 && null == e2)
            st = e1.getStackTrace();
        else
            return null;

        for (StackTraceElement stackTraceElement : st) {
            String exclass = stackTraceElement.getClassName();
            String method = stackTraceElement.getMethodName();
            System.out.println(exclass + ":::::" + method);
        }

        return null;
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

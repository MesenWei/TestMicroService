package com.mesen.commons.fallbackfactory;

import com.mesen.commons.exception.ExceptionAutowired;
import com.mesen.vo.PageVo;

import java.util.Optional;

/**
 * ControllerCommonFallback是我们自定义的所有controller的公共Fallback，用于web请求时的异常处理。
 *
 * Created by maosheng on 2017/11/22
 */
public class ControllerCommonFallback extends ExceptionAutowired {
    public PageVo controllerFbMethod(Throwable throwable){
        return autoWired(Optional.of(throwable));
    }
}

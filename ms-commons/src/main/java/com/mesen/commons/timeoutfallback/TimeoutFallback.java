package com.mesen.commons.timeoutfallback;

import com.mesen.commons.fallbackfactory.ExceptionAutowired;
import com.mesen.vo.PageVo;

/**
 * Created by maosheng on 2017/11/22
 */
public class TimeoutFallback extends ExceptionAutowired {
    public PageVo timeoutFallback(){
        return new PageVo("TODO","-2","方法执行时间超时！");
    }
}

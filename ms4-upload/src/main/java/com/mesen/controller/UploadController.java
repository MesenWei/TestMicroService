package com.mesen.controller;

import com.mesen.commons.exception.MyRuntimeException;
import com.mesen.commons.fallbackfactory.ControllerCommonFallback;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * Created by maosheng on 2017/12/6
 */
@RestController
public class UploadController extends ControllerCommonFallback {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @HystrixCommand(defaultFallback = "controllerFbMethod")
    public String upload(String name, MultipartFile file) {
        try{
            if(null == file)
                throw new MyRuntimeException("");
        } catch (MyRuntimeException e){
            e.printStackTrace();
        }

        return null;
    }
}

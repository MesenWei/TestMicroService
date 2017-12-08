package com.mesen.controller;

import com.mesen.commons.exception.MyRuntimeException;
import com.mesen.commons.fallbackfactory.ControllerCommonFallback;
import com.mesen.vo.PageVo;
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

    /**
     * Spring security的跨站访问（csrf）是被禁止，这样是安全的。
     * 在微服务架构中，我需要允许跨站访问，在WebSecurityConfig进行了配置。
     *
     * @param name
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @HystrixCommand(defaultFallback = "controllerFbMethod")
    public PageVo upload(String name, MultipartFile file) {
        PageVo pageVo = null;
        try{
            if(null == file)
                throw new MyRuntimeException("1#我疯狂疯狂疯狂疯狂的出了个错！");

            String result = file.getOriginalFilename() + "_____" + file.getSize();
            pageVo = new PageVo(result);
        } catch (MyRuntimeException e){
            throw new MyRuntimeException(e.getMessage());
        }

        return pageVo;
    }

    @RequestMapping(value = "/testpost", method = RequestMethod.POST)
    public String test(){
        return "testpost";
    }

    @RequestMapping(value = "/testget")
    public String test2(){
        return "testgetsasd";
    }
}

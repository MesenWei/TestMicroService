package com.mesen.util;

import com.mesen.commons.exception.MyRuntimeException;

import java.util.Optional;

/**
 * Created by maosheng on 2017/11/27
 */
public class UrlUtil {
    /**
     * 获取url的host，url中应当包含http://。
     *
     * @param optional
     * @return
     */
    public static String getHost(Optional<String> optional){
            String url = optional.get();
            String url2 = url.toLowerCase();

            int firstIndex = url2.indexOf("http://");

            if(firstIndex == -1)
                throw new MyRuntimeException("传入的url缺少Http://表示");

            firstIndex = firstIndex + 7;

            int endIndex = url2.indexOf("/",firstIndex);

            if(endIndex == -1)
                endIndex = url2.length();

        return url.substring(firstIndex,endIndex);
    }

    public static void main (String[] org0){
        String url = "Connection refused: connect executing GET http://micro-server2-hystrix-user";
        String host = getHost(Optional.of(url));
        System.out.println(host);
    }
}

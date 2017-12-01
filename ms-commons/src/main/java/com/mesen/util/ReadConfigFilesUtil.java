package com.mesen.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

@Configuration
public class ReadConfigFilesUtil {
    private static String filePath = "/application.yml";

    private static Properties prop = new Properties();
    private static Yaml yaml = new Yaml();
    private static Map map;

    private static void initProperty(){
        try {
            if(prop.size() != 0)
                return;

            InputStream in = ReadConfigFilesUtil.class.getResourceAsStream(filePath);

            prop.load(in);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initYaml(){
        if(null != map)
            return;

        InputStream in = ReadConfigFilesUtil.class.getResourceAsStream(filePath);

        map = yaml.load(in);
        System.out.println(map);
    }

    public static String propProperty(Optional<String> optional){
        initProperty();

        return prop.getProperty(optional.get());
    }

    public static Object yamlProperty(Optional<String> optional){
        initYaml();

        String value = (String) map.get(optional.get());

        if(StringUtils.isNotBlank(value))
            return value;

        return yamlProperty(optional.get());
    }


    private static Object yamlProperty(String property){
        String[] str = property.split("\\.");

        Object obj = map;
        for (String s : str) {
            obj = ((Map)obj).get(s);
        }

        return obj;
    }

    public static void main (String[] org0){
        String[] str = "security.basic.enabled".split("\\.");
        System.out.println(str);
    }
}

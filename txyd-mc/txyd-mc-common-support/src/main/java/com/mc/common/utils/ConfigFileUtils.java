package com.mc.common.utils;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Description: 读取配置文件http地址配置<br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉信息技术有限公司<br>
 * 
 * @author 赵义云
 * @date 2015年7月28日
 */
public class ConfigFileUtils {

    /**
     * 读取authconfig.properties
     * 
     * @param nodeName
     * @return
     */
    public static String getAuthconfig(String nodeName) {
        String configName = "application.properties";
        String result = pubReadConfig(configName, nodeName);
        if (result == null) {
            result = pubReadConfig("prop/" + configName, nodeName);
        }
        return result;
    }

    /**
     * 通用读取方法
     * 
     * @param configName
     * @param nodeName
     * @return
     */
    public static String pubReadConfig(String configName, String nodeName) {
        Properties props = new Properties();
        while (true) {
            try {
                props = PropertiesLoaderUtils.loadAllProperties(configName);
                return (String) props.get(nodeName);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

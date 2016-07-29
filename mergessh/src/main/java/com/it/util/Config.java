package com.it.util;


import java.util.Properties;

/**
 *
 *
 */
public class Config {

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (Exception e) {
            throw new RuntimeException("读取config.properties文档异常", e);
        }
    }

    /**
     * @param key 输入查询的key
     * @return 返回值
     */

    public static String get(String key) {
        return properties.getProperty(key);
    }

    /**
     * @param key          输入查询的key
     * @param defaultvalue 默认值
     * @return 返回值
     */
    public static String get(String key, String defaultvalue) {
        return properties.getProperty(key, defaultvalue);
    }

}

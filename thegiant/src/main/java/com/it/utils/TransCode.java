package com.it.utils;

/**
 * Created by xieyue on 2016/7/5.
 */


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class TransCode {
    Logger logger = LoggerFactory.getLogger(TransCode.class);


    public static String toUTF8(String str){
        if(StringUtils.isNotEmpty(str)){
            try {
                return new String(str.getBytes("ISO8859-1"),"utf-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("转码异常");
            }
        }
        return "";
    }


}

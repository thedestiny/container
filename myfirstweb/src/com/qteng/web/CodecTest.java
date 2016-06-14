package com.qteng.web;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * Created by xieyue on 2016/6/14.
 */
public class CodecTest {


    @Test
    public void  test(){
        String md5 = DigestUtils.md5Hex("wd123");
        System.out.println(md5);
    }

}

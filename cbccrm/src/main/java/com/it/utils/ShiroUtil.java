package com.it.utils;

/**
 * Created by xieyue on 2016/7/8.
 * ShiroUtil
 */


import com.it.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroUtil {
    Logger logger = LoggerFactory.getLogger(ShiroUtil.class);

    public static User getCurrentUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    public static Integer getCurrentUserId() {
        return getCurrentUser().getId();
    }

    public static String getCurrentUsername() {
        return getCurrentUser().getUsername();
    }

    public static String getCurrentRealname() {
        return getCurrentUser().getRealname();
    }


}

package com.it.controller;

/**
 * Created by xieyue on 2016/7/8.
 * UserController
 */


import com.google.common.collect.Maps;
import com.it.dto.DataTablesResult;
import com.it.pojo.Login;
import com.it.pojo.User;
import com.it.service.UserService;
import com.it.utils.ShiroUtil;
import com.it.utils.SmallUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Inject
    private UserService userService;

    // 获取日志界面
    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public String showUserLog() {
        return "setting/log";
    }

    // 获取数据ajax
    @RequestMapping(value = "/log/load", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult loadUserLog(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        List<Login> loginList = userService.queryLoginLog(start, length);
        long total = userService.queryCountByUserid();
        return new DataTablesResult<>(draw, loginList, total, total);
    }

    // 获取修改密码界面
    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String updatePassword() {
        return "setting/password";
    }

    // 验证密码是否正确，用于修改密码remote
    @RequestMapping(value = "/identify")
    @ResponseBody
    public String identityPassword(String password,
                                   @RequestHeader("X-Requested-With") String xRequestWith) {

        if ("XMLHttpRequest".equals(xRequestWith)) {
            String pwd = ShiroUtil.getCurrentUser().getPassword();
            return pwd.equals(password) ? "true" : "false";
        } else {
            throw new RuntimeException("服务器正在忙。。。。");
        }
    }

    // 提交修改密码
    @RequestMapping(value = "/password",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String updatePassword(String password) {
        int n = userService.updateUserPWD(password);
        return SmallUtils.transtoISO(n == 1 ? "修改成功" : "修改失败");
    }


}

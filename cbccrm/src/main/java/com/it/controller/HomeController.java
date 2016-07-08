package com.it.controller;

/**
 * Created by xieyue on 2016/7/7.
 * HomeController
 */


import com.it.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Inject
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPage(String username, String password,
                            RedirectAttributes redirectAttributes,
                            HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            //当前用户已经登录,则先退出之前的账号（选做）
            subject.logout();
        }
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            subject.login(usernamePasswordToken);
            userService.insertLoginLog(request.getRemoteAddr());
            return "redirect:/home";
        } catch (AuthenticationException exception) {
            redirectAttributes.addFlashAttribute("message", "账号或密码错误");
            return "redirect:/";
        }

    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage() {
        return "home";
    }


}

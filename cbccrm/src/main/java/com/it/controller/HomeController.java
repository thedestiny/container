package com.it.controller;

/**
 * Created by xieyue on 2016/7/7.
 * HomeController
 */


import com.it.service.UserService;
import com.it.utils.ShiroUtil;
import com.it.utils.SmallUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    // 获取登录界面
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    // 提交用户信息用户名和密码
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPage(String username, String password,
                            RedirectAttributes redirectAttributes,
                            HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();
        //当前用户已经登录,如果返回登录界面则退出
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            subject.login(usernamePasswordToken);
            String ip = SmallUtils.getRemoteIp(request);
            logger.debug("ip is {}", ip);
            redirectAttributes.addFlashAttribute("user",username);
            userService.insertLoginLog(ip);
            return "redirect:/home";
        } catch (LockedAccountException exception) {
            redirectAttributes.addFlashAttribute("message", "该账户已经被冻结");
            redirectAttributes.addFlashAttribute("style", "alert-danger");
            return "redirect:/login";
        } catch (AuthenticationException exception) {
            redirectAttributes.addFlashAttribute("message", "账号或密码错误");
            redirectAttributes.addFlashAttribute("style", "alert-danger");
            return "redirect:/login";
        }

    }

    // 跳转主界面
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(Model model) {
        model.addAttribute("username", ShiroUtil.getCurrentUsername());
        return "home";
    }
    // 退出系统
    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    public String signOut(RedirectAttributes redirectAttributes) {
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "已经安全退出！");
        redirectAttributes.addFlashAttribute("style", "alert-success");
        return "redirect:/login";
    }


}

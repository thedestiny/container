package com.it.controller;

/**
 * Created by xieyue on 2016/7/4.
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login/{id:\\d+}")
    public String login( @PathVariable Integer id){
        logger.debug(" id is {}",id);
        return "login";
    }


}

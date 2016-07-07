package com.it.controller;

/**
 * Created by xieyue on 2016/7/4.
 */


import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/login")
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String login( MultipartFile file){
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.debug("image:{}" , file.getName());
        logger.debug("File Content Type:{}",file.getContentType());
        logger.debug("File Size:{}",file.getSize());
        logger.debug("File isEmpty:{}",file.isEmpty());
        logger.debug("File Name:{}",file.getOriginalFilename());
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        try {
            IOUtils.copy(file.getInputStream(),new FileOutputStream("G:/"+file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/login";
    }


}

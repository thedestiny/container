package com.qteng.web;


import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;


@WebServlet("/patchca.png")
public class Patchca extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(Patchca.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
        RandomWordFactory factory = new RandomWordFactory();
        factory.setMinLength(6);
        factory.setMaxLength(8);
        cs.setWordFactory(factory);
        OutputStream outputStream = resp.getOutputStream(); // 获取输出流
        String pa = EncoderHelper.getChallangeAndWriteImage(cs, "png", outputStream);
        System.out.println(pa);
        HttpSession session = req.getSession();
        session.setAttribute("patchca", pa);
        logger.info("patchca{}", pa);
        outputStream.flush();
        outputStream.close();


//        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
//        cs.setColorFactory(new SingleColorFactory(new Color(67, 78, 170)));
//        cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
//
//        RandomWordFactory factory = new RandomWordFactory();
//        factory.setMinLength(6);
//        factory.setMaxLength(8);
//        cs.setWordFactory(factory);
//
//        OutputStream outputStream = resp.getOutputStream(); //响应输出流
//        String pa = EncoderHelper.getChallangeAndWriteImage(cs,"png",outputStream);
//
//        //将产生的验证码放入session
//        HttpSession session = req.getSession();
//        session.setAttribute("captcha",pa);
//
//        logger.debug("captcha:{}",pa);
//
//        outputStream.flush();
//        outputStream.close();
    }


}

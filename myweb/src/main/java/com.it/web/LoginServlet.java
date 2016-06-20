package com.it.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xieyue on 2016/6/20.
 * LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("  execute doGet LoginServlet");
        resp.setContentType("text/html;charset=UTF-8");
        resp.addHeader("pragma","no-cache");
        resp.addHeader("cache-control","no-cache");
        resp.addHeader("expires","0");
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }
}

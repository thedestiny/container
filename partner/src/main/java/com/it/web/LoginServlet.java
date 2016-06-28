package com.it.web;

import com.google.gson.Gson;
import com.it.entity.User;
import com.it.servive.LoginService;
import com.it.servive.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xieyue on 2016/6/25.
 * LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(LoginServlet.class);
    private LoginService loginService = new LoginService();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug(" execute LoginServlet doGet ");
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String pwd = req.getParameter("password");
        User user = new User();
        user.setUsername(name);
        user.setPassword(pwd);
        user = loginService.login(user);
        String json = new Gson().toJson(user);
        logger.debug("json is : " + json);
        logger.debug(" execute LoginServlet doPost ");
        // resp.sendRedirect("/issue?json=" + json);
        resp.sendRedirect("/issue");
    }
}

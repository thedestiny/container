package com.it.login;

import com.it.entity.Admin;
import com.it.servers.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * Created by xieyue on 2016/6/15.
 * login controler
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug(" execute login doget method");
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("user");
        String code = req.getParameter("pwd");
        String check = req.getParameter("check");
        LoginService loginService = new LoginService();
        Admin admin = loginService.login(name);
        if(admin!=null && admin.getPwd().equals(code)){
            req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/WEB-INF/views/login.jsp?err=1001").forward(req, resp);

    }
}

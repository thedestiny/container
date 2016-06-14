package com.qteng.web;


import com.qteng.dao.CountDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(Login.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("execute doGet");
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("count");
        String code = req.getParameter("pwd");
        String pat = req.getParameter("pat");
        HttpSession session = req.getSession();
        String patchca = (String) session.getAttribute("patchca");


        if(!pat.equals(patchca)){
            req.setAttribute("err", 1002);
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }
        logger.info("execute doPost");
        CountDao dao = new CountDao();
        if (dao.login(name, code)) {
            logger.info("execute redirect");
            resp.sendRedirect("/success");
        } else {
            req.setAttribute("err", 1001);
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}

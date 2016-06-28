package com.it.web;

import com.it.entity.Register;
import com.it.servive.RegisterService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xieyue on 2016/6/25.
 * RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(RegisterServlet.class);
    private RegisterService registerService = new RegisterService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug(" execute RegisterServlet doGet");
        String name = req.getParameter("username");
        if (StringUtils.isEmpty(name)) {
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        } else {
            boolean flag = registerService.usernameExist(name);
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.print(flag ? "true" : "false");
            out.flush();
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug(" execute RegisterServlet doPost");
        String name = req.getParameter("username");
        String pwd = req.getParameter("password");
        String email = req.getParameter("email");
        int n = registerService.register(new Register(name, email, pwd));
        if(n == 1){
            req.setAttribute("email", email);
            // feedback.jsp 展示一个界面,连接邮箱进行验证。
            req.getRequestDispatcher("WEB-INF/views/feedback.jsp").forward(req, resp);
        } else{
            logger.debug( " 请重新注册！");
            resp.sendRedirect("/register");
        }


    }
}

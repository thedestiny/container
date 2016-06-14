package com.qteng.web;


import com.qteng.entity.Count;
import com.qteng.service.UserService;
import com.qteng.utils.EmailUtil;
import org.joda.time.DateTime;
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

//        if ( !pat.equals(patchca)) {
//            req.setAttribute("err", 1002);
//            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
//            return;
//        }

        logger.info("execute doPost");
        UserService us = new UserService();
        Count count = us.getCount(name);
        if (count != null) {
            if (count.getPwd().equals(code)) {
                logger.info("execute redirect");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // zhuxiaoxue0104@126.com 3131500685@qq.com
                        String email = "1830683298@qq.com";
                        String subject = "email from Liang";
                        String content = "Dear friends! now is" + DateTime.now().toString("yyyy-MM-dd HH:mm:ss") + "tips";
                        EmailUtil.sendEmail(email, subject, content);
                    }
                }).start();
                resp.sendRedirect("/success");
            } else {
                req.setAttribute("err", 1001);
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("err", 1003);
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }


//        CountDao dao = new CountDao();
//        if (dao.login(name, code)) {
//            logger.info("execute redirect");
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    String email = "3131500685@163com";
//                    String subject = "登陆验证提醒";
//                    String content = "word cub is running!";
//                    EmailUtil.sendEmail(email, subject, content);
//                }
//            }).start();
//            resp.sendRedirect("/success");
//        } else {
//            req.setAttribute("err", 1001);
//            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
//        }
    }
}

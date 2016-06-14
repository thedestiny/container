package com.qteng.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by xieyue on 2016/6/13.
 */
@WebServlet("/pay")
public class Pay extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(Pay.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("pay doGet execute");
        // 获取唯一的uuid
        String token = UUID.randomUUID().toString();
        // 获取session
        HttpSession session = req.getSession();
        //存入session
        session.setAttribute("token", token);
        //存入表单
        req.setAttribute("token", token);
        req.getRequestDispatcher("/WEB-INF/views/pay.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String money = req.getParameter("money");
        String token1 = req.getParameter("token");
        String token2 = (String) session.getAttribute("token");
        if (token1.equals(token2)) {
            logger.info("支付金额{},已成功！", money);
            req.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(req, resp);
            session.removeAttribute("token");
        } else {
            resp.sendError(400,"不要重复提交！");
        }

        // resp.sendRedirect("/success");


    }
}

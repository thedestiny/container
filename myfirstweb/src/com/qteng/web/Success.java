package com.qteng.web;

import com.qteng.entity.Count;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/success")
public class Success extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(Success.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("execute success");
        HttpSession session = req.getSession();
        req.setAttribute("user",session.getAttribute("user"));
        req.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(req, resp);
    }
}

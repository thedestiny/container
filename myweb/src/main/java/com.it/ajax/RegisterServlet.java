package com.it.ajax;

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
 * Created by xieyue on 2016/6/23.
 * RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(RegisterServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("username");
        logger.debug(" execute RegisterServlet doGet, and username is {}", name);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        if ("tom".equals(name)) {
            out.print("false");
        } else {
            out.print("true");
        }
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String resume = req.getParameter("resume");
        System.out.println("receive is :" + username + password + resume);


    }
}

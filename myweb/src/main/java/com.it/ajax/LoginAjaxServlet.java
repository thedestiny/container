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
 * Created by xieyue on 2016/6/20.
 * LoginAjaxServlet
 */
@WebServlet("/loginajax")
public class LoginAjaxServlet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(LoginAjaxServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("  execute doGet LoginAjaxServlet");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String tel = req.getParameter("tel");
        logger.debug("the value is {}", name);
        // value = new String(value.getBytes("ISO8859-1"),"UTF-8");
        PrintWriter printWriter = resp.getWriter();
        if ("jim".equals(name)) {
            printWriter.print("false" + name + address + tel);
        } else {
            printWriter.print("true" + name + address + tel);
        }
        printWriter.flush();
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("  execute doPost LoginAjaxServlet");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String tel = req.getParameter("tel");
        logger.debug("the name is {}", name);
        PrintWriter printWriter = resp.getWriter();
        if ("jim".equals(name)) {
            printWriter.print("false" + "post" + name + address + tel);
        } else {
            printWriter.print("true" + "post" + name + address + tel);
        }
        printWriter.flush();
        printWriter.close();
    }
}

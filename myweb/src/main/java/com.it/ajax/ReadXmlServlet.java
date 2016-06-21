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
 * Created by xieyue on 2016/6/21.
 * ReadXmlServlet
 */
@WebServlet("/read.xml")
public class ReadXmlServlet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(ReadXmlServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/xml;charset=utf-8");

        PrintWriter printWriter = resp.getWriter();
        printWriter.print("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        printWriter.print("<users>");
        printWriter.print("<user id=\"1001\"><name>tom</name><age>18</age><nation>USA</nation><hobby>ball</hobby></user>");
        printWriter.print("<user id=\"1001\"><name>tom</name><age>18</age><nation>USA</nation><hobby>ball</hobby></user>");
        printWriter.print("<user id=\"1001\"><name>tom</name><age>18</age><nation>USA</nation><hobby>ball</hobby></user>");
        printWriter.print("<user id=\"1001\"><name>tom</name><age>18</age><nation>USA</nation><hobby>ball</hobby></user>");
        printWriter.print("<user id=\"1001\"><name>tom</name><age>18</age><nation>USA</nation><hobby>ball</hobby></user>");
        printWriter.print("<user id=\"1001\"><name>tom</name><age>18</age><nation>USA</nation><hobby>ball</hobby></user>");
        printWriter.print("</users>");
        printWriter.flush();
        printWriter.close();
    }
}

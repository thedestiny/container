package com.it.ajax;

import com.it.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xieyue on 2016/6/21.
 * TransServlet
 */
@WebServlet("/trans")
public class TransServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String word = req.getParameter("q");
        String url = "http://fanyi.youdao.com/openapi.do?keyfrom=kaishengit&" +
                "key=1587754017&type=data&doctype=xml&version=1.1&q=" + word;
        String xml = HttpUtils.getText(url);
        System.out.println(url);
        resp.setContentType("text/xml;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(xml);
        printWriter.flush();
        printWriter.close();




    }
}

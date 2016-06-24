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
 * Created by xieyue on 2016/6/23.
 * WordJsonServlet
 */
@WebServlet("/wordjson")
public class WordJsonServlet  extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "http://fanyi.youdao.com/openapi.do?keyfrom=kaishengit&" +
                "key=1587754017&type=data&doctype=json&version=1.1";
        String q = req.getParameter("q");
        url = url+ "&q=" + q;
        String json = HttpUtils.getText(url);
        // resp.setContentType("text/xml;charset=utf-8");
        // resp.setContentType("text/html;charset=utf-8");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }
}

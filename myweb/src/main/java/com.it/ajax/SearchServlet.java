package com.it.ajax;

import com.it.utils.HttpUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xieyue on 2016/6/22.
 * SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getParameter("url");
        resp.setContentType("text/xml;charset=utf-8");
        if(StringUtils.isEmpty(url)){
            url = "http://blog.sina.com.cn/rss/1220218113.xml";
        }
        PrintWriter printWriter = resp.getWriter();
        String xml = HttpUtils.getText(url);
        // System.out.println(xml);
        printWriter.print(xml);
        printWriter.flush();
        printWriter.close();

    }
}

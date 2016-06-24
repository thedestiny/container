package com.it.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * Created by xieyue on 2016/6/24.
 * UploadServlet
 */
@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Part part = req.getPart("file");
        String filename = getFilename(part);
        System.out.println(filename);

    }

    private String getFilename(Part part) {
        String content = part.getHeader("Content-Disposition");
        System.out.println("content is : " + content);
        // 获取最后一个引号
        int n = content.lastIndexOf("\"");
        // 获取最后一个等号
        int m = content.lastIndexOf("=");
        String[] con = content.split("\"");
        for (int i = 0; i < con.length; i++) {
            System.out.println(i + "  " + con[i]);
        }
        System.out.println(con[con.length - 2]);
        return content.substring(m + 2, n);
    }
}

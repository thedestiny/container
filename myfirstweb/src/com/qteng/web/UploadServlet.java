package com.qteng.web;

import com.qteng.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by xieyue on 2016/6/16.
 * UploadServlet
 */
@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(UploadServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("execute upload doGet");
        req.getRequestDispatcher("/WEB-INF/views/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // String filetype = part.getContentType();
        req.setCharacterEncoding("utf-8");
        String desc = req.getParameter("desc");
        Part part = req.getPart("file");
        InputStream inputStream = part.getInputStream();
        String fileName = getFilename(part);
        long fileSize = part.getSize();
        DocumentService documentDao = new DocumentService();
        documentDao.upload(fileName, fileSize, inputStream);
    }

    private String getFilename(Part part) {
        // Collection<String> conten = part.getHeaders("Content-Disposition");
        String content = part.getHeader("Content-Disposition");
        // 获取最后一个引号
        int n = content.lastIndexOf("\"");
        // 获取最后一个等号
        int m = content.lastIndexOf("=");
        String[] con = content.split("\"", 10);
        for (int i = 0; i < con.length; i++) {
            System.out.println(i + "  " + con[i]);
        }
        System.out.println(con[con.length - 2]);
        return content.substring(m + 2, n);
    }
}

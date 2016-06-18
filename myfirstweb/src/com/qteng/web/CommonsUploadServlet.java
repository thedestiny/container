package com.qteng.web;

import com.qteng.service.DocumentService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by xieyue on 2016/6/17.
 * CommonsUploadServlet
 */
@WebServlet("/uploadcommons")
public class CommonsUploadServlet extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(CommonsUploadServlet.class);
    private DocumentService documentService = new DocumentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/uploadcommons.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<FileItem> list = null;
        try {
            list = upLoad(req);
        } catch (FileUploadException e) {
            throw new RuntimeException("获取List<FileItem>异常", e);
        }
        for (FileItem fileItem : list) {
            // 是否为普通表单元素
            if (fileItem.isFormField()) {
                // 获取表单属性名
                String fileName = fileItem.getFieldName();
                if ("desc".equals(fileName)) {
                    // 以utf-8 形式获取表单的值
                    String value = fileItem.getString("utf-8");
                    logger.debug("fileName: {},value {}", fileName, value);
                }
            } else {
                // 获取文件名称
                String fileName = fileItem.getName();
                // 获取文件大小
                long size = fileItem.getSize();
                // 文件类型
                String contentType = fileItem.getContentType();
                System.out.println(fileName + " dfgdsfgdfgdf" + contentType);
                logger.debug("fileName :{}  contentType {}", fileName, contentType);
                documentService.upload(fileName, size, fileItem.getInputStream());
            }
        }
        resp.sendRedirect("/download");
    }

    public List<FileItem> upLoad(HttpServletRequest request) throws FileUploadException {

        if (ServletFileUpload.isMultipartContent(request)) {
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            ServletContext servletContext = getServletContext();
            // 临时文件夹 tomact temple file  并设置文件上传临时路径和缓冲区
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            diskFileItemFactory.setRepository(repository);
            diskFileItemFactory.setSizeThreshold(8192);
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            return servletFileUpload.parseRequest(request);
        }
        return null;
    }
}
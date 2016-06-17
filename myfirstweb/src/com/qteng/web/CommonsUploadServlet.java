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
 *
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


        if(ServletFileUpload.isMultipartContent(req)){
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            ServletContext servletContext = getServletContext();
            // 临时文件夹 tomact temple file  并设置文件上传临时路径和缓冲区
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            diskFileItemFactory.setRepository(repository);
            diskFileItemFactory.setSizeThreshold(8192);
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            // 获取文件的list集合
            try {
                List<FileItem> list = servletFileUpload.parseRequest(req);
                for (FileItem fileItem : list) {
                    // 是否为普通表单元素
                    if(fileItem.isFormField()){
                        // 获取表单属性值
                        String fileName = fileItem.getFieldName();
                        if("desc".equals(fileName)) {
                            String value =fileItem.getString("utf-8");
                            logger.debug("fileName: {},value {}",fileName,value);
                        }
                    }else{
                        String fileName = fileItem.getName();
                        long size = fileItem.getSize();
                        String contentType = fileItem.getContentType();
                        documentService.upload(fileName,size,fileItem.getInputStream());
                    }
                }

                resp.sendRedirect("/download");
            } catch (FileUploadException e) {
                e.printStackTrace();
            }


        }



    }
}

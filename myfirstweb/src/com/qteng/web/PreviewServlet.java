package com.qteng.web;

import com.qteng.entity.Document;
import com.qteng.service.DocumentService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by xieyue on 2016/6/16.
 * PreviewServlet
 */
@WebServlet("/preview")
public class PreviewServlet extends HttpServlet{

    Logger logger = LoggerFactory.getLogger(PreviewServlet.class);
    DocumentService documentService = new DocumentService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("execute documentService doGet");
        String md5 = req.getParameter("file");
        String down = req.getParameter("down");
        if (md5 == null || "".equals(md5)) {
            logger.debug("PreviewServlet  md5值为空");
            resp.sendError(404,"md5为空");
            return;
        }
        Document document = documentService.findFile(md5);
        if (document == null ){
            logger.debug("PreviewServlet document值为空");
            resp.sendError(404,"未找到文件");
            return;
        }
        String saveName = document.getSavename();
        File file = new File("E:/upload",saveName);
        if(file.exists()){
            // 判断是否下载
            if("down".equals(down)){
                logger.debug(" PreviewServlet 下载文件");
                //设置文件类型，弹出对话框
                resp.setContentType("application/octet-stream");
                //设置对话框的文件名
                String fileName = new String(document.getFilename().getBytes("utf-8"),"ISO8859-1");
                resp.addHeader("Content-Disposition","attachment;filename=\""+fileName+"\"");
                // 设置文件大小，便于浏览器显示进度
                resp.setContentLength((int) document.getSize());
                return;
            }
            // 响应预览
            logger.debug(" PreviewServlet 预览文件");
            FileInputStream fileInputStream = new FileInputStream(file);
            // resp.getOutputStream() 调用响应输出流，输出到网页
            OutputStream outputStream = resp.getOutputStream();
            IOUtils.copy(fileInputStream,outputStream);
            outputStream.flush();
            outputStream.close();
            fileInputStream.close();
        } else {
            resp.sendError(404,"未找到文件");
        }
    }
}

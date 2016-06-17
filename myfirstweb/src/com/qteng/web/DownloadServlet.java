package com.qteng.web;

import com.qteng.entity.Document;
import com.qteng.service.DocumentService;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xieyue on 2016/6/16.
 */
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(DownloadServlet.class);
    DocumentService documentService = new DocumentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("execute DownloadServlet doGet");
        req.setAttribute("documentlist", documentService.getAllDocument());
        req.getRequestDispatcher("/WEB-INF/views/download.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("execute DownloadServlet doPost");



    }
}

package com.it.web;

import com.it.servive.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xieyue on 2016/6/25.
 * IssueQueServlet
 */
@WebServlet("issue/que")
public class IssueQueServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(IssueQueServlet.class);
    private IssueService issueService = new IssueService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String content = req.getParameter("content");

    }
}

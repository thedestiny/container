package com.it.web;

import com.it.entity.Issue;
import com.it.servive.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by xieyue on 2016/6/26.
 * IssueServlet
 */
@WebServlet("/issue")
public class IssueServlet extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(IssueServlet.class);
    private IssueService issueService = new IssueService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Issue> issueList = issueService.getAllIssue();
        req.setAttribute("issueList",issueList);
        logger.debug(" execute IssueServlet doGet ");
        req.getRequestDispatcher("/WEB-INF/views/issue.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String question = req.getParameter("question");
        logger.debug(" username is :" + username + " question is : " + question);
        boolean flag = issueService.dealQue(username, question);
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html;charset=utf-8");
        if(flag){
            out.print("true");
        } else{
            out.print("false");
        }
        out.flush();
        out.close();

    }
}

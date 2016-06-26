package com.it.web;

import com.it.entity.Answer;
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
 * Created by xieyue on 2016/6/25.
 * IssueAnsServlet
 */
@WebServlet("/issue/ans")
public class IssueAnsServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(IssueAnsServlet.class);
    private IssueService issueService = new IssueService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String question = req.getParameter("question");
        Issue issue = issueService.findIssue(question);
        List<Answer> answerList = issueService.findAllAnswer(question);
        req.setAttribute("issue", issue);
        req.setAttribute("answerList", answerList);
        req.getRequestDispatcher("/WEB-INF/views/answer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String question = req.getParameter("question");
        String username = req.getParameter("username");
        String answer = req.getParameter("answer");
        logger.debug(" username is :" + username + " question is : " + answer);
        boolean flag = issueService.dealAns(question, username, answer);
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html;charset=utf-8");
        if (flag) {
            out.print("true");
        } else {
            out.print("false");
        }
        out.flush();
        out.close();


    }
}

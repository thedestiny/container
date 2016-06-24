package com.it.web;

import com.google.gson.Gson;
import com.it.entity.Message;
import com.it.service.MessageService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by xieyue on 2016/6/24.
 * MseeageServlet
 */
@WebServlet("/message")
public class MseeageServlet extends HttpServlet{

    private MessageService service = new MessageService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Message> messageList = service.show();
        req.setAttribute("messageList",messageList);
        req.setAttribute("idMax",messageList.get(0).getId());
        req.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(StringUtils.isEmpty(id)){
            return;
        }
        List<Message> list= service.update(Integer.parseInt(id));
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.print(new Gson().toJson(list));
        out.flush();
        out.close();
    }
}

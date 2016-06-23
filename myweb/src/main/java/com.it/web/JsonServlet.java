package com.it.web;

import com.google.gson.Gson;
import com.it.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by xieyue on 2016/6/23.
 * JsonServlet
 */
@WebServlet("/json")
public class JsonServlet extends HomeServlet {

    private User user = new User(1,"jin","ball","math");
    private User user1 = new User(2,"flash","ball","sport");
    private User user2 = new User(3,"arrow","ball","politic");
    private User user3 = new User(4,"queen","ball","science");



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        String json = new Gson().toJson(list);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(json);
        printWriter.flush();
        printWriter.close();
    }
}

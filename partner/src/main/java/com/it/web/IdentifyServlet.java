package com.it.web;

import com.it.dao.UserDao;
import com.it.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xieyue on 2016/6/25.
 * IdentifyServlet
 */
@WebServlet("/identify")
public class IdentifyServlet extends HttpServlet {

    private UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("username");
        String pwd = req.getParameter("pwd");
        String md5pwd = DigestUtils.md5Hex(pwd);
        String email = req.getParameter("email");
        String time = req.getParameter("time");
        String timeNow = new DateTime().toString("MM-dd HH:mm:ss");
        // num 为正 则在12小时内，否则失效
        int num = time.compareTo(timeNow);
        if (num > 0) {
            User user = new User(name, email, pwd, md5pwd, timeNow);
            userDao.insert(user);
        } else {

        }
        // 此步认证成功，进行注册，将数据移至user中。detail.jsp 是记录用户的详细信息，可以跳过。
        req.getRequestDispatcher("WEB-INF/views/detail.jsp").forward(req, resp);

    }
}

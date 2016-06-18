package com.it.web;

import com.it.entity.Movie;
import com.it.service.HomeService;
import com.it.service.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by xieyue on 2016/6/17.
 * HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(HomeServlet.class);
    private HomeService homeService = new HomeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("execute doGet in HomeServlet");
        String startNum = req.getParameter("page");
        String search = req.getParameter("search");

        System.out.println(search);
        int p = 1;
        if (StringUtils.isNumeric(startNum)) {
            p = Integer.parseInt(startNum);

        }
        Page<Movie> movie = homeService.showFilm(p, search);
        req.setAttribute("movie", movie);
        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);

    }
}

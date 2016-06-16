package com.it.book;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.dao.BookDAO;
import com.it.entity.Book;
import com.it.helper.BRMsuper;

/**
 * Servlet implementation class BookAdd
 */
@WebServlet("/BookAdd")
public class BookAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookDAO bdao = new BookDAO();
	Book book = new Book();
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/views/bookadd.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		request.setCharacterEncoding("utf-8");
		BRMsuper<Book> brm = new BRMsuper<>(Book.class,request,response);
		try {
			book = brm.build();
			System.out.println(book.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		int num = new Integer(request.getParameter("num"));
		int n = bdao.addbook(book, num);
		if (n > 0) {
			//response.sendRedirect("/bookshow");
			brm.fixon("/bookshow");
		} else {
			request.getRequestDispatcher("/WEB-INF/views/bookadd.jsp?err=1001").forward(request, response);
		}
	}
}

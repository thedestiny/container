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
 * Servlet implementation class BookEdit
 */
@WebServlet("/BookEdit")
public class BookEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookEdit() {
        super();
        // TODO Auto-generated constructor stub
    }
    BookDAO bdao = new BookDAO();
	Book book = new Book();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
	    String code = request.getParameter("id");
		// BookDAO bdao = new BookDAO();
		// Book book = new Book();
		book = bdao.find(code);
		request.setAttribute("book", book);
		request.getRequestDispatcher("/WEB-INF/views/bookedit.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		BRMsuper<Book> brm = new BRMsuper<>(Book.class,request,response);
		request.setCharacterEncoding("utf-8");
		try {
			book = brm.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// request.setCharacterEncoding("utf-8");
//		book.setCode(request.getParameter("code"));
//		book.setTitle(request.getParameter("title"));
//		book.setAuthor(request.getParameter("author"));
//		book.setPress(request.getParameter("press"));
//		book.setStation(request.getParameter("station"));
		//System.out.println(book.getCode());
		//System.out.println(bdao.revise(book));
		if (bdao.revise(book)) {
			response.sendRedirect("/bookshow");
		} else {
			response.sendError(400, "�༭ʧ��");
		}
		
		
	}

}

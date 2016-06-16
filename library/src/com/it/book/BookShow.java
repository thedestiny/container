package com.it.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.dao.BookDAO;


/**
 * Servlet implementation class Book
 */
@WebServlet("/Book")
public class BookShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookShow() {
        super();
        // TODO Auto-generated constructor stub
    }
    BookDAO  bdao = new BookDAO(); 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// BookDAO  bdao = new BookDAO(); 
		String search = request.getParameter("search");
		if(search == null){
			search = "";
			request.setAttribute("list",bdao.showall());
		}  else {
			search = new String(search.getBytes("ISO-8859-1"),"utf-8");
			request.setAttribute("list",bdao.showall(search));
		}
		request.getRequestDispatcher("/WEB-INF/views/bookshow.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
	}

}

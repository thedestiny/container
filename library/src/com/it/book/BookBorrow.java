package com.it.book;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.dao.BookDAO;
import com.it.dao.RecordDAO;
import com.it.entity.Book;
import com.it.servers.BorrowService;

/**
 * Servlet implementation class BookBorrow
 */
@WebServlet("/BookBorrow")
public class BookBorrow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookBorrow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    BookDAO bdao = new BookDAO();
	Book book = new Book();
	RecordDAO dao = new RecordDAO();
	BorrowService bs =  new BorrowService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    String code = request.getParameter("id");
	    if(code == null) {
	    	request.getRequestDispatcher("/WEB-INF/views/bookborrow.jsp").forward(request, response);	
	    } else {
	    	 book = bdao.find(code);
	 		request.setAttribute("book", book);
	 		if(book.getStation().equals("�ڿ�")){
	 			request.getRequestDispatcher("/WEB-INF/views/bookborrow.jsp").forward(request, response);	
	 		} else {
	 			response.sendRedirect("/bookshow");
	 		}
	    }
	   
	    	
	   
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String ccode = request.getParameter("ccode");
		String bcode = request.getParameter("code");
		String msg = bs.borrowbook(bcode, ccode);
		// dao.insert(bcode, ccode)
		if(msg.equals("bs")){
			System.out.println("����ɹ���");
			// �鿴��ǰ���Ž������
			response.sendRedirect("/inforcard?infor=" + ccode + "&result=bs");
		} else{
			response.sendRedirect("/inforcard?infor=" + ccode + "&result=bf");
		}	
	}

}

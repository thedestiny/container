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
 * Servlet implementation class BookReturn
 */
@WebServlet("/BookReturn")
public class BookReturn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookReturn() {
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
	    String bcode = request.getParameter("bcode");
	    // String ccode = request.getParameter("ccode");
	    // Boolean bool = dao.update(bcode, ccode);
//	    String msg = bs.returnbook(bcode, ccode);
//	    if(msg.equals("rs")){
//	    	response.sendRedirect("/inforcard?infor=" + ccode + "&result=rs");
//	    } else {
//	    	response.sendRedirect("/inforcard?infor=" + ccode + "&result=rf");
//	    } 
	    String msgs = bs.returnbook(bcode);
	    String[] arr = msgs.split(",");
	    String msg = arr[0];
	    String ccode = arr[1];
	    response.sendRedirect("/inforcard?infor=" + ccode + "&result=" + msg);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

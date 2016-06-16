package com.it.record;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.dao.RealationDAO;
import com.it.entity.Realation;

/**
 * Servlet implementation class InforCard
 */
@WebServlet("/InforCard")
public class InforCard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InforCard() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// BookDAO bdao = new BookDAO();
	// Book book = new Book();
	// RecordDAO dao = new RecordDAO();
	// CardDAO cdao = new CardDAO();
	RealationDAO dao = new RealationDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String result = request.getParameter("result");
		String infor = request.getParameter("infor");
		request.setAttribute("result", result);
		if (infor != null) {
			List<Realation> list = dao.findccode(infor);
			request.setAttribute("user", list.get(0).getUser());
			request.setAttribute("ccode", list.get(0).getCcode());
			System.out.println("123456" + infor);
			request.setAttribute("list", dao.findccode(infor));
			request.getRequestDispatcher("WEB-INF/views/inforcard.jsp").forward(request, response);
		} else {
			request.setAttribute("list", dao.findccode());
			request.getRequestDispatcher("WEB-INF/views/inforcard.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
	}

}

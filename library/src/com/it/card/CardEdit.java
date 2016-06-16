package com.it.card;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.dao.CardDAO;
import com.it.entity.Card;
import com.it.helper.BRMsuper;

/**
 * Servlet implementation class CardEdit
 */
@WebServlet("/CardEdit")
public class CardEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CardEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    CardDAO dao = new CardDAO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String id = request.getParameter("id");
		Card card = dao.find(id);
		request.setAttribute("card", card);
		request.getRequestDispatcher("/WEB-INF/views/cardedit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // doGet(request, response);
		request.setCharacterEncoding("utf-8");
		Card card = new Card();
		BRMsuper<Card> brm = new BRMsuper<>(Card.class,request,response);
		try {
			card = brm.build();
			// System.out.println(card);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		request.setCharacterEncoding("utf-8");
//		String code = request.getParameter("code");
//		String user = request.getParameter("user");
//		String tel = request.getParameter("tel");
//		String ctime = request.getParameter("ctime");
//		String count = request.getParameter("count");
//		card.setCode(code);
//		card.setUser(user);
//		card.setTel(tel);
//		card.setCtime(Integer.parseInt(ctime));
//		card.setCount(Integer.parseInt(count));
		if(dao.update(card)){
			response.sendRedirect("/cardhome");
		} else {
			response.sendError(1001, "����ʧ�ܣ�");
		}
		
		
		
		
		
		
		
		
		
	}

}

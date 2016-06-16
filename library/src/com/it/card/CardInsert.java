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
 * Servlet implementation class CardInsert
 */
@WebServlet("/CardInsert")
public class CardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CardInsert() {
        super();
        // TODO Auto-generated constructor stub
    }
    CardDAO dao = new CardDAO();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/views/cardinsert.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("utf-8");
		Card card = new Card();
		BRMsuper<Card> brm = new BRMsuper<>(Card.class,request,response);
		try {
			card = brm.build();
			// System.out.println(card);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(dao.insert(card)){
			response.sendRedirect("/cardhome");
		} else{
			request.getRequestDispatcher("/WEB-INF/views/cardinsert.jsp��err=1003").forward(request, response);
		}
		
		
		
	}

}

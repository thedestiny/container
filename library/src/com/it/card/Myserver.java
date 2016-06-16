package com.it.card;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Myserver extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		// req.setCharacterEncoding("utf-8");
		System.out.println(id);
		PrintWriter out = resp.getWriter();
		out.println("<h1>jim is here!</h1>");
		out.println("<h1>����!</h1>");
		out.println("<p>" +id +"</p>");
		// System.out.println("do get!");
		out.flush();
		out.close();
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("server start!");
		super.service(arg0, arg1);
		System.out.println("server end!");
	}

	@Override
	public void destroy() {
		System.out.println("�����ߣ�");
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		System.out.println("��ʼ���С�������");
//		String name = getInitParameter("name");
//		String code = getInitParameter("code");
//		System.out.println("��ʼֵ:" +name+ ":  " + code);
		Enumeration<String> en = getInitParameterNames();
		while(en.hasMoreElements()){
			String name = en.nextElement();
			System.out.println(name + "  :  " + getInitParameter(name));
		}
	}
	public Myserver() {
		System.out.println("���췽�������У�");
	}
	
	

	
	
	
	
	
	

}

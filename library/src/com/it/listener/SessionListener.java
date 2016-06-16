package com.it.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener {

	/**
	 * Default constructor.
	 */
	public SessionListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent arg0) {
		ServletContext apply = arg0.getSession().getServletContext();
		Object count = apply.getAttribute("count");
		// System.out.println("apply" + apply.getAttribute("count"));
		if (count == null) {
			apply.setAttribute("count", 1);
			System.out.println("apply" + apply.getAttribute("count"));
		} else {
			apply.setAttribute("count", (Integer.parseInt(count.toString()) + 1));
		}
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent arg0) {
		ServletContext apply = arg0.getSession().getServletContext();
		Object count = apply.getAttribute("count");
		apply.setAttribute("count", (Integer.parseInt(count.toString()) - 1));
	}

}

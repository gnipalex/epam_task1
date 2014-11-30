package com.epam.hnyp.shop.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.epam.hnyp.shop.cart.Cart;


public class SessionListener implements HttpSessionListener {
	private static final Logger LOG = Logger.getLogger(SessionListener.class);
	
	public static final String SESSION_CART_KEY = "SESSION_CART";
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		Cart cart = new Cart();
		HttpSession session = arg0.getSession();
		session.setAttribute(SESSION_CART_KEY, cart);
		if (LOG.isInfoEnabled()) {
			LOG.info("new cart added to session " + session.getId());
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

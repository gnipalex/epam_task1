package com.epam.hnyp.shop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.hnyp.shop.model.User;
import com.epam.hnyp.shop.servlet.LoginServlet;

public class MakingOrderFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpSession session = httpReq.getSession();
		User user = (User) session
				.getAttribute(LoginServlet.SESSION_AUT_USER_KEY);
		if (user == null) {
			HttpServletResponse httpResp = (HttpServletResponse) response;
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(httpReq.getServletContext().getContextPath())
					.append("/login?");
			strBuilder.append(LoginServlet.LOGIN_FORM_URL_REFERRER_PARAM)
					.append("=").append(httpReq.getRequestURI());
			httpResp.sendRedirect(strBuilder.toString());
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
	
	public void destroy() {

	}
}

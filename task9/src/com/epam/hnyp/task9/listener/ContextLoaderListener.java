package com.epam.hnyp.task9.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.epam.hnyp.task9.repo.UserRepo;
import com.epam.hnyp.task9.repo.impl.UserInMemoryRepo;
import com.epam.hnyp.task9.repo.impl.UsersInitializer;
import com.epam.hnyp.task9.service.AbstractCapchaService;
import com.epam.hnyp.task9.service.CapchaUidService;
import com.epam.hnyp.task9.service.UserService;
import com.epam.hnyp.task9.service.impl.AppContextCapchaService;
import com.epam.hnyp.task9.service.impl.CookieCapchaUidService;
import com.epam.hnyp.task9.service.impl.HiddenCapchaUidService;
import com.epam.hnyp.task9.service.impl.SessionCapchaService;
import com.epam.hnyp.task9.service.impl.UserServiceImpl;

public class ContextLoaderListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent arg0) {
    	ServletContext context = arg0.getServletContext();
    	String capchaUidParamName = context.getInitParameter("capchaUidParamName");
    	String capchaClientMode = context.getInitParameter("capchaClientMode");
    	String capchaServerMode = context.getInitParameter("capchaServerMode");
    	String capchaTTLStr = context.getInitParameter("capchaTTL");
    	int capchaTTL = Integer.parseInt(capchaTTLStr);
    	String capchaLengthStr = context.getInitParameter("capchaLength");
    	int capchaLength = Integer.parseInt(capchaLengthStr);
    	
    	CapchaUidService capchaUidService = null;
    	switch(capchaClientMode){
    	case "cookie":
    		capchaUidService = new CookieCapchaUidService(capchaUidParamName);
    		break;
    	case "hidden":
    		capchaUidService = new HiddenCapchaUidService(capchaUidParamName);
    		break;
    		default:
    			//error terminate
    	}
    	context.setAttribute("init:capchaUidService", capchaUidService);
    	
    	AbstractCapchaService capchaService = null;
    	switch(capchaServerMode) {
    	case "session":
    		capchaService = new SessionCapchaService(capchaTTL);
    		break;
    	case "appContext":
    		capchaService = new AppContextCapchaService(capchaTTL);
    		break;
    		default:
    			//error terminate
    	}
    	context.setAttribute("init:capchaService", capchaService);
    	context.setAttribute("init:capchaLength", capchaLength);
    	
    	UserRepo userRepo = new UserInMemoryRepo();
    	UsersInitializer.initUsers(userRepo);
    	UserService userService = new UserServiceImpl(userRepo);
    	context.setAttribute("init:userService", userService);
    }

    public void contextDestroyed(ServletContextEvent arg0) {

    }
	
}

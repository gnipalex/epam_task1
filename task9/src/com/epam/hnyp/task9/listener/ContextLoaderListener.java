package com.epam.hnyp.task9.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.epam.hnyp.task9.Constants;
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
import com.sun.corba.se.impl.orbutil.closure.Constant;

public class ContextLoaderListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent arg0) {
    	ServletContext context = arg0.getServletContext();
    	String capchaClientMode = context.getInitParameter("capchaClientMode");
    	String capchaServerMode = context.getInitParameter("capchaServerMode");
    	String capchaTTLStr = context.getInitParameter("capchaTTL");
    	int capchaTTL = Integer.parseInt(capchaTTLStr);
    	String capchaLengthStr = context.getInitParameter("capchaLength");
    	int capchaLength = Integer.parseInt(capchaLengthStr);
    	
    	CapchaUidService capchaUidService = null;
    	switch(capchaClientMode){
    	case "cookie":
    		capchaUidService = new CookieCapchaUidService(Constants.CAPCHA_UID_PARAM_NAME);
    		break;
    	case "hidden":
    		capchaUidService = new HiddenCapchaUidService(Constants.CAPCHA_UID_PARAM_NAME);
    		break;
    		default:
    			throw new RuntimeException("Client capcha uid store mod not specified. Use context param 'capchaClientMode' = 'cookie' | 'hidden'");
    	}
    	context.setAttribute(Constants.INIT_CAPCHAUID_SERVICE_KEY, capchaUidService);
    	
    	AbstractCapchaService capchaService = null;
    	switch(capchaServerMode) {
    	case "session":
    		capchaService = new SessionCapchaService(capchaTTL);
    		break;
    	case "appContext":
    		capchaService = new AppContextCapchaService(capchaTTL);
    		break;
    		default:
    			throw new RuntimeException("Server capcha uid store mod not specified. Use context param 'capchaServerMode' = 'session' | 'appContext'");
    	}
    	context.setAttribute(Constants.INIT_CAPCHA_SERVICE_KEY, capchaService);
    	context.setAttribute(Constants.INIT_CAPCHA_LENGTH_KEY, capchaLength);
    	
    	UserRepo userRepo = new UserInMemoryRepo();
    	UsersInitializer.initUsers(userRepo);
    	UserService userService = new UserServiceImpl(userRepo);
    	context.setAttribute(Constants.INIT_USER_SERVICE_KEY, userService);
    }

    public void contextDestroyed(ServletContextEvent arg0) {

    }
	
}

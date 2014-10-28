package com.epam.hnyp.task9.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.epam.hnyp.task9.Constants;
import com.epam.hnyp.task9.capcha.provider.AbstractCapchaProvider;
import com.epam.hnyp.task9.capcha.provider.CookieCapchaProvider;
import com.epam.hnyp.task9.capcha.provider.HiddenCapchaProvider;
import com.epam.hnyp.task9.capcha.provider.SessionCapchaProvider;
import com.epam.hnyp.task9.repo.UserRepo;
import com.epam.hnyp.task9.repo.impl.UserInMemoryRepo;
import com.epam.hnyp.task9.repo.impl.UsersInitializer;
import com.epam.hnyp.task9.service.UserService;
import com.epam.hnyp.task9.service.impl.UserServiceImpl;

public class ContextInitializer implements ServletContextListener {
	
	public static final String INIT_CAPCHA_PROVIDER_KEY = "init:capchaProvider";
	public static final String INIT_USER_SERVICE_KEY = "init:userService";
	
    public void contextInitialized(ServletContextEvent arg0) {
    	ServletContext context = arg0.getServletContext();
    	String capchaServerMode = context.getInitParameter("capchaServerMode");
    	String capchaTTLStr = context.getInitParameter("capchaTTL");
    	int capchaTTL = Integer.parseInt(capchaTTLStr);
    	
    	AbstractCapchaProvider capchaProvider = null;
    	switch(capchaServerMode) {
    	case "session":
    		capchaProvider = new SessionCapchaProvider(capchaTTL);
    		break;
    	case "cookie":
    		capchaProvider = new CookieCapchaProvider(capchaTTL);
    		break;
    	case "hidden":
    		capchaProvider = new HiddenCapchaProvider(capchaTTL);
    		break;
    		default:
    			throw new RuntimeException("Server capcha uid store mod not specified. Use context param 'capchaServerMode' = 'session' | 'appContext'");
    	}
    	context.setAttribute(INIT_CAPCHA_PROVIDER_KEY, capchaProvider);
    	
    	UserRepo userRepo = new UserInMemoryRepo();
    	UsersInitializer.initUsers(userRepo);
    	UserService userService = new UserServiceImpl(userRepo);
    	context.setAttribute(INIT_USER_SERVICE_KEY, userService);
    }

    public void contextDestroyed(ServletContextEvent arg0) {

    }
	
}

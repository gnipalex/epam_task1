package com.epam.hnyp.task9.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.epam.hnyp.task9.capcha.provider.AbstractCapchaProvider;
import com.epam.hnyp.task9.capcha.provider.CookieCapchaProvider;
import com.epam.hnyp.task9.capcha.provider.HiddenCapchaProvider;
import com.epam.hnyp.task9.capcha.provider.SessionCapchaProvider;
import com.epam.hnyp.task9.repo.UserRepo;
import com.epam.hnyp.task9.repo.impl.UserInMemoryRepo;
import com.epam.hnyp.task9.repo.impl.UsersInitializer;
import com.epam.hnyp.task9.service.UserService;
import com.epam.hnyp.task9.service.impl.UserServiceInMemory;
import com.epam.hnyp.task9.util.ConversationScopeFactory;
import com.epam.hnyp.task9.util.SessionConversationScopeFactory;

public class ContextInitializer implements ServletContextListener {
	private static final Logger LOG = Logger.getLogger(ContextInitializer.class);
	
	public static final String INIT_CAPCHA_PROVIDER_KEY = "init:capchaProvider";
	public static final String INIT_USER_SERVICE_KEY = "init:userService";
	public static final String INIT_CONVERSATION_SCOPE_FACTORY_KEY = "init:conversationScopeFactory";
	
    public void contextInitialized(ServletContextEvent arg0) {
    	try {
    		init(arg0);
    	} catch (Exception e) {
    		LOG.error(e);
    		throw e;
    	}
    }
    
    private void init(ServletContextEvent arg0) {
    	ServletContext context = arg0.getServletContext();
    	String capchaServerMode = context.getInitParameter("capchaServerMode");
    	String capchaTTLStr = context.getInitParameter("capchaTTL");
    	int capchaTTL = Integer.parseInt(capchaTTLStr);
    	
    	if (LOG.isInfoEnabled()) {
    		LOG.info("capcha TTL = " + capchaTTL);
    	}
    	
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
    			throw new RuntimeException("Server capcha uid store mod not specified. Use context param 'capchaServerMode' = 'session' | 'cookie' | 'hidden'");
    	}
    	context.setAttribute(INIT_CAPCHA_PROVIDER_KEY, capchaProvider);
    	
    	if (LOG.isInfoEnabled()) {
    		LOG.info("capcha provider = " + capchaProvider.getClass().getName());
    	}
    	
    	UserRepo userRepo = new UserInMemoryRepo();
    	UsersInitializer.initUsers(userRepo);
    	UserService userService = new UserServiceInMemory(userRepo);
    	context.setAttribute(INIT_USER_SERVICE_KEY, userService);
    	
    	if (LOG.isInfoEnabled()) {
    		LOG.info("userService initialized");
    	}
    	
    	ConversationScopeFactory conversationScopeFactory = new SessionConversationScopeFactory();
    	context.setAttribute(INIT_CONVERSATION_SCOPE_FACTORY_KEY, conversationScopeFactory);
    	
    	if (LOG.isInfoEnabled()) {
    		LOG.info("conversation scope factory initialized = " + conversationScopeFactory.getClass().getName());
    	}
    }

    public void contextDestroyed(ServletContextEvent arg0) {

    }
	
}

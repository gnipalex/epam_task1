package com.epam.hnyp.task9.listener;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.epam.hnyp.task9.capcha.provider.AbstractCapchaProvider;
import com.epam.hnyp.task9.capcha.provider.CookieCapchaProvider;
import com.epam.hnyp.task9.capcha.provider.HiddenCapchaProvider;
import com.epam.hnyp.task9.capcha.provider.SessionCapchaProvider;
import com.epam.hnyp.task9.dao.UserDao;
import com.epam.hnyp.task9.dao.mysql.UserDaoMySql;
import com.epam.hnyp.task9.service.UserService;
import com.epam.hnyp.task9.service4dao.TransactionManager;
import com.epam.hnyp.task9.service4dao.UserDaoService;
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
    	
//    	UserRepo userRepo = new UserInMemoryRepo();
//    	UsersInitializer.initUsers(userRepo);
//    	UserService userService = new UserServiceInMemory(userRepo);
    	
    	DataSource dataSource = initDataSource();
    	TransactionManager tranManager = new TransactionManager(dataSource);
    	
    	UserDao userDao = new UserDaoMySql();
    	UserService userService = new UserDaoService(userDao, tranManager);
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
    
    private DataSource initDataSource() {
    	try {
    		InitialContext initialContext = new InitialContext();
    		return (DataSource)initialContext.lookup("java:comp/env/jdbc/task_shop");
    	} catch (NamingException e) {
    		LOG.error(e);
    		throw new RuntimeException(e);
    	}
    }

    public void contextDestroyed(ServletContextEvent arg0) {

    }
	
}

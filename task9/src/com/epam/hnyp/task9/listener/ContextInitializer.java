package com.epam.hnyp.task9.listener;

import java.io.File;

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
import com.epam.hnyp.task9.dao.CategoryDao;
import com.epam.hnyp.task9.dao.ManufacturerDao;
import com.epam.hnyp.task9.dao.ProductDao;
import com.epam.hnyp.task9.dao.UserDao;
import com.epam.hnyp.task9.dao.mysql.CategoryDaoMySql;
import com.epam.hnyp.task9.dao.mysql.ManufacturerDaoMySql;
import com.epam.hnyp.task9.dao.mysql.ProductDaoMySql;
import com.epam.hnyp.task9.dao.mysql.UserDaoMySql;
import com.epam.hnyp.task9.service.ProductsService;
import com.epam.hnyp.task9.service.UserService;
import com.epam.hnyp.task9.service.impl.ProductsServiceDaoImpl;
import com.epam.hnyp.task9.service.impl.TransactionManager;
import com.epam.hnyp.task9.service.impl.UserServiceDaoImpl;
import com.epam.hnyp.task9.util.convscope.ConversationScopeFactory;
import com.epam.hnyp.task9.util.convscope.SessionConversationScopeFactory;
import com.epam.hnyp.task9.util.img.ImgProvider;
import com.epam.hnyp.task9.util.img.JpegImgProvider;

public class ContextInitializer implements ServletContextListener {
	private static final Logger LOG = Logger.getLogger(ContextInitializer.class);
	
	public static final String PARAM_CAPCHA_SERVER_MODE = "capchaServerMode";
	public static final String PARAM_CAPCHA_TTL = "capchaTTL";
	public static final String PARAM_AVATAR_FOLDER = "avatarFolder";
	
	public static final String INIT_CAPCHA_PROVIDER_KEY = "init:capchaProvider";
	public static final String INIT_USER_SERVICE_KEY = "init:userService";
	public static final String INIT_CONVERSATION_SCOPE_FACTORY_KEY = "init:conversationScopeFactory";
	public static final String INIT_AVATAR_PROVIDER_KEY = "init:avatarProvider";
	public static final String INIT_PRODUCTS_SERVICE_KEY = "init:productService";
	
    public void contextInitialized(ServletContextEvent arg0) {
    	try {
    		ServletContext context = arg0.getServletContext();
        	initCapchaProvider(context);      	
        	initServices(context);
        	initConversationScope(context);       	
        	initAvatarImageProvider(context);
    	} catch (Exception e) {
    		LOG.error(e);
    		throw e;
    	}
    }
    
    private void initCapchaProvider(ServletContext context) {
    	String capchaServerMode = context.getInitParameter(PARAM_CAPCHA_SERVER_MODE);
    	String capchaTTLStr = context.getInitParameter(PARAM_CAPCHA_TTL);
    	
    	int capchaTTL = Integer.parseInt(capchaTTLStr);
    	if (capchaTTL <= 0) {
    		throw new IllegalArgumentException("parameter 'capchaTTL' must be > 0");
    	}
    	
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
    			throw new IllegalArgumentException("Server capcha uid store mod not specified. Use context param 'capchaServerMode' = 'session' | 'cookie' | 'hidden'");
    	}
    	context.setAttribute(INIT_CAPCHA_PROVIDER_KEY, capchaProvider);
    	if (LOG.isInfoEnabled()) {
    		LOG.info("capcha provider initialized = " + capchaProvider.getClass().getName());
    	}
    }
    
    private void initServices(ServletContext context) {
//    	UserRepo userRepo = new UserInMemoryRepo();
//    	UsersInitializer.initUsers(userRepo);
//    	UserService userService = new UserServiceInMemory(userRepo);
    	DataSource ds = null;
    	try {
    		InitialContext initialContext = new InitialContext();
    		ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/task_shop");
    	} catch (NamingException e) {
    		LOG.error(e);
    		throw new IllegalArgumentException(e);
    	}
    	TransactionManager tranManager = new TransactionManager(ds);
    	
    	UserDao userDao = new UserDaoMySql();
    	UserService userService = new UserServiceDaoImpl(userDao, tranManager);
    	context.setAttribute(INIT_USER_SERVICE_KEY, userService);
    	if (LOG.isInfoEnabled()) {
    		LOG.info("userService initialized = " + userService.getClass().getName());
    	}
    	
    	ProductDao productDao = new ProductDaoMySql();
    	CategoryDao categoryDao = new CategoryDaoMySql();
    	ManufacturerDao manufacturerDao = new ManufacturerDaoMySql();
    	ProductsService productService = new ProductsServiceDaoImpl(tranManager, productDao, categoryDao, manufacturerDao);
    	context.setAttribute(INIT_PRODUCTS_SERVICE_KEY, productService);
    	if (LOG.isInfoEnabled()) {
    		LOG.info("productService initialized = " + productService.getClass().getName());
    	}
    }
    
    private void initConversationScope(ServletContext context) {
    	ConversationScopeFactory conversationScopeFactory = new SessionConversationScopeFactory();
    	context.setAttribute(INIT_CONVERSATION_SCOPE_FACTORY_KEY, conversationScopeFactory);
    	if (LOG.isInfoEnabled()) {
    		LOG.info("conversation scope factory initialized = " + conversationScopeFactory.getClass().getName());
    	}
    }
    
    private void initAvatarImageProvider(ServletContext context) {
    	String avatarFolderPath = context.getInitParameter(PARAM_AVATAR_FOLDER);
    	
    	if (avatarFolderPath == null || avatarFolderPath.isEmpty()) {
    		throw new IllegalArgumentException("parameter '" + PARAM_AVATAR_FOLDER + "' must specify folder to contain avatar images");
    	}
    	
    	File folder = new File(avatarFolderPath);
    	if (!folder.exists() || !folder.isDirectory()) {
    		if (!folder.mkdirs()) {
    			throw new IllegalArgumentException("folder specified in parameter '" + PARAM_AVATAR_FOLDER + "' = [" + avatarFolderPath + "] does not exist and can not be created");
    		}
    	}
    	
    	ImgProvider avatarProvider = new JpegImgProvider(avatarFolderPath);
    	context.setAttribute(INIT_AVATAR_PROVIDER_KEY, avatarProvider);
    	
    	if (LOG.isInfoEnabled()) {
    		LOG.info("image provider initialized = " + avatarProvider.getClass().getName() + ", images folder = " + avatarFolderPath);
    	}
    }

    public void contextDestroyed(ServletContextEvent arg0) {

    }
	
}

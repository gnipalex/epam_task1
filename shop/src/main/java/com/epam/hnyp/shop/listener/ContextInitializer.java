package com.epam.hnyp.shop.listener;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.epam.hnyp.shop.capcha.provider.AbstractCapchaProvider;
import com.epam.hnyp.shop.capcha.provider.CookieCapchaProvider;
import com.epam.hnyp.shop.capcha.provider.HiddenCapchaProvider;
import com.epam.hnyp.shop.capcha.provider.SessionCapchaProvider;
import com.epam.hnyp.shop.dao.CategoryDao;
import com.epam.hnyp.shop.dao.ManufacturerDao;
import com.epam.hnyp.shop.dao.OrderDao;
import com.epam.hnyp.shop.dao.OrderItemDao;
import com.epam.hnyp.shop.dao.ProductDao;
import com.epam.hnyp.shop.dao.UserDao;
import com.epam.hnyp.shop.dao.mysql.CategoryDaoMySql;
import com.epam.hnyp.shop.dao.mysql.ManufacturerDaoMySql;
import com.epam.hnyp.shop.dao.mysql.OrderDaoMySql;
import com.epam.hnyp.shop.dao.mysql.OrderItemDaoMySql;
import com.epam.hnyp.shop.dao.mysql.ProductDaoMySql;
import com.epam.hnyp.shop.dao.mysql.UserDaoMySql;
import com.epam.hnyp.shop.locale.provider.AbstractLocaleProvider;
import com.epam.hnyp.shop.locale.provider.CookieLocaleProvider;
import com.epam.hnyp.shop.locale.provider.SessionLocaleProvider;
import com.epam.hnyp.shop.service.OrderService;
import com.epam.hnyp.shop.service.ProductService;
import com.epam.hnyp.shop.service.UserService;
import com.epam.hnyp.shop.service.impl.OrderServiceDaoImpl;
import com.epam.hnyp.shop.service.impl.ProductServiceDaoImpl;
import com.epam.hnyp.shop.service.impl.TransactionManager;
import com.epam.hnyp.shop.service.impl.UserServiceDaoImpl;
import com.epam.hnyp.shop.util.convscope.ConversationScopeFactory;
import com.epam.hnyp.shop.util.convscope.SessionConversationScopeFactory;
import com.epam.hnyp.shop.util.img.ImgProvider;
import com.epam.hnyp.shop.util.img.JpegImgProvider;

public class ContextInitializer implements ServletContextListener {
	private static final Logger LOG = Logger.getLogger(ContextInitializer.class);
	
	public static final String CONF_CONFIG_PROPS = "config.properties";
	public static final String CONF_AVATAR_FOLDER = "com.epam.hnyp.shop.util.img.ImgProvider.avatarFolder";
	public static final String CONF_PRODUCT_IMAGE_FOLDER = "com.epam.hnyp.shop.util.img.ImgProvider.productImagesFolder";
	
	public static final String PARAM_CAPCHA_SERVER_MODE = "capchaServerMode";
	public static final String PARAM_CAPCHA_TTL = "capchaTTL";
	public static final String PARAM_LOCALE_MODE = "localeMode";
	public static final String PARAM_LOCALE_COOKIE_TTL = "localeCookieTTL";
	
	public static final String INIT_CAPCHA_PROVIDER_KEY = "init:capchaProvider";
	
	public static final String INIT_USER_SERVICE_KEY = "init:userService";
	public static final String INIT_PRODUCTS_SERVICE_KEY = "init:productService";
	public static final String INIT_ORDER_SERVIVE_KEY = "init:orderService";

	public static final String INIT_CONVERSATION_SCOPE_FACTORY_KEY = "init:conversationScopeFactory";
	public static final String INIT_AVATAR_PROVIDER_KEY = "init:avatarProvider";
	public static final String INIT_PRODUCT_IMAGES_PROVIDER_KEY = "init:prodImagesProvider";
	public static final String INIT_LOCALE_PROVIDER_KEY = "init:localeProvider";
	
    public void contextInitialized(ServletContextEvent arg0) {
    	try {
    		ServletContext context = arg0.getServletContext();
        	initCapchaProvider(context);
        	initServices(context);
        	initConversationScope(context);
        	initLocaleProvider(context);
        	
        	Properties config = readConfigProperties();
        	initImageProvider(context, config.getProperty(CONF_AVATAR_FOLDER), INIT_AVATAR_PROVIDER_KEY);
        	initImageProvider(context, config.getProperty(CONF_PRODUCT_IMAGE_FOLDER), INIT_PRODUCT_IMAGES_PROVIDER_KEY);
    	} catch (Exception e) {
    		LOG.error(e.getMessage(), e);
    		throw new IllegalArgumentException("error initializing context", e);
    	}
    }
    
    public Properties readConfigProperties() throws IOException {
    	Properties conf = new Properties();
    	conf.load(ContextInitializer.class.getClassLoader().getResourceAsStream(CONF_CONFIG_PROPS));
    	return conf;
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
    	DataSource ds = null;
    	try {
    		InitialContext initialContext = new InitialContext();
    		ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/task_shop");
    		if (ds == null) {
    			throw new IllegalArgumentException("data source is not initialized");
    		}
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
    	ProductService productService = new ProductServiceDaoImpl(tranManager, productDao, categoryDao, manufacturerDao);
    	context.setAttribute(INIT_PRODUCTS_SERVICE_KEY, productService);
    	if (LOG.isInfoEnabled()) {
    		LOG.info("productService initialized = " + productService.getClass().getName());
    	}
    	
    	OrderDao orderDao = new OrderDaoMySql();
    	OrderItemDao orderItemDao = new OrderItemDaoMySql();
    	OrderService orderService = new OrderServiceDaoImpl(orderDao, orderItemDao, productDao, tranManager);
    	context.setAttribute(INIT_ORDER_SERVIVE_KEY, orderService);
    	if (LOG.isInfoEnabled()) {
    		LOG.info("orderService initialized = " + orderService.getClass().getName());
    	}
    }
    
    private void initConversationScope(ServletContext context) {
    	ConversationScopeFactory conversationScopeFactory = new SessionConversationScopeFactory();
    	context.setAttribute(INIT_CONVERSATION_SCOPE_FACTORY_KEY, conversationScopeFactory);
    	if (LOG.isInfoEnabled()) {
    		LOG.info("conversation scope factory initialized = " + conversationScopeFactory.getClass().getName());
    	}
    }
    
    private void initImageProvider(ServletContext context, String folder, String initKey) {

    	File dir = new File(folder);
    	if (!dir.exists() || !dir.isDirectory()) {
    		if (!dir.mkdirs()) {
    			throw new IllegalArgumentException("folder [" + folder + "] does not exist and can not be created");
    		}
    	}
    	
    	ImgProvider avatarProvider = new JpegImgProvider(folder);
    	context.setAttribute(initKey, avatarProvider);
    	
    	if (LOG.isInfoEnabled()) {
    		LOG.info("image provider initialized = " + avatarProvider.getClass().getName() + ", images folder = " + folder);
    	}
    }
    
    private void initLocaleProvider(ServletContext context) {
    	String localeMode = context.getInitParameter(PARAM_LOCALE_MODE);
    	String localeCookieTTL = context.getInitParameter(PARAM_LOCALE_COOKIE_TTL);
    	if (localeMode == null) {
    		throw new IllegalArgumentException("localeMode not specified");
    	}
    	AbstractLocaleProvider localeProvider = null;
    	switch (localeMode) {
    	case "cookie" :
    		int cookieTTL = 0;
    		try {
    			cookieTTL = Integer.parseInt(localeCookieTTL);
    			if (cookieTTL < 0) {
    				throw new NumberFormatException();
    			}
    		} catch (NumberFormatException e) {
    			throw new IllegalArgumentException(PARAM_LOCALE_COOKIE_TTL + " wrong format", e);
    		}
    		localeProvider = new CookieLocaleProvider(cookieTTL);
    		break;
    	case "session":
    		localeProvider = new SessionLocaleProvider();
    		break;
    		default:
    			throw new IllegalArgumentException(PARAM_LOCALE_MODE + " mode not supported or not specified, use 'session' or 'cookie'");
    	}
    	context.setAttribute(INIT_LOCALE_PROVIDER_KEY, localeProvider);
    	if (LOG.isInfoEnabled()) {
    		LOG.info("locale provider created = " + localeProvider.getClass().getName());
    	}
    }
    
    public void contextDestroyed(ServletContextEvent arg0) {

    }
	
}

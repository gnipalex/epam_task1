package com.epam.hnyp.shop.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.hnyp.shop.cart.Cart;
import com.epam.hnyp.shop.cart.Cart.CartStateException;
import com.epam.hnyp.shop.form.OrderFormBean;
import com.epam.hnyp.shop.listener.ContextInitializer;
import com.epam.hnyp.shop.model.DeliveryType;
import com.epam.hnyp.shop.model.Order;
import com.epam.hnyp.shop.model.PayType;
import com.epam.hnyp.shop.util.convscope.ConversationScopeFactory;
import com.epam.hnyp.shop.util.convscope.ConversationScopeProvider;

public class PrepareOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger
			.getLogger(PrepareOrderServlet.class);

	public static final String ORDER_JSP = "WEB-INF/jsp/prepareOrder.jsp";
	public static final String CONFIRM_ORDER_JSP = "WEB-INF/jsp/confirmOrder.jsp";
	
	public static final String POSTREDIRECT_PREPARE_CONVSCOPE_KEY = "com.epam.hnyp.shop.servlet.PrepareOrderServlet.POSTREDIRECT_PREPARE_CONVSCOPE_KEY";
	public static final String FAILED_URL = "/prepareOrder";
	public static final String SUCCESS_URL = "/products";

	public static final String PARAM_PAY_TYPE = "payType";
	public static final String PARAM_CREDIT_CARD = "creditCard";
	public static final String PARAM_DELIVERY_TYPE = "deliveryType";
	public static final String PARAM_ADDRESS = "address";

	public static final String PREPARED_ORDER_KEY = "PREPARED_ORDER_KEY";

	public static final String ERROR_MESSAGES_MAP_KEY = "errorMessages";
	public static final String ORDER_FORM_BEAN_KEY = "orderBean";
	public static final String PAY_TYPES_KEY = "payTypes";
	public static final String DELIVERY_TYPES_KEY = "deliveryTypes";

	private ConversationScopeFactory convScopeFactory;

	@Override
	public void init() throws ServletException {
		convScopeFactory = (ConversationScopeFactory) getServletContext()
				.getAttribute(
						ContextInitializer.INIT_CONVERSATION_SCOPE_FACTORY_KEY);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ConversationScopeProvider convScope = convScopeFactory
				.newConversationScopeProvider(req,
						POSTREDIRECT_PREPARE_CONVSCOPE_KEY);
		convScope.restore();

		req.setAttribute(PAY_TYPES_KEY, PayType.values());
		req.setAttribute(DELIVERY_TYPES_KEY, DeliveryType.values());
		req.getRequestDispatcher(ORDER_JSP).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// validate
		OrderFormBean bean = readOrderForm(req);
		Map<String, String> errorsMap = bean.validate();
		if (!errorsMap.isEmpty()) {
			ConversationScopeProvider convScope = convScopeFactory
					.newConversationScopeProvider(req,
							POSTREDIRECT_PREPARE_CONVSCOPE_KEY);
			convScope.addAttribute(ERROR_MESSAGES_MAP_KEY, errorsMap);
			convScope.addAttribute(ORDER_FORM_BEAN_KEY, bean);
			convScope.save();
			resp.sendRedirect(getServletContext().getContextPath() + FAILED_URL);
			return;
		}

		HttpSession session = req.getSession();
		Cart preparedCart = (Cart) session.getAttribute(PrepareCartServlet.PREPARED_CART_KEY);
		if (preparedCart == null || preparedCart.getTotalCount() == 0) {
			LOG.error("prepared cart is not found or empty");
			resp.sendRedirect(getServletContext().getContextPath() + "/cart");
			return;
		}
		try {
			Order preparedOrder = preparedCart.prepareOrder();
			preparedOrder.setAddress(bean.getAddressParam());
			preparedOrder.setCreditCardCode(bean.getCreditCardParam());
			preparedOrder.setDeliveryType(bean.getDeliveryType());
			preparedOrder.setPayType(bean.getPayType());
			session.setAttribute(PREPARED_ORDER_KEY, preparedOrder);
		} catch (CartStateException e) {
			LOG.error("order cannot be prepared", e);
			resp.sendRedirect(getServletContext().getContextPath() + "/message" + prepareMessageUrlArgs("Order create status", "Order is not prepared, cart is empty"));
			return;
		}
		req.getRequestDispatcher(CONFIRM_ORDER_JSP).forward(req, resp);
	}
	
	private String prepareMessageUrlArgs(String title, String message) {
		StringBuilder reqArgs = new StringBuilder();
		reqArgs.append('?').append("message=").append(message).append("&title=").append(title);
		return reqArgs.toString();
	}

	private OrderFormBean readOrderForm(HttpServletRequest req) {
		OrderFormBean bean = new OrderFormBean();
		bean.setAddressParam(req.getParameter(PARAM_ADDRESS));
		bean.setCreditCardParam(req.getParameter(PARAM_CREDIT_CARD));
		bean.setDeliveryTypeParam(req.getParameter(PARAM_DELIVERY_TYPE));
		bean.setPayTypeParam(req.getParameter(PARAM_PAY_TYPE));
		return bean;
	}
}

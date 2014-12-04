package com.epam.hnyp.shop.form;

import java.util.HashMap;
import java.util.Map;

import com.epam.hnyp.shop.model.DeliveryType;
import com.epam.hnyp.shop.model.PayType;

public class OrderFormBean {
	private String payTypeParam;
	private PayType payType;
	public static final String PAY_TYPE_ERROR_KEY = "payTypeError";
	
	private String creditCardParam;
	public static final String CREDIT_CARD_ERROR_KEY = "creditCardError";
	
	private String deliveryTypeParam;
	private DeliveryType deliveryType;
	public static final String DELIVERY_TYPE_ERROR_KEY = "deliveryTypeError";
	
	private String addressParam;
	public static final String ADDRESS_ERROR_KEY = "addressError";
	
	public Map<String, String> validate() {
		Map<String, String> errors = new HashMap<String, String>();
		if (payTypeParam == null || payTypeParam.isEmpty()) {
			errors.put(PAY_TYPE_ERROR_KEY, "pay type not specified");
		} else {
			try {
				payType = PayType.valueOf(payTypeParam);
			} catch (Exception e) {
				errors.put(PAY_TYPE_ERROR_KEY, "incorrect pay type");
			}
		}
		if (creditCardParam == null || creditCardParam.isEmpty()) {
			if (payType != null && payType == PayType.CREDIT) {
				errors.put(CREDIT_CARD_ERROR_KEY, "must be specified for " + PayType.CREDIT.getType());
			} 
		} 
		if (deliveryTypeParam == null || deliveryTypeParam.isEmpty()) {
			errors.put(DELIVERY_TYPE_ERROR_KEY, "delivery type not specified");
		} else {
			try {
				deliveryType = DeliveryType.valueOf(deliveryTypeParam);
			} catch (Exception e) {
				errors.put(DELIVERY_TYPE_ERROR_KEY, "incorrect delivery type");
			}
		}
		if (addressParam == null || addressParam.isEmpty()) {
			if (deliveryType != null && deliveryType == DeliveryType.HOMEDELIVERY) {
				errors.put(ADDRESS_ERROR_KEY, "must be specified for " + DeliveryType.HOMEDELIVERY.getDelivery());
			}
		}
		return errors;
	}

	public String getPayTypeParam() {
		return payTypeParam;
	}

	public void setPayTypeParam(String payTypeParam) {
		this.payTypeParam = payTypeParam;
	}

	public String getCreditCardParam() {
		return creditCardParam;
	}

	public void setCreditCardParam(String creditCardParam) {
		this.creditCardParam = creditCardParam;
	}

	public String getDeliveryTypeParam() {
		return deliveryTypeParam;
	}

	public void setDeliveryTypeParam(String deliveryTypeParam) {
		this.deliveryTypeParam = deliveryTypeParam;
	}

	public String getAddressParam() {
		return addressParam;
	}

	public void setAddressParam(String addressParam) {
		this.addressParam = addressParam;
	}

	public PayType getPayType() {
		return payType;
	}

	public DeliveryType getDeliveryType() {
		return deliveryType;
	}

}

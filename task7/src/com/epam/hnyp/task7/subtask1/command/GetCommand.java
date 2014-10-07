package com.epam.hnyp.task7.subtask1.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.hnyp.task7.subtask1.facade.ProductsFacade;

public class GetCommand implements Command  {
	private ProductsFacade facade;

	public GetCommand(ProductsFacade facade) {
		this.facade = facade;
	}

	@Override
	public String process(String params) {
		StringBuilder response = new StringBuilder();
		Matcher mItem = Pattern.compile("(?i)^\\s*item").matcher(params);
		Matcher mCount = Pattern.compile("(?i)^\\s*count").matcher(params);
		if (mItem.find()) {
			getItem(response, params);
		} else if (mCount.find()) {
			getCount(response);
		} else {
			response.append("#get : bad request");
		}		
		return response.toString();
	}
	
	private void getItem(StringBuilder response, String params) {
		Matcher idMatcher = Pattern.compile("item\\s*=\\s*([-\\d]+)").matcher(params);
		if (!idMatcher.find()){
			response.append("#get item : wrong arguments");
			return;
		}
		long id = 0;
		try {
			id = Long.parseLong(idMatcher.group(1));
		} catch (NumberFormatException e) {
			response.append("#get item : wrong format of argument");
			return;
		}
		ProductsFacade.ProductInfo prodInfo = facade.getProductInfo(id);
		if (prodInfo == null) {
			response.append("product not found");
			return;
		} 
		response.append(prodInfo.getName()).append(" | ").append(prodInfo.getPrice());
	}
	
	private void getCount(StringBuilder response) {
		response.append(facade.getCount());
	}
}

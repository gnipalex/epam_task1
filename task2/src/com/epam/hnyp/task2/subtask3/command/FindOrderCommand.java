package com.epam.hnyp.task2.subtask3.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.model.Order;
import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class FindOrderCommand extends AbstractCommand {

	private ShopFacade shopFacade;
	private IOProvider ioProvider;
	
	public FindOrderCommand(ShopFacade shopFacade, IOProvider ioProvider) {
		this.shopFacade = shopFacade;
		this.ioProvider = ioProvider;
	}
	
	@Override
	public void execute() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Scanner sc = new Scanner(ioProvider.getInput());
		Date date = null; 
		
		while (true) {
			ioProvider.getOutput().print("Please enter a date of order (dd/MM/yyyy) or just pass enter to cancel: ");
			String line = sc.nextLine();
			if (line.isEmpty()) {
				return;
			}
			try {
				date = sdf.parse(line);
			} catch (ParseException e) {
				System.out.println("##date parse error##");
				continue;
			}
			break;
		}
		
		Order order = shopFacade.getNearestOrder(date);
		
		if (order == null) {
			ioProvider.getOutput().println("---order not found---");
			return;
		} 
		
		ioProvider.getOutput().println("Order near to date " + sdf.format(date) + " :");
		SimpleDateFormat sdf_full = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		ioProvider.getOutput().println("Date of order: " + sdf_full.format(order.getDate()));
		ioProvider.getOutput().println("Customer: " + order.getCustomer());
		StringBuilder str = new StringBuilder();
		
		ioProvider.getOutput().print("Goods in cart (name[count]): ");
		for (Entry<Long, Integer> e : order.getItems().entrySet()) {
			Product g = shopFacade.getProductById(e.getKey());
			if (g == null) {
				ioProvider.getOutput().print("not found, ");
				continue;
			}
			ioProvider.getOutput().print(g.getName() + "[" + e.getValue() + "], ");
		}
		ioProvider.getOutput().println();
		int totalPrice = shopFacade.getPriceForOrder(order);
		ioProvider.getOutput().println("Total price: " + totalPrice);
	}

	@Override
	public String about() {
		return "find order";
	}

	@Override
	public Map<String, AbstractCommand> getCommandsMap() {
		return null;
	}

}

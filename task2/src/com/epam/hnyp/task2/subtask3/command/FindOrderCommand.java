package com.epam.hnyp.task2.subtask3.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.model.Order;
import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class FindOrderCommand extends AbstractCommand {

	private ShopFacade shopFacade;
	private IOProvider ioProvider;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public FindOrderCommand(ShopFacade shopFacade, IOProvider ioProvider) {
		this.shopFacade = shopFacade;
		this.ioProvider = ioProvider;
	}
	
	@Override
	public void execute() {
		Date date = null; 
		boolean parseError = true;
		
		do {
			ioProvider.print("Please enter a date of order (dd/MM/yyyy) or just pass enter to cancel: ");
			String line = ioProvider.readLine();
			if (line.isEmpty()) {
				return;
			}
			try {
				date = sdf.parse(line);
				parseError = false;
			} catch (ParseException e) {
				System.out.println("##date parse error##");
			}
		} while (parseError);
		
		Order order = shopFacade.getNearestOrder(date);
		
		if (order == null) {
			ioProvider.printLine("---order not found---");
			return;
		} 
		
		printOrder(order, date);
	}
	
	private void printOrder(Order order, Date date) {
		SimpleDateFormat sdf_full = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		ioProvider.printLine("Order near to date " + sdf.format(date) + " :");
		ioProvider.printLine("Date of order: " + sdf_full.format(order.getDate()));
		ioProvider.printLine("Customer: " + order.getCustomer());		
		ioProvider.printLine("Goods in cart (name[count]): ");
		for (Entry<Long, Integer> e : order.getItems().entrySet()) {
			Product g = shopFacade.getProductById(e.getKey());
			if (g == null) {
				ioProvider.print("not found, ");
				continue;
			}
			ioProvider.print(g.getName() + "[" + e.getValue() + "], ");
		}
		ioProvider.printLine();
		int totalPrice = shopFacade.getPriceForOrder(order);
		ioProvider.printLine("Total price: " + totalPrice);
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

package com.epam.hnyp.task2.subtask3.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.model.Order;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class OrdersByPeriodCommand extends AbstractCommand {
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private ShopFacade shopFacade;
	private IOProvider ioProvider;
	
	public OrdersByPeriodCommand(ShopFacade shopFacade, IOProvider ioProvider) {
		this.shopFacade = shopFacade;
		this.ioProvider = ioProvider;
	}
	
	@Override
	public void execute() {	
		Date date_left = null;
		Date date_right = null;
		
		boolean parseError = true;
		do {
			ioProvider.print("Please enter left border date (dd/MM/yyyy) or pass enter to cancel: ");
			String line = ioProvider.readLine();
			if (line.isEmpty()) {
				return;
			}
			try {
				date_left = sdf.parse(line);
				parseError = false;
			} catch (ParseException e) {
				ioProvider.printLine("##date parse error##");
			}
		} while (parseError);
		
		parseError = true;
		do {
			ioProvider.print("Please enter right border date (dd/MM/yyyy) or pass enter to cancel: ");
			String line = ioProvider.readLine();
			if (line.isEmpty()) {
				return;
			}
			try {
				date_right = sdf.parse(line);
				parseError = false;
			} catch (ParseException e) {
				ioProvider.printLine("##date parse error##");
			}
		} while (parseError);
		
		Collection<Order> orders = shopFacade.getOrdersOfPeriod(date_left, date_right);
		printOrders(orders, date_left, date_right);
	}
	
	private void printOrders(Collection<Order> orders, Date dateLeft, Date dateRight) {
		SimpleDateFormat sdf_full = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		ioProvider.printLine("Orders for period " + sdf.format(dateLeft) + " -- " + sdf.format(dateRight));
		ioProvider.printLine(String.format("%1$20s\t%2$20s\t%3$10s", "date", "customer", "goods cnt"));
		ioProvider.printLine("------------------------------------");
		for (Order o : orders) {
			ioProvider.printLine(String.format("%1$20s\t%2$20s\t%3$10d", sdf_full.format(o.getDate()), o.getCustomer(), o.getCountOfProducts()));
		}
		ioProvider.printLine("------------------------------------");
	}

	@Override
	public String about() {
		return "orders by period";
	}

	@Override
	public Map<String, AbstractCommand> getCommandsMap() {
		return null;
	}

}

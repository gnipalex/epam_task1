package com.epam.hnyp.task2.subtask3.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.model.Order;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class OrdersByPeriodCommand extends AbstractCommand {

	private ShopFacade shopFacade;
	private IOProvider ioProvider;
	
	public OrdersByPeriodCommand(ShopFacade shopFacade, IOProvider ioProvider) {
		this.shopFacade = shopFacade;
		this.ioProvider = ioProvider;
	}
	
	@Override
	public void execute() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Scanner sc = new Scanner(ioProvider.getInput());
		
		Date date_left = null;
		Date date_right = null;
		
		while(true) {
			ioProvider.getOutput().print("Please enter left border date (dd/MM/yyyy) or pass enter to cancel: ");
			String line = sc.nextLine();
			if (line.isEmpty()) {
				return;
			}
			try {
				date_left = sdf.parse(line);
			} catch (ParseException e) {
				ioProvider.getOutput().println("##date parse error##");
				continue;
			}
			break;
		}
		while(true) {
			ioProvider.getOutput().print("Please enter right border date (dd/MM/yyyy) or pass enter to cancel: ");
			String line = sc.nextLine();
			if (line.isEmpty()) {
				return;
			}
			try {
				date_right = sdf.parse(line);
			} catch (ParseException e) {
				ioProvider.getOutput().println("##date parse error##");
				continue;
			}
			break;
		}
		SimpleDateFormat sdf_full = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		ioProvider.getOutput().println("Orders for period " + sdf.format(date_left) + " -- " + sdf.format(date_right));
		ioProvider.getOutput().printf("%1$20s\t%2$20s\t%3$10s\n", "date", "customer", "goods cnt");
		ioProvider.getOutput().println("------------------------------------");
		
		for (Order o : shopFacade.getOrdersOfPeriod(date_left, date_right)) {
			ioProvider.getOutput().printf("%1$20s\t%2$20s\t%3$10d\n", sdf_full.format(o.getDate()), o.getCustomer(), o.getCountOfProducts());
		}
		ioProvider.getOutput().println("------------------------------------");
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

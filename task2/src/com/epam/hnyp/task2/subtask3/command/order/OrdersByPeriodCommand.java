package com.epam.hnyp.task2.subtask3.command.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.command.AbstractCommand;
import com.epam.hnyp.task2.subtask3.model.Order;

public class OrdersByPeriodCommand extends AbstractCommand {

	@Override
	public void execute(String... args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);
		
		Date date_left = null;
		Date date_right = null;
		
		while(true) {
			System.out.print("Please enter left border date (dd/MM/yyyy) or pass enter to cancel: ");
			String line = sc.nextLine();
			if (line.isEmpty()) {
				return;
			}
			try {
				date_left = sdf.parse(line);
			} catch (ParseException e) {
				System.out.println("##date parse error##");
				continue;
			}
			break;
		}
		while(true) {
			System.out.print("Please enter right border date (dd/MM/yyyy) or pass enter to cancel: ");
			String line = sc.nextLine();
			if (line.isEmpty()) {
				return;
			}
			try {
				date_right = sdf.parse(line);
			} catch (ParseException e) {
				System.out.println("##date parse error##");
				continue;
			}
			break;
		}
		SimpleDateFormat sdf_full = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		System.out.println("Orders for period " + sdf.format(date_left) + " -- " + sdf.format(date_right));
		System.out.printf("%1$20s\t%2$20s\t%3$10s\n", "date", "customer", "goods cnt");
		System.out.println("------------------------------------");
//		for (Order o : ConfigGrocery.STORE.getOrdersOfPeriod(date_left, date_right)) {
//			System.out.printf("%1$20s\t%2$20s\t%3$10d\n", sdf_full.format(o.getDate()), o.getCustomer(), o.getCart().size());
		for (Order o : getOrderService().getOrdersOfPeriod(date_left, date_right)) {
			System.out.printf("%1$20s\t%2$20s\t%3$10d\n", sdf_full.format(o.getDate()), o.getCustomer(), o.getCountOfProducts());
		}
		System.out.println("------------------------------------");
	}

	@Override
	public String about() {
		return "orders by period";
	}

}

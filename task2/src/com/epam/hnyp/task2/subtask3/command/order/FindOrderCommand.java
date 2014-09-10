package com.epam.hnyp.task2.subtask3.command.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map.Entry;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.command.AbstractCommand;
import com.epam.hnyp.task2.subtask3.model.Good;
import com.epam.hnyp.task2.subtask3.model.Order;

public class FindOrderCommand extends AbstractCommand {

	@Override
	public void execute(String... args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);
		Date date = null; 
		
		while (true) {
			System.out.print("Please enter a date of order (dd/MM/yyyy) or just pass enter to cancel: ");
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
		
//		Order order = ConfigGrocery.STORE.getNearest(date);
		Order order = getOrderService().getNearest(date);
		
		if (order == null) {
			System.out.println("---order not found---");
			return;
		} 
		
		System.out.println("Order near to date " + sdf.format(date) + " :");
		SimpleDateFormat sdf_full = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		System.out.println("Date of order: " + sdf_full.format(order.getDate()));
		System.out.println("Customer: " + order.getCustomer());
		StringBuilder str = new StringBuilder();
		System.out.print("Goods in cart (name[count]): ");
//		for (Entry<Long, Integer> e : order.getCart().getAllItems().entrySet()) {
//			Good g = ConfigGrocery.STORE.get(e.getKey());
		for (Entry<Long, Integer> e : order.getItems().entrySet()) {
			Good g = getGoodsService().get(e.getKey());
			if (g == null) {
				System.out.print("not found, ");
				continue;
			}
			System.out.print(g.getName() + "[" + e.getValue() + "], ");
		}
		System.out.println();
//		int totalPrice = ConfigGrocery.STORE.getPriceForAll(order.getCart().getAllItems());
		int totalPrice = getGoodsService().getPriceForAll(order.getItems());
		System.out.println("Total price: " + totalPrice);
	}

	@Override
	public String about() {
		return "find order";
	}

}

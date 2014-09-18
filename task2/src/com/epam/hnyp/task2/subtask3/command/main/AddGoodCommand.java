package com.epam.hnyp.task2.subtask3.command.main;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.command.AbstractCommand;
import com.epam.hnyp.task2.subtask3.command.MyKeyValue;
import com.epam.hnyp.task2.subtask3.model.CerealGood;
import com.epam.hnyp.task2.subtask3.model.DrinkGood;
import com.epam.hnyp.task2.subtask3.model.Good;
import com.epam.hnyp.task2.subtask3.model.SweetGood;
import com.epam.hnyp.task2.subtask3.model.VegetableGood;
import com.epam.hnyp.task2.subtask3.model.WeightableGood;
import com.epam.hnyp.task2.subtask3.model.creator.GoodCreator;
import com.epam.hnyp.task2.subtask3.model.creator.GoodCreator.GoodCreateException;

public class AddGoodCommand extends AbstractCommand {
	
	public static final String CREATOR_CLASS_PARAM = "AddGoodCommand_CREATOR_CLASS";
	
	private static Map<String, MyKeyValue<String ,Class<? extends Good>>> goods = new LinkedHashMap<>();
	static {
		goods.put("1",  new MyKeyValue<String, Class<? extends Good>>("simple good", Good.class));
		goods.put("2",  new MyKeyValue<String, Class<? extends Good>>("vegetable", VegetableGood.class));
		goods.put("3",  new MyKeyValue<String, Class<? extends Good>>("drink", DrinkGood.class));
		goods.put("4",  new MyKeyValue<String, Class<? extends Good>>("sweet", SweetGood.class));
		goods.put("5",  new MyKeyValue<String, Class<? extends Good>>("cereal", CerealGood.class));
		goods.put("6",  new MyKeyValue<String, Class<? extends Good>>("weightable good", WeightableGood.class));
	}

	@Override
	public void execute(String... args) {
		GoodCreator goodCreator = buildCreator(args);
		if (goodCreator == null) {
			return;
		}
		
		System.out.println("Adding of new good to grocery.");
		printGoodsTypes();
		
		System.out.println();
		System.out.print("Choice (or nothing to cancel) -> ");
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		
		if (line.isEmpty()) {
			return;
		}
		
		MyKeyValue<String, Class<? extends Good>> chosedType = goods.get(String.valueOf(line.charAt(0)));
		if (chosedType == null) {
			System.out.println("wrong input, type with key " + line.charAt(0) + " not found");
			return;
		}
		
		Good good = instantiateGood(chosedType.getValue());
		if (good == null) {
			System.out.println("##error, good cannot be added##");
			return;
		}
		
		System.out.println("Creating new '" + chosedType.getKey() + "'");
		try {
			goodCreator.createGood(good);
		} catch (GoodCreateException e) {
			System.out.println("##error while creating good##");
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("Created new '" + chosedType.getKey() + "' : " + good.toString());
		if (getGoodsService().add(good)) {
			System.out.println("Good successfuly saved");
		} else {
			System.out.println("Good NOT saved, something went wrong");
		}
	}
	
	private Good instantiateGood(Class<? extends Good> goodType) {
		Good good = null;
		try {
			good = goodType.newInstance();
		} catch (Exception e) {
			return null;
		}
		return good;
	}
	
	private void printGoodsTypes() {
		System.out.println("Available types of goods:");
		for (Entry<String, MyKeyValue<String ,Class<? extends Good>>> e : goods.entrySet()) {
			System.out.println(e.getKey() + " - " + e.getValue().getKey());
		}
	}
	
	
	private GoodCreator buildCreator(String[] args) {
		GoodCreator goodCreator = null;
		String className = null;
		for (String s : args) {
			if (s.startsWith(CREATOR_CLASS_PARAM)) {
				String[] split = s.split(":");
				if (split == null || split.length < 2) {
					System.out
							.println("##error, config implementation to add good##");
					return null;
				}
				className = split[1];
				break;
			}
		}
		try {
			goodCreator = (GoodCreator) Class.forName(className).newInstance();
		} catch (ClassNotFoundException e) {
			System.out
					.println("##error, implementation of good creator not found##");
			return null;
		} catch (Exception e) {
			System.out.println("##error, creator is not created, good cannot be added##");
			return null;
		}
		return goodCreator;
	}

	@Override
	public String about() {
		return "add new good to store";
	}

}

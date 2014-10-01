package com.epam.hnyp.task2.subtask3.command;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.model.CerealProduct;
import com.epam.hnyp.task2.subtask3.model.DrinkProduct;
import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.model.SweetProduct;
import com.epam.hnyp.task2.subtask3.model.VegetableProduct;
import com.epam.hnyp.task2.subtask3.model.WeightableProduct;
import com.epam.hnyp.task2.subtask3.model.creator.ProductCreator;
import com.epam.hnyp.task2.subtask3.model.creator.ProductCreator.ProductCreateException;

public class AddProductCommand extends AbstractCommand {
	
	public static final String CREATOR_CLASS_PARAM = "AddProductCommand_CREATOR_CLASS";
	
	private static Map<String, MyKeyValue<String ,Class<? extends Product>>> prods = new LinkedHashMap<>();
	static {
		prods.put("1",  new MyKeyValue<String, Class<? extends Product>>("simple product", Product.class));
		prods.put("2",  new MyKeyValue<String, Class<? extends Product>>("vegetable", VegetableProduct.class));
		prods.put("3",  new MyKeyValue<String, Class<? extends Product>>("drink", DrinkProduct.class));
		prods.put("4",  new MyKeyValue<String, Class<? extends Product>>("sweet", SweetProduct.class));
		prods.put("5",  new MyKeyValue<String, Class<? extends Product>>("cereal", CerealProduct.class));
		prods.put("6",  new MyKeyValue<String, Class<? extends Product>>("weightable product", WeightableProduct.class));
	}

	@Override
	public void execute(String... args) {
		ProductCreator productCreator = buildCreator(args);
		if (productCreator == null) {
			return;
		}
		
		System.out.println("Adding of new product to grocery.");
		printProductsTypes();
		
		System.out.println();
		System.out.print("Choice (or nothing to cancel) -> ");
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		
		if (line.isEmpty()) {
			return;
		}
		
		MyKeyValue<String, Class<? extends Product>> chosedType = prods.get(String.valueOf(line.charAt(0)));
		if (chosedType == null) {
			System.out.println("wrong input, type with key " + line.charAt(0) + " not found");
			return;
		}
		
		Product product = instantiateProduct(chosedType.getValue());
		if (product == null) {
			System.out.println("##error, product cannot be added##");
			return;
		}
		
		System.out.println("Creating new '" + chosedType.getKey() + "'");
		try {
			productCreator.createProduct(product);
		} catch (ProductCreateException e) {
			System.out.println("##error while creating product##");
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("Created new '" + chosedType.getKey() + "' : " + product.toString());
		if (getProductsService().add(product)) {
			System.out.println("Product successfuly saved");
		} else {
			System.out.println("Product NOT saved, something went wrong");
		}
	}
	
	private Product instantiateProduct(Class<? extends Product> prodType) {
		Product product = null;
		try {
			product = prodType.newInstance();
		} catch (Exception e) {
			return null;
		}
		return product;
	}
	
	private void printProductsTypes() {
		System.out.println("Available types of products:");
		for (Entry<String, MyKeyValue<String ,Class<? extends Product>>> e : prods.entrySet()) {
			System.out.println(e.getKey() + " - " + e.getValue().getKey());
		}
	}
	
	private ProductCreator buildCreator(String[] args) {
		ProductCreator prodCreator = null;
		String className = null;
		for (String s : args) {
			if (s.startsWith(CREATOR_CLASS_PARAM)) {
				String[] split = s.split(":");
				if (split == null || split.length < 2) {
					System.out
							.println("##error, config implementation to add product##");
					return null;
				}
				className = split[1];
				break;
			}
		}
		try {
			prodCreator = (ProductCreator) Class.forName(className).newInstance();
		} catch (ClassNotFoundException e) {
			System.out
					.println("##error, implementation of product creator not found##");
			return null;
		} catch (Exception e) {
			System.out.println("##error, creator is not created, product cannot be added##");
			return null;
		}
		return prodCreator;
	}

	@Override
	public String about() {
		return "add new product to store";
	}

}

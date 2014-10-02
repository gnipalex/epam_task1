package com.epam.hnyp.task2.subtask3.command;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.creator.AbstractProductCreator;
import com.epam.hnyp.task2.subtask3.creator.AbstractProductCreator.CreateProductException;
import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.util.IOProvider;
import com.epam.hnyp.task2.subtask3.util.MyKeyValue;

public class AddProductCommand extends AbstractCommand {	
//	private static Map<String, MyKeyValue<String ,Class<? extends Product>>> prods = new LinkedHashMap<>();
//	static {
//		prods.put("1",  new MyKeyValue<String, Class<? extends Product>>("simple product", Product.class));
//		prods.put("2",  new MyKeyValue<String, Class<? extends Product>>("vegetable", VegetableProduct.class));
//		prods.put("3",  new MyKeyValue<String, Class<? extends Product>>("drink", DrinkProduct.class));
//		prods.put("4",  new MyKeyValue<String, Class<? extends Product>>("sweet", SweetProduct.class));
//		prods.put("5",  new MyKeyValue<String, Class<? extends Product>>("cereal", CerealProduct.class));
//		prods.put("6",  new MyKeyValue<String, Class<? extends Product>>("weightable product", WeightableProduct.class));
//	}
	
	private ShopFacade shopFacade;
	private IOProvider ioProvider;
	private AbstractProductCreator productCreator;
	
	private Map<String, MyKeyValue<String ,Class<? extends Product>>> availableProducts = new LinkedHashMap<>();
	
	public AddProductCommand(ShopFacade shopFacade, IOProvider ioProvider, AbstractProductCreator productCreator) {
		this.shopFacade = shopFacade;
		this.ioProvider = ioProvider;
		this.productCreator = productCreator;
		int i = 1;
		for (Entry<String, Class<? extends Product>> e : productCreator.getProducts().entrySet()) {
			availableProducts.put(String.valueOf(i++), 
					new MyKeyValue<String, Class<? extends Product>>(e.getKey(), e.getValue()));
		}
	}

	@Override
	public void execute() {
		ioProvider.getOutput().println("Adding of new product to grocery.");
		printProductsTypes();
		ioProvider.getOutput().println();
		ioProvider.getOutput().print("Choice (or nothing to cancel) -> ");
		
		Scanner sc = new Scanner(ioProvider.getInput());
		String line = sc.nextLine();
		
		if (line.isEmpty()) {
			return;
		}
		
		MyKeyValue<String, Class<? extends Product>> chosedType = availableProducts.get(String.valueOf(line.charAt(0)));
		if (chosedType == null) {
			ioProvider.getOutput().println("wrong input, type with key " + line.charAt(0) + " not found");
			return;
		}
		
		Product product = null;
		try {
			product = productCreator.createProduct(chosedType.getValue());
		} catch (CreateProductException e) {
			ioProvider.getOutput().println("##error product creating : " + e.getMessage());
			return;
		}
		
		ioProvider.getOutput().println("Created new '" + chosedType.getKey() + "' : " + product.toString());
		if (shopFacade.addNewProduct(product)) {
			ioProvider.getOutput().println("Product successfuly saved");
		} else {
			ioProvider.getOutput().println("Product NOT saved, something went wrong");
		}
	}
	
	private void printProductsTypes() {
		ioProvider.getOutput().println("Available types of products:");
		for (Entry<String, MyKeyValue<String ,Class<? extends Product>>> e : availableProducts.entrySet()) {
			ioProvider.getOutput().println(e.getKey() + " - " + e.getValue().getKey());
		}
	}

	@Override
	public String about() {
		return "add new product to store";
	}

	@Override
	public Map<String, AbstractCommand> getCommandsMap() {
		return null;
	}

}

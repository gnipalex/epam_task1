package com.epam.hnyp.task2.subtask3.command;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.epam.hnyp.task2.subtask3.creator.AbstractProductCreator;
import com.epam.hnyp.task2.subtask3.creator.AbstractProductCreator.CreateProductException;
import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.util.IOProvider;
import com.epam.hnyp.task2.subtask3.util.MyKeyValue;

public class AddProductCommand extends AbstractCommand {	
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
		ioProvider.printLine("Adding of new product to grocery.");
		printProductsTypes();
		ioProvider.printLine();
		ioProvider.print("Choice (or nothing to cancel) -> ");
		
		String line = ioProvider.readLine();
		if (line.isEmpty()) {
			return;
		}
		
		MyKeyValue<String, Class<? extends Product>> chosedType = availableProducts.get(String.valueOf(line.charAt(0)));
		if (chosedType == null) {
			ioProvider.printLine("wrong input, type with key " + line.charAt(0) + " not found");
			return;
		}
		
		Product product = null;
		try {
			product = productCreator.createProduct(chosedType.getValue());
		} catch (CreateProductException e) {
			ioProvider.printLine("##error product creating : " + e.getMessage());
			return;
		}
		
		ioProvider.printLine("Created new '" + chosedType.getKey() + "' : " + product.toString());
		if (shopFacade.addNewProduct(product)) {
			ioProvider.printLine("Product successfuly saved");
		} else {
			ioProvider.printLine("Product NOT saved, something went wrong");
		}
	}
	
	private void printProductsTypes() {
		ioProvider.printLine("Available types of products:");
		for (Entry<String, MyKeyValue<String ,Class<? extends Product>>> e : availableProducts.entrySet()) {
			ioProvider.printLine(e.getKey() + " - " + e.getValue().getKey());
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

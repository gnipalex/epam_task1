package com.epam.hnyp.task2.subtask3;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.command.AbstractCommand;
import com.epam.hnyp.task2.subtask3.command.MainMenuCommand;
import com.epam.hnyp.task2.subtask3.command.MyKeyValue;
import com.epam.hnyp.task2.subtask3.command.main.AddProductCommand;
import com.epam.hnyp.task2.subtask3.factory.ProductsInitializer;
import com.epam.hnyp.task2.subtask3.factory.ServicesContainer;
import com.epam.hnyp.task2.subtask3.factory.ServicesFactory;
import com.epam.hnyp.task2.subtask3.factory.ServicesFactoryInMemory;
import com.epam.hnyp.task2.subtask3.model.creator.ConsoleProductCreator;
import com.epam.hnyp.task2.subtask3.model.creator.RandomProductCreator;
import com.epam.hnyp.task2.subtask3.model.creator.ConsoleReflectionProductCreator;
import com.epam.hnyp.task2.subtask3.model.creator.ProductCreator;
import com.epam.hnyp.task2.subtask3.model.creator.RandomReflectionProductCreator;
import com.epam.hnyp.task2.subtask3.model.creator.__RandomProductCreator;
import com.epam.hnyp.task2.subtask3.serialize.GzipProductsSerializer;
import com.epam.hnyp.task2.subtask3.serialize.NtimesProductsSerializer;
import com.epam.hnyp.task2.subtask3.serialize.ProductsSerializer;

public class ConsoleGrocery {
	public static final String SERIALIZED_PRODUCTS_FILE = "products.dat";
	public static final String SERIALIZED_NTIMES_PRODUCTS_FILE = "products.ntimes.dat";
	public static final int SERIALIZE_COUNT = 3;
	public static final String SERIALIZED_GZIP_PRODUCTS_FILE = "products.gzip.dat";
	
	private static ServicesContainer servicesContainer;
	
	private static ProductsSerializer serializer = new ProductsSerializer();
	private static ProductsSerializer ntimesSerializer = new NtimesProductsSerializer(SERIALIZE_COUNT);
	private static ProductsSerializer gzipSerializer = new GzipProductsSerializer();
	
	private static String productCreatorImplClass;
	
	private static final Map<String, MyKeyValue<String, Class<? extends ProductCreator>>> productCreators = new LinkedHashMap<>();
	static {
		productCreators.put("1", new MyKeyValue<String, Class<? extends ProductCreator>>("from console mode", ConsoleProductCreator.class));
		productCreators.put("2", new MyKeyValue<String, Class<? extends ProductCreator>>("random mode", RandomProductCreator.class));
		productCreators.put("3", new MyKeyValue<String, Class<? extends ProductCreator>>("from console using annotations mode", ConsoleReflectionProductCreator.class));
		productCreators.put("4", new MyKeyValue<String, Class<? extends ProductCreator>>("random using annotations mode", RandomReflectionProductCreator.class));
		
	}
	
	private static void initServicesContainer() {
		ServicesFactory factory = new ServicesFactoryInMemory();
		servicesContainer = factory.buildServicesContainer(5);
	}
	
	private static void serializeAllProducts() {
		try {
			serializer.serialize(servicesContainer.getProductsService(), new File(SERIALIZED_PRODUCTS_FILE));
		} catch (IOException e) {
			System.out.println("--Error while writing products to file--");
		}
		try {
			ntimesSerializer.serialize(servicesContainer.getProductsService(), new File(SERIALIZED_NTIMES_PRODUCTS_FILE));
		} catch (IOException e) {
			System.out.println("--Error while writing products to file (ntimes)--");
		}
		try {
			gzipSerializer.serialize(servicesContainer.getProductsService(), new File(SERIALIZED_GZIP_PRODUCTS_FILE));
		} catch (IOException e) {
			System.out.println("--Error while writing products to file (gzip)--");
		}
	}
	
	private static void deserializeAllProducts() {
		try {
			serializer.deserialize(servicesContainer.getProductsService(), new File(SERIALIZED_PRODUCTS_FILE));
		} catch (IOException e) {
			System.out.println("--Error while reading products from file--");
		}
	}
	
	private static void chooseAddProductCreatorImpl() {
		System.out.println("Before grocery starts you should choose the mode of adding products :");
		for (Entry<String, MyKeyValue<String, Class<? extends ProductCreator>>> e : productCreators.entrySet()) {
			System.out.println(e.getKey() + " - " + e.getValue().getKey());
		}
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("Choice -> ");
			String line = sc.nextLine();
			if (line.isEmpty()) {
				continue;
			}
			MyKeyValue<String, Class<? extends ProductCreator>> entry = productCreators.get(String.valueOf(line.charAt(0)));
			if (entry == null) {
				System.out.println("wrong input, try again");
				continue;
			}
			System.out.println("chosen " + entry.getKey());
			System.out.println();
			productCreatorImplClass = entry.getValue().getName();
			break;
		}
	}
	
	
	public static void main(String[] args) {
		chooseAddProductCreatorImpl();
		
		initServicesContainer();

		deserializeAllProducts();
		
		//hardcode filling
		//ProductsInitializer.fillProducts(servicesContainer.getProductsService(), 1);
		
		AbstractCommand.services = servicesContainer;
		//grocery start
		AbstractCommand main = new MainMenuCommand();
		String addConfParam = AddProductCommand.CREATOR_CLASS_PARAM + ":" + productCreatorImplClass;
		main.execute(new String[] {addConfParam});
		
		serializeAllProducts();
	}
}

package com.epam.hnyp.task2.subtask3;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.command.AbstractCommand;
import com.epam.hnyp.task2.subtask3.creator.AbstractProductCreator;
import com.epam.hnyp.task2.subtask3.factory.ProductCreatorFactory;
import com.epam.hnyp.task2.subtask3.initializer.CommandInitializer;
import com.epam.hnyp.task2.subtask3.initializer.CommandInitializerUltra;
import com.epam.hnyp.task2.subtask3.initializer.ServicesInMemoryInitializer;
import com.epam.hnyp.task2.subtask3.initializer.ServicesInitializer;
import com.epam.hnyp.task2.subtask3.initializer.ServicesInitializer.ServicesContainer;
import com.epam.hnyp.task2.subtask3.serialize.GzipProductsSerializer;
import com.epam.hnyp.task2.subtask3.serialize.NtimesProductsSerializer;
import com.epam.hnyp.task2.subtask3.serialize.ProductsSerializer;
import com.epam.hnyp.task2.subtask3.service.ProductsService;
import com.epam.hnyp.task2.subtask3.util.ConsoleIOProvider;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class ConsoleGrocery {
	public static final String SERIALIZED_PRODUCTS_FILE = "products.dat";
	public static final String SERIALIZED_NTIMES_PRODUCTS_FILE = "products.ntimes.dat";
	public static final int SERIALIZE_COUNT = 3;
	public static final String SERIALIZED_GZIP_PRODUCTS_FILE = "products.gzip.dat";

	public static final String RESOURCES_FILE = "modelFieldsNames";
	public static final String LOCATION = "ru";

	private static ProductsSerializer serializer = new ProductsSerializer();
	private static ProductsSerializer ntimesSerializer = new NtimesProductsSerializer(
			SERIALIZE_COUNT);
	private static ProductsSerializer gzipSerializer = new GzipProductsSerializer();

	private static ServicesContainer initServicesContainer() {
		ServicesInitializer initializer = new ServicesInMemoryInitializer();
		return initializer.buildServicesContainer(5);
	}

	private static void serializeAllProducts(ProductsService prodService,
			IOProvider ioProvider) {
		try {
			serializer.serialize(prodService,
					new File(SERIALIZED_PRODUCTS_FILE));
		} catch (IOException e) {
			ioProvider.getOutput().println(
					"--Error while writing products to file--");
		}
		try {
			ntimesSerializer.serialize(prodService, new File(
					SERIALIZED_NTIMES_PRODUCTS_FILE));
		} catch (IOException e) {
			ioProvider.getOutput().println(
					"--Error while writing products to file (ntimes)--");
		}
		try {
			gzipSerializer.serialize(prodService, new File(
					SERIALIZED_GZIP_PRODUCTS_FILE));
		} catch (IOException e) {
			ioProvider.getOutput().println(
					"--Error while writing products to file (gzip)--");
		}
	}

	private static void deserializeAllProducts(ProductsService prodService,
			IOProvider ioProvider) {
		try {
			serializer.deserialize(prodService,
					new File(SERIALIZED_PRODUCTS_FILE));
		} catch (IOException e) {
			ioProvider.getOutput().println("--Error while reading products from file--");
		}
	}

	private static AbstractProductCreator chooseProductCreatorImpl(
			IOProvider ioProvider) {
		AbstractProductCreator productCreator = null;
		ioProvider
				.getOutput()
				.println(
						"Before grocery starts you should choose the mode of adding products :");
		ioProvider.getOutput().println("1 - from console mode");
		ioProvider.getOutput().println("2 - random mode");
		ioProvider.getOutput().println(
				"3 - from console using annotations mode");
		ioProvider.getOutput().println("4 - random using annotations mode");
		Scanner sc = new Scanner(ioProvider.getInput());
		outer: while (true) {
			ioProvider.getOutput().print("Choice -> ");
			String line = sc.nextLine();
			if (line.isEmpty()) {
				continue;
			}
			switch (line.charAt(0)) {
			case '1':
				productCreator = ProductCreatorFactory
						.newSimpleProductCreator(ioProvider);
				break;
			case '2':
				productCreator = ProductCreatorFactory
						.newSimpleRandomProductCreator(ioProvider);
				break;
			case '3':
				productCreator = ProductCreatorFactory
						.newReflectionProductCreator(ioProvider,
								ResourceBundle.getBundle(RESOURCES_FILE,
										new Locale(LOCATION)));
				break;
			case '4':
				productCreator = ProductCreatorFactory
						.newReflectionRandomProductCreator(ioProvider,
								ResourceBundle.getBundle(RESOURCES_FILE,
										new Locale(LOCATION)));
				break;
			default:
				continue outer;
			}
			break;
		}
		return productCreator;
	}

	public static void main(String[] args) {
		IOProvider ioProvider = new ConsoleIOProvider();

		AbstractProductCreator prodCreator = chooseProductCreatorImpl(ioProvider);

		ServicesContainer servicesContainer = initServicesContainer();

		deserializeAllProducts(servicesContainer.getProductsService(), ioProvider);
		
		// hardcode filling
		// ProductsInitializer.fillProducts(servicesContainer.getProductsService(),
		// 1);

//		CommandInitializer commandInitializer = new CommandInitializerOrdinary(
//				servicesContainer.getShopFacade(), ioProvider);
		
		CommandInitializer commandInitializer = new CommandInitializerUltra(servicesContainer.getShopFacade(),
				ioProvider, prodCreator);

		AbstractCommand shopCommand = commandInitializer.initMainCommand();
		shopCommand.execute();

		serializeAllProducts(servicesContainer.getProductsService(), ioProvider);
	}
}

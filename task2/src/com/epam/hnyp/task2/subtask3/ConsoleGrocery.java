package com.epam.hnyp.task2.subtask3;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

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
import com.epam.hnyp.task7.subtask1.facade.ProductsFacade;
import com.epam.hnyp.task7.subtask1.facade.ProductsShopFacade;
import com.epam.hnyp.task7.subtask1.factory.SimpleRequestHandlerFactory;
import com.epam.hnyp.task7.subtask1.server.Server;
import com.epam.hnyp.task7.subtask3.factory.RequestHandlerFactory;

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
	
	private static final int SERVER_PORT = 3000;

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
			ioProvider.printLine("--Error while writing products to file--");
		}
		try {
			ntimesSerializer.serialize(prodService, new File(
					SERIALIZED_NTIMES_PRODUCTS_FILE));
		} catch (IOException e) {
			ioProvider
					.printLine("--Error while writing products to file (ntimes)--");
		}
		try {
			gzipSerializer.serialize(prodService, new File(
					SERIALIZED_GZIP_PRODUCTS_FILE));
		} catch (IOException e) {
			ioProvider
					.printLine("--Error while writing products to file (gzip)--");
		}
	}

	private static void deserializeAllProducts(ProductsService prodService,
			IOProvider ioProvider) {
		try {
			serializer.deserialize(prodService, new File(
					SERIALIZED_PRODUCTS_FILE));
		} catch (IOException e) {
			ioProvider.printLine("--Error while reading products from file--");
		}
	}

	private static AbstractProductCreator chooseProductCreatorImpl(
			IOProvider ioProvider) {
		AbstractProductCreator productCreator = null;
		ioProvider
				.printLine("Before grocery starts you should choose the mode of adding products :");
		ioProvider.printLine("1 - from console mode");
		ioProvider.printLine("2 - random mode");
		ioProvider.printLine("3 - from console using annotations mode");
		ioProvider.printLine("4 - random using annotations mode");
		String line = null;
		boolean inputError = false;
		do {
			inputError = false;
			ioProvider.print("Choice -> ");
			line = ioProvider.readLine();
			if (!line.isEmpty()) {
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
					inputError = true;
				}
			}
		} while (line.isEmpty() || inputError);
		return productCreator;
	}
	
	public static void startServer(ProductsService prodService) {
		ProductsFacade prodFacade = new ProductsShopFacade(prodService);
		RequestHandlerFactory reqHandlerFactory = new SimpleRequestHandlerFactory(prodFacade);
		Thread serverThread = new Thread(new Server(SERVER_PORT, reqHandlerFactory));
		serverThread.setDaemon(true);
		serverThread.start();
	}

	public static void main(String[] args) {
		IOProvider ioProvider = new ConsoleIOProvider();

		AbstractProductCreator prodCreator = chooseProductCreatorImpl(ioProvider);

		ServicesContainer servicesContainer = initServicesContainer();

		deserializeAllProducts(servicesContainer.getProductsService(),
				ioProvider);

		// hardcode filling
		// ProductsInitializer.fillProducts(servicesContainer.getProductsService(),
		// 1);

		startServer(servicesContainer.getProductsService());
		ioProvider.printLine("--server started at port " + SERVER_PORT + "--");
		
		// CommandInitializer commandInitializer = new
		// CommandInitializerOrdinary(
		// servicesContainer.getShopFacade(), ioProvider);

		CommandInitializer commandInitializer = new CommandInitializerUltra(
				servicesContainer.getShopFacade(), ioProvider, prodCreator);

		AbstractCommand shopCommand = commandInitializer.initMainCommand();
		shopCommand.execute();

		serializeAllProducts(servicesContainer.getProductsService(), ioProvider);
	}
}

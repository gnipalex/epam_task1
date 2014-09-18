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
import com.epam.hnyp.task2.subtask3.command.main.AddGoodCommand;
import com.epam.hnyp.task2.subtask3.factory.GoodsInitializer;
import com.epam.hnyp.task2.subtask3.factory.ServicesContainer;
import com.epam.hnyp.task2.subtask3.factory.ServicesFactory;
import com.epam.hnyp.task2.subtask3.factory.ServicesFactoryInMemory;
import com.epam.hnyp.task2.subtask3.model.creator.ConsoleGoodCreator;
import com.epam.hnyp.task2.subtask3.model.creator.GoodCreator;
import com.epam.hnyp.task2.subtask3.model.creator.RandomGoodCreator;
import com.epam.hnyp.task2.subtask3.serialize.GoodsSerializer;
import com.epam.hnyp.task2.subtask3.serialize.GzipGoodsSerializer;
import com.epam.hnyp.task2.subtask3.serialize.NtimesGoodsSerializer;

public class ConsoleGrocery {
	public static final String SERIALIZED_GOODS_FILE = "goods.dat";
	public static final String SERIALIZED_NTIMES_GOODS_FILE = "goods.ntimes.dat";
	public static final int SERIALIZE_COUNT = 3;
	public static final String SERIALIZED_GZIP_GOODS_FILE = "goods.gzip.dat";
	
	private static ServicesContainer servicesContainer;
	
	private static GoodsSerializer serializer = new GoodsSerializer();
	private static GoodsSerializer ntimesSerializer = new NtimesGoodsSerializer(SERIALIZE_COUNT);
	private static GoodsSerializer gzipSerializer = new GzipGoodsSerializer();
	
	private static String goodCreatorImplClass;
	
	private static final Map<String, MyKeyValue<String, Class<? extends GoodCreator>>> goodCreators = new LinkedHashMap<>();
	static {
		goodCreators.put("1", new MyKeyValue<String, Class<? extends GoodCreator>>("from console mode", ConsoleGoodCreator.class));
		goodCreators.put("2", new MyKeyValue<String, Class<? extends GoodCreator>>("random mode", RandomGoodCreator.class));
	}
	
	private static void initServicesContainer() {
		ServicesFactory factory = new ServicesFactoryInMemory();
		servicesContainer = factory.buildServicesContainer(5);
	}
	
	private static void serializeAllGoods() {
		try {
			serializer.serialize(servicesContainer.getGoodsService(), new File(SERIALIZED_GOODS_FILE));
		} catch (IOException e) {
			System.out.println("--Error while writing goods to file--");
		}
		try {
			ntimesSerializer.serialize(servicesContainer.getGoodsService(), new File(SERIALIZED_NTIMES_GOODS_FILE));
		} catch (IOException e) {
			System.out.println("--Error while writing goods to file (ntimes)--");
		}
		try {
			gzipSerializer.serialize(servicesContainer.getGoodsService(), new File(SERIALIZED_GZIP_GOODS_FILE));
		} catch (IOException e) {
			System.out.println("--Error while writing goods to file (gzip)--");
		}
	}
	
	private static void deserializeAllGoods() {
		try {
			serializer.deserialize(servicesContainer.getGoodsService(), new File(SERIALIZED_GOODS_FILE));
		} catch (IOException e) {
			System.out.println("--Error while reading goods from file--");
		}
	}
	
	private static void chooseAddGoodCreatorImpl() {
		System.out.println("Before grocery starts you should choose the mode of adding goods :");
		for (Entry<String, MyKeyValue<String, Class<? extends GoodCreator>>> e : goodCreators.entrySet()) {
			System.out.println(e.getKey() + " - " + e.getValue().getKey());
		}
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("Choice -> ");
			String line = sc.nextLine();
			if (line.isEmpty()) {
				continue;
			}
			MyKeyValue<String, Class<? extends GoodCreator>> entry = goodCreators.get(String.valueOf(line.charAt(0)));
			if (entry == null) {
				System.out.println("wrong input, try again");
				continue;
			}
			System.out.println("chosen " + entry.getKey());
			System.out.println();
			goodCreatorImplClass = entry.getValue().getName();
			break;
		}
	}
	
	
	public static void main(String[] args) {
		chooseAddGoodCreatorImpl();
		
		initServicesContainer();
		
		deserializeAllGoods();
		
		//hardcode filling
		//GoodsInitializer.fillGoods(servicesContainer.getGoodsService(), 1);
		
		AbstractCommand.services = servicesContainer;
		//grocery start
		AbstractCommand main = new MainMenuCommand();
		String addConfParam = AddGoodCommand.CREATOR_CLASS_PARAM + ":" + goodCreatorImplClass;
		main.execute(new String[] {addConfParam});
		
		serializeAllGoods();
	}
}

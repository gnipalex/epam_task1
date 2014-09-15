package com.epam.hnyp.task2.subtask3;

import java.io.File;
import java.io.IOException;

import com.epam.hnyp.task2.subtask3.command.AbstractCommand;
import com.epam.hnyp.task2.subtask3.command.MainMenuCommand;
import com.epam.hnyp.task2.subtask3.factory.GoodsInitializer;
import com.epam.hnyp.task2.subtask3.factory.ServicesContainer;
import com.epam.hnyp.task2.subtask3.factory.ServicesFactory;
import com.epam.hnyp.task2.subtask3.factory.ServicesFactoryInMemory;
import com.epam.hnyp.task2.subtask3.serialize.GoodsSerializer;
import com.epam.hnyp.task2.subtask3.serialize.GzipGoodsSerializer;
import com.epam.hnyp.task2.subtask3.serialize.NtimesGoodsSerializer;

public class ConsoleGrocery {
	public static final String SERIALIZED_GOODS_FILE = "goods.dat";
	public static final String SERIALIZED_NTIMES_GOODS_FILE = "goods.ntimes.dat";
	public static final int SERIALIZE_COUNT = 3;
	public static final String SERIALIZED_GZIP_GOODS_FILE = "goods.gzip.dat";
	
	public static void main(String[] args) {
		ServicesFactory factory = new ServicesFactoryInMemory();
		ServicesContainer servicesContainer = factory.buildServicesContainer(5);
		
		GoodsSerializer serializer = new GoodsSerializer();
		GoodsSerializer ntimesSerializer = new NtimesGoodsSerializer(SERIALIZE_COUNT);
		GoodsSerializer gzipSerializer = new GzipGoodsSerializer();
		File datFile = new File(SERIALIZED_GOODS_FILE);
		File ntimesFile = new File(SERIALIZED_NTIMES_GOODS_FILE);
		File gzipFile = new File(SERIALIZED_GZIP_GOODS_FILE);

		try {
			serializer.deserialize(servicesContainer.getGoodsService(), datFile);
		} catch (IOException e) {
			System.out.println("--Error while reading goods from file--");
		}
		
		//hardcode filling
		//GoodsInitializer.fillGoods(servicesContainer.getGoodsService(), 1);
		
		AbstractCommand.services = servicesContainer;
		//grocery start
		AbstractCommand main = new MainMenuCommand();
		main.execute(new String[0]);
		
		try {
			serializer.serialize(servicesContainer.getGoodsService(), datFile);
		} catch (IOException e) {
			System.out.println("--Error while writing goods to file--");
		}
		try {
			ntimesSerializer.serialize(servicesContainer.getGoodsService(), ntimesFile);
		} catch (IOException e) {
			System.out.println("--Error while writing goods to file (ntimes)--");
		}
		try {
			gzipSerializer.serialize(servicesContainer.getGoodsService(), gzipFile);
		} catch (IOException e) {
			System.out.println("--Error while writing goods to file (gzip)--");
		}
	}
}

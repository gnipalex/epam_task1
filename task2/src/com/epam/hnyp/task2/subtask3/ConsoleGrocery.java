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

public class ConsoleGrocery {
	public static final String SERIALIZED_GOODS_FILE = "goods.dat";
	
	public static void main(String[] args) {
		GoodsSerializer serializer = new GoodsSerializer();
		File datFile = new File(SERIALIZED_GOODS_FILE);
		
		ServicesFactory factory = new ServicesFactoryInMemory();
		ServicesContainer servicesContainer = factory.buildServicesContainer(5);
		
		try {
			serializer.deserialize(servicesContainer.getGoodsService(), datFile);
		} catch (IOException e) {
			System.out.println("--Error while reading goods from file--");
		}
		
		//GoodsInitializer.fillGoods(servicesContainer.getGoodsService(), 1);
		
		AbstractCommand.services = servicesContainer;
		
		AbstractCommand main = new MainMenuCommand();
		main.execute(new String[0]);
		
		try {
			serializer.serialize(servicesContainer.getGoodsService(), datFile);
		} catch (IOException e) {
			System.out.println("--Error while writing goods to file--");
		}
	}
}

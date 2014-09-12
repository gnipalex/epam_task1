package com.epam.hnyp.task2.subtask3.factory;

import com.epam.hnyp.task2.subtask3.model.CerealGood;
import com.epam.hnyp.task2.subtask3.model.DrinkGood;
import com.epam.hnyp.task2.subtask3.model.Good;
import com.epam.hnyp.task2.subtask3.model.SweetGood;
import com.epam.hnyp.task2.subtask3.model.VegetableGood;
import com.epam.hnyp.task2.subtask3.model.WeightableGood;
import com.epam.hnyp.task2.subtask3.service.GoodsService;

public class GoodsInitializer {
	public static void fillGoods(GoodsService goodsService, long idStart) {
		Good g1 = new CerealGood(idStart++, "sugar", 12, 2);
		goodsService.add(g1);
		Good g2 = new DrinkGood(idStart++, "milk", 11, 1);
		goodsService.add(g2);
		Good g3 = new SweetGood(idStart++, "vafles artek", 30, 0.2, "chocolate");
		goodsService.add(g3);
		Good g4 = new VegetableGood(idStart++, "potato", 4, 1);
		goodsService.add(g4);
		Good g5 = new WeightableGood(idStart++, "bread", 5, 0.7);
		goodsService.add(g5);
		Good g6 = new DrinkGood(idStart++, "cola", 12, 2);
		goodsService.add(g6);
		Good g7 = new Good(idStart++, "sigarettes", 20);
		goodsService.add(g7);
		Good g8 = new Good(idStart++, "butter", 15);
		goodsService.add(g8);
	}
}

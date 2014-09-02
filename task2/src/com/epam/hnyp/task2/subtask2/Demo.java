package com.epam.hnyp.task2.subtask2;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Demo {
	public static void main(String[] args) {
		Map<StringKeyWraper, Integer> map1 = new HashMap<>();
		map1.put(new StringKeyWraperLength("ke1y"), 1);
		map1.put(new StringKeyWraperLength("key1"), 2);
		map1.put(new StringKeyWraperLength("key1key2"), 3);
		map1.put(new StringKeyWraperLength("key1key"), 4);
		map1.put(new StringKeyWraperLength("key1keyy"), 5);
		map1.put(new StringKeyWraperLength("key1ke1"), 6);
		map1.put(new StringKeyWraperLength("key1ke2"), 7);
		map1.put(new StringKeyWraperLength("key1ke3"), 8);
		map1.put(new StringKeyWraperLength("key1ke4"), 9);
		
		System.out.println("Elements : 1 2 3 4 5 6 7 8 9");
		System.out.println("----------------------");
		System.out.println("HashMap,key = StringKeyWraperLength:");
		for (Integer i : map1.values()) {
			System.out.print(i + " ");
		}
		System.out.println();
		/////////////////////////////////////////
		Map<StringKeyWraper, Integer> map2 = new HashMap<>();
		map2.put(new StringKeyWraperCodes("key1"), 1);
		map2.put(new StringKeyWraperCodes("key2"), 2);
		map2.put(new StringKeyWraperCodes("key1key2"), 3);
		map2.put(new StringKeyWraperCodes("key1key"), 4);
		map2.put(new StringKeyWraperCodes("key1keyy"), 5);
		map2.put(new StringKeyWraperCodes("key1ke1"), 6);
		map2.put(new StringKeyWraperCodes("key1ke2"), 7);
		map2.put(new StringKeyWraperCodes("key1ke3"), 8);
		map2.put(new StringKeyWraperCodes("key1ke4"), 9);
		
		System.out.println("----------------------");
		System.out.println("HashMap,key = StringKeyWraperCodes:");
		for (Integer i : map2.values()) {
			System.out.print(i + " ");
		}
		System.out.println();
		/////////////////////////////////////////////
		Map<StringKeyWraper, Integer> map3 = new LinkedHashMap<>();
		map3.put(new StringKeyWraperLength("ke1y"), 1);
		map3.put(new StringKeyWraperLength("key1"), 2);
		map3.put(new StringKeyWraperLength("key1key2"), 3);
		map3.put(new StringKeyWraperLength("key1key"), 4);
		map3.put(new StringKeyWraperLength("key1keyy"), 5);
		map3.put(new StringKeyWraperLength("key1ke1"), 6);
		map3.put(new StringKeyWraperLength("key1ke2"), 7);
		map3.put(new StringKeyWraperLength("key1ke3"), 8);
		map3.put(new StringKeyWraperLength("key1ke4"), 9);
		
		System.out.println("----------------------");
		System.out.println("LinkedHashMap,key = StringKeyWraperLength:");
		for (Integer i : map3.values()) {
			System.out.print(i + " ");
		}
		System.out.println();
		//////////////////////////////////////////////
		Map<StringKeyWraper, Integer> map4 = new LinkedHashMap<>();
		map4.put(new StringKeyWraperCodes("key1"), 1);
		map4.put(new StringKeyWraperCodes("key2"), 2);
		map4.put(new StringKeyWraperCodes("key1key2"), 3);
		map4.put(new StringKeyWraperCodes("key1key"), 4);
		map4.put(new StringKeyWraperCodes("key1keyy"), 5);
		map4.put(new StringKeyWraperCodes("key1ke1"), 6);
		map4.put(new StringKeyWraperCodes("key1ke2"), 7);
		map4.put(new StringKeyWraperCodes("key1ke3"), 8);
		map4.put(new StringKeyWraperCodes("key1ke4"), 9);
		
		System.out.println("----------------------");
		System.out.println("LinkedHashMap,key = StringKeyWraperCodes:");
		for (Integer i : map4.values()) {
			System.out.print(i + " ");
		}
		System.out.println();
		
	}
}

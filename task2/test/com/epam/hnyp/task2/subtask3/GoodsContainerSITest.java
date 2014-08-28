package com.epam.hnyp.task2.subtask3;

import static org.junit.Assert.*;

import org.junit.Test;

public class GoodsContainerSITest {

	@Test
	public void testBridgeCreating() {
		GoodsContainerSI<Integer> items = new GoodsContainerSI<>();
		ListIteratorBridge<Integer> b1 = items.getBridge();
		items.add(5);
		items.add(7);
		items.add(6);
		items.add(8);
		items.add(9);
		int sz = items.size();
		ListIteratorBridge<Integer> b2 = items.getBridge();
		
		assertTrue(b1.getList() == b2.getList());
		
		ParameterizedIteratorSI<Integer> it1 = (ParameterizedIteratorSI<Integer>)items.iterator();
		
		assertTrue(it1.getBridge().getList() == items);
		
		items.add(10);
		
		ParameterizedIteratorSI<Integer> it2 = (ParameterizedIteratorSI<Integer>)items.iterator();
		
		assertTrue(it1.getBridge().getList() != items);
		assertTrue(it1.getBridge().getList().size() == sz);
		assertTrue(it2.getBridge().getList() == items);
		assertTrue(it2.getBridge().getList() != it1.getBridge().getList());
	}

}

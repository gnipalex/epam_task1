package com.epam.hnyp.task1.subtask3;

import static org.junit.Assert.*;

import org.junit.Test;

import com.epam.hnyp.task1.subtask3.GoodsStableIteratorList;
import com.epam.hnyp.task1.subtask3.ListIteratorBridge;
import com.epam.hnyp.task1.subtask3.ParameterizedStableIterator;

public class GoodsStableIteratorListTest {

	@Test
	public void testBridgeCreating() {
		GoodsStableIteratorList<Integer> items = new GoodsStableIteratorList<>();
		ListIteratorBridge<Integer> b1 = items.getBridge();
		items.add(5);
		items.add(7);
		items.add(6);
		items.add(8);
		items.add(9);
		int sz = items.size();
		ListIteratorBridge<Integer> b2 = items.getBridge();
		
		assertTrue(b1.getList() == b2.getList());
		
		ParameterizedStableIterator<Integer> it1 = (ParameterizedStableIterator<Integer>)items.iterator();
		
		assertTrue(it1.getBridge().getList() == items);
		
		items.add(10);
		
		ParameterizedStableIterator<Integer> it2 = (ParameterizedStableIterator<Integer>)items.iterator();
		
		assertTrue(it1.getBridge().getList() != items);
		assertTrue(it1.getBridge().getList().size() == sz);
		assertTrue(it2.getBridge().getList() == items);
		assertTrue(it2.getBridge().getList() != it1.getBridge().getList());
	}

}

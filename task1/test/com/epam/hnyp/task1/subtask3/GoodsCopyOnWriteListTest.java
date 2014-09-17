package com.epam.hnyp.task1.subtask3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.epam.hnyp.task1.subtask3.GoodsCopyOnWriteList;
import com.epam.hnyp.task1.subtask3.ListIteratorBridge;
import com.epam.hnyp.task1.subtask3.ParameterizedCopyOnWriteIterator;

public class GoodsCopyOnWriteListTest {

	private GoodsCopyOnWriteList<Integer> items;
	
	@Before
	public void before() {
		items = new GoodsCopyOnWriteList<>();
		items.add(5);
		items.add(7);
		items.add(6);
	}
	
	@Test
	public void testBridgeConstantNoIterators() {
		ListIteratorBridge<Integer> b1 = items.getBridge();
		items.add(8);
		items.add(9);
		ListIteratorBridge<Integer> b2 = items.getBridge();
		
		assertTrue(b1.getList() == b2.getList());
		
		ParameterizedCopyOnWriteIterator<Integer> it1 = (ParameterizedCopyOnWriteIterator<Integer>)items.iterator();
		
		assertTrue(it1.getBridge().getList() == items);
	}
	
	@Test
	public void testBridgeChangedHasIterators() {
		ListIteratorBridge<Integer> b1 = items.getBridge();
		ParameterizedCopyOnWriteIterator<Integer> it1 = (ParameterizedCopyOnWriteIterator<Integer>)items.iterator();
		items.add(8);
		ListIteratorBridge<Integer> b2 = items.getBridge();
		assertFalse(b1 == b2);
		assertFalse(b1.getList() == items);
	}
	
	
	

}

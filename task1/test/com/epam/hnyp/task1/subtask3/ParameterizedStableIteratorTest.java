package com.epam.hnyp.task1.subtask3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.epam.hnyp.task1.subtask3.GoodsStableIteratorList;
import com.epam.hnyp.task1.subtask3.ParameterizedStableIterator;

public class ParameterizedStableIteratorTest {

	private GoodsStableIteratorList<Integer> items;
	
	@Before
	public void before() {
		items = new GoodsStableIteratorList<>();
		items.add(5);
		items.add(7);//
		items.add(6);
	}
	
	@Test
	public void testIteratorDataUnmodifiedOnListModify() {
		int sz = items.size();
		
		ParameterizedStableIterator<Integer> it1 = (ParameterizedStableIterator<Integer>)items.iterator();
		
		it1.hasNext();
		assertTrue(it1.next().equals(5));
		
		//removing 7 from list
		items.remove(1);
		
		//old iterators still see 7
		it1.hasNext();
		assertTrue(it1.next().equals(7));
	}
	
	@Test
	public void testIteratorHasSameBridgeBeforeMod() {
		ParameterizedStableIterator<Integer> it1 = (ParameterizedStableIterator<Integer>)items.iterator();
		ParameterizedStableIterator<Integer> it2 = (ParameterizedStableIterator<Integer>)items.iterator();
		
		assertTrue(it1.getBridge() == it2.getBridge());
		assertTrue(it1.getBridge() == items.getBridge());
//		assertTrue(it2.getBridge() == items.getBridge());
		assertTrue(it1.getBridge().getList() == items);
//		assertTrue(it2.getBridge().getList() == items);
	}
	
	@Test
	public void testIteratorHasOtherBridgeAfterMod() {
		ParameterizedStableIterator<Integer> it1 = (ParameterizedStableIterator<Integer>)items.iterator();
		ParameterizedStableIterator<Integer> it2 = (ParameterizedStableIterator<Integer>)items.iterator();
		
		//removing 7 from list
		items.remove(1);
		
		assertTrue(it1.getBridge() == it2.getBridge());
		assertTrue(it1.getBridge() != items.getBridge());
//		assertTrue(it1.getBridge().getList() != items.getBridge().getList());
//		assertTrue(it1.getBridge().getList() == it2.getBridge().getList());
		assertTrue(it1.getBridge().getList() != items);
	}

}

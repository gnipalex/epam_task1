package com.epam.hnyp.task1.subtask3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.epam.hnyp.task1.subtask3.GoodsCopyOnWriteList;
import com.epam.hnyp.task1.subtask3.ParameterizedCopyOnWriteIterator;

public class ParameterizedCopyOnWriteIteratorTest {

	private GoodsCopyOnWriteList<Integer> items;
	
	private final Integer magicValue = 7;
	
	
	@Before
	public void before() {
		items = new GoodsCopyOnWriteList<>();
		items.add(5);
		items.add(magicValue);
		items.add(6);
	}
	
	@Test
	public void testIteratorDataUnmodifiedOnListModify() {
		ParameterizedCopyOnWriteIterator<Integer> it1 = (ParameterizedCopyOnWriteIterator<Integer>)items.iterator();
		it1.next();
		items.remove(magicValue);
		assertEquals(magicValue, it1.next());
	}
	
	@Test
	public void testIteratorHasSameBridgeBeforeMod() {
		ParameterizedCopyOnWriteIterator<Integer> it1 = (ParameterizedCopyOnWriteIterator<Integer>)items.iterator();
		ParameterizedCopyOnWriteIterator<Integer> it2 = (ParameterizedCopyOnWriteIterator<Integer>)items.iterator();
		
		assertSame(it1.getBridge(), it2.getBridge());
		assertSame(it1.getBridge(), items.getBridge());
		assertSame(it1.getBridge().getList(), items);
	}
	
	@Test
	public void testIteratorHasOtherBridgeAfterMod() {
		ParameterizedCopyOnWriteIterator<Integer> it1 = (ParameterizedCopyOnWriteIterator<Integer>)items.iterator();
		ParameterizedCopyOnWriteIterator<Integer> it2 = (ParameterizedCopyOnWriteIterator<Integer>)items.iterator();
		
		items.remove(0);
		
		assertSame(it1.getBridge(), it2.getBridge());
		assertNotSame(it1.getBridge(), items.getBridge());
		assertNotSame(it1.getBridge().getList(), items);
	}

}

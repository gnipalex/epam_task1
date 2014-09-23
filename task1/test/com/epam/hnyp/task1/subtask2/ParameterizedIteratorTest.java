package com.epam.hnyp.task1.subtask2;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class ParameterizedIteratorTest {

	private GoodsList<Integer> list;
	
	@Before
	public void before() {
		list = new GoodsList<>();
		list.add(1);
		list.add(2);
		list.add(3);
	}
	
	@Test
	public void testCondition() {
		Condition<Integer> cond = new Condition<Integer>() {
			@Override
			public boolean satisfy(Integer item) {
				return item > 2;
			}
		};
		list.setIteratorCondition(cond);
		Iterator<Integer> iter = list.iterator();
		assertTrue(iter.hasNext());
		assertEquals(new Integer(3), iter.next());
	}
	
	
	
	@Test
	public void testConditionAllNotSatisfy() {
		Condition<Integer> cond = new Condition<Integer>() {
			@Override
			public boolean satisfy(Integer item) {
				return item < 0;
			}
		};
		list.setIteratorCondition(cond);
		int count = 0;
		Iterator<Integer> iter = list.iterator();
		assertFalse(iter.hasNext());
	}
	
	@Test
	public void testHasNext() {
		Iterator<Integer> iter = list.iterator();
		assertTrue(iter.hasNext());
	}
	
	@Test
	public void testHasNextDoesNotShiftCursor() {
		Iterator<Integer> iter = list.iterator();
		assertTrue(iter.hasNext());
		assertTrue(iter.hasNext());
		assertEquals(new Integer(1), iter.next());
	}
	
	@Test
	public void testNext() {
		Iterator<Integer> iter = list.iterator();
		assertEquals(new Integer(1), iter.next());
		assertEquals(new Integer(2), iter.next());
		assertEquals(new Integer(3), iter.next());
	}
	
	@Test
	public void testNextNoSuchElementException(){
		Iterator<Integer> iter = list.iterator();
		iter.next();
		iter.next();
		iter.next();
		try {
			iter.next();
			fail();
		} catch (NoSuchElementException e) {}
	}
	
	
	
	
}

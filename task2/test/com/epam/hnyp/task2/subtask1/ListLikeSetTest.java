package com.epam.hnyp.task2.subtask1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.epam.hnyp.task2.subtask1.ListLikeSet.NonUniqueElementException;

public class ListLikeSetTest {

	private ListLikeSet<Integer> list; 
	
	@Before
	public void before() {
		list = new ListLikeSet<>(); 
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
	}
	
	@Test
	public void testAddE() {
		assertTrue(list.add(6));
		assertFalse(list.add(1));
	}

	@Test
	public void testAddIntE() {
		list.add(0, 0);
		try {
			list.add(0, 0);
		} catch (NonUniqueElementException e) {}
	}

	@Test
	public void testAddAllCollectionOfQextendsE() {
		List<Integer> c1 = new ArrayList<>();
		c1.add(6);
		c1.add(7);
		c1.add(8);
		c1.add(9);
		int sz = list.size();
		assertTrue(list.addAll(c1));
		assertTrue(list.size() == sz + c1.size());
		
		List<Integer> c2 = new ArrayList<>();
		c2.add(10);
		c2.add(11);
		c2.add(8);
		c2.add(9);
		sz = list.size();
		assertTrue(list.addAll(c2));
		assertTrue(list.size() == sz + 2);
		
		assertFalse(list.addAll(c2));
	}

	@Test
	public void testAddAllIntCollectionOfQextendsE() {
		List<Integer> c1 = new ArrayList<>();
		c1.add(6);
		c1.add(7);
		c1.add(8);
		c1.add(9);
		int sz = list.size();
		assertTrue(list.addAll(1 , c1));
		assertTrue(list.size() == sz + c1.size());
		assertTrue(list.get(1).equals(c1.get(0)));
		
		List<Integer> c2 = new ArrayList<>();
		c2.add(10);
		c2.add(11);
		c2.add(8);
		c2.add(9);
		sz = list.size();
		assertTrue(list.addAll(0, c2));
		assertTrue(list.size() == sz + 2);
		assertTrue(list.get(0).equals(c2.get(0)));
		
		assertFalse(list.addAll(0, c2));
	}

	@Test
	public void testSetIntE() {
		assertTrue(list.set(0, 0).equals(1));
		assertTrue(list.set(0, 0).equals(0));
		try {
			list.set(0, 3);
			fail();
		} catch (NonUniqueElementException e) {}
	}

}

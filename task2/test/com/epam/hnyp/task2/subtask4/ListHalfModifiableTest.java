package com.epam.hnyp.task2.subtask4;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ListHalfModifiableTest {

	private ListHalfModifiable<Integer> halfList;
	private List<Integer> list1;
	private List<Integer> list2;
	
	@Before
	public void createList(){
		list1 = new ArrayList<>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(4);
		
		list2 = new ArrayList<>();
		list2.add(5);
		list2.add(6);
		list2.add(3);
		list2.add(3);
		list2.add(4);
		
		halfList = new ListHalfModifiable<>(list1, list2);
	}

	@Test
	public void testSize() {
		assertTrue(halfList.size() == list1.size() + list2.size());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(halfList.isEmpty());
		ListHalfModifiable<Integer> list = new ListHalfModifiable<>(new ArrayList<Integer>(), new ArrayList<Integer>());
		assertTrue(list.isEmpty());
		list.add(2);
		assertFalse(list.isEmpty());
	}

	@Test
	public void testContains() {
		assertTrue(halfList.contains(1));
		assertTrue(halfList.contains(3));
		assertTrue(halfList.contains(6));
		assertFalse(halfList.contains(0));
	}

	@Test
	public void testGet() {
		assertTrue(halfList.get(0).equals(1));
		assertTrue(halfList.get(4).equals(4));
		assertTrue(halfList.get(8).equals(4));
		try {
			halfList.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {}
		try {
			halfList.get(halfList.size());
			fail();
		} catch (IndexOutOfBoundsException e) {}
	}
	
	@Test
	public void testToArray() {
		Object[] mas = halfList.toArray();
		assertTrue(mas.length == halfList.size());
		assertTrue( ((Integer)mas[0]).equals(1) );
		assertTrue( ((Integer)mas[mas.length - 1]).equals(4) );
	}

	@Test
	public void testToArrayTArray() {
		Integer[] mas = new Integer[halfList.size()];
		Integer[] res = halfList.toArray(mas);
		
		assertTrue(mas == res);
		assertTrue(mas[0].equals(halfList.get(0)));
		assertTrue(mas[mas.length - 1].equals(halfList.get(mas.length - 1)));
		
		mas = new Integer[halfList.size() - 1];
		res = halfList.toArray(mas);
		
		assertFalse(mas == res);
		assertTrue(mas[0].equals(halfList.get(0)));
		assertTrue(mas[mas.length - 1].equals(halfList.get(mas.length - 1)));
	}

	@Test
	public void testAddE() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testContainsAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddAllCollectionOfQextendsE() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddAllIntCollectionOfQextendsE() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetainAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testClear() {
		fail("Not yet implemented");
	}

	@Test
	public void testSet() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddIntE() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testIndexOf() {
		fail("Not yet implemented");
	}

	@Test
	public void testLastIndexOf() {
		fail("Not yet implemented");
	}

	@Test
	public void testListIterator() {
		fail("Not yet implemented");
	}

	@Test
	public void testListIteratorInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testSubList() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyingStartAtIndex() {
		fail("Not yet implemented");
	}

}

package com.epam.hnyp.task1.subtask4;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.epam.hnyp.task1.subtask4.ListHalfModifiable;
import com.epam.hnyp.task1.subtask4.ListHalfModifiable.UnmodifiedCollectionException;

public class ListHalfModifiableTest {

	private ListHalfModifiable<Integer> halfList;
	private List<Integer> list1;
	private List<Integer> list2;

	@Before
	public void before() {
		list1 = new ArrayList<>();
		list1.add(1);// 0
		list1.add(2);
		list1.add(3);
		list1.add(4);// 3

		list2 = new ArrayList<>();
		list2.add(5);// 4
		list2.add(6);
		list2.add(3);
		list2.add(3);
		list2.add(4);// 8

		halfList = new ListHalfModifiable<>(list1, list2);
	}

	@Test
	public void testSize() {
		assertTrue(halfList.size() == list1.size() + list2.size());
	}

	@Test
	public void testIsEmpty() {
		ListHalfModifiable<Integer> list = new ListHalfModifiable<>(
				new ArrayList<Integer>(), new ArrayList<Integer>());
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void testIsEmptyFalse() {
		assertFalse(halfList.isEmpty());
	}

	@Test
	public void testContains() {
		assertTrue(halfList.contains(1));
	}
	
	@Test
	public void testContainsFalse() {
		assertFalse(halfList.contains(0));
	}

	@Test
	public void testGet() {
		assertTrue(halfList.get(0).equals(1));
		assertTrue(halfList.get(3).equals(4));
	}
	@Test
	public void testGetIndexOutOfBoundsException() {
		try {
			halfList.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			halfList.get(halfList.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
		}
	}

	@Test
	public void testToArray() {
		Object[] mas = halfList.toArray();
		assertTrue(mas.length == halfList.size());
		assertTrue(((Integer) mas[0]).equals(1));
		assertTrue(((Integer) mas[mas.length - 1]).equals(4));
	}

	@Test
	public void testToArrayTArray() {
		Integer[] mas = new Integer[halfList.size()];
		Integer[] res = halfList.toArray(mas);

		assertTrue(mas[0].equals(halfList.get(0)));
		assertTrue(mas[mas.length - 1].equals(halfList.get(mas.length - 1)));
	}
	
	@Test
	public void testToArrayTArrayEnoughSize() {
		Integer[] mas = new Integer[halfList.size()];
		Integer[] res = halfList.toArray(mas);

		assertTrue(mas == res);
		assertTrue(mas[0].equals(halfList.get(0)));
		assertTrue(mas[mas.length - 1].equals(halfList.get(mas.length - 1)));
	}
	
	@Test
	public void testToArrayTArraySizeNotEnough() {
		Integer[] mas = new Integer[halfList.size() - 1];
		Integer[] res = halfList.toArray(mas);

		assertFalse(mas == res);
		assertTrue(res[0].equals(halfList.get(0)));
		assertTrue(res[res.length - 1].equals(halfList.get(res.length - 1)));
	}

	@Test
	public void testModifyingStartAtIndex() {
		assertTrue(halfList.modifyingStartAtIndex() == list1.size());
	}

	@Test
	public void testAddE() {
		assertTrue(halfList.add(10));
		assertTrue(halfList.modifyingStartAtIndex() == list1.size());
	}

	@Test
	public void testRemoveObject() {
		int sz = halfList.size();
		assertTrue(halfList.remove(new Integer(4)));
		assertTrue(halfList.size() == sz - 1);
	}
	
	@Test
	public void testRemoveObjectFalse() {
		assertFalse(halfList.remove(new Integer(0)));
	}
	
	@Test
	public void testRemoveObjectUnmodifiedCollectionException() {
		try {
			halfList.remove(new Integer(1));
			fail();
		} catch (UnmodifiedCollectionException e) {
		}
	}

	@Test
	public void testContainsAll() {
		assertTrue(halfList.containsAll(list1));
		assertTrue(halfList.containsAll(list2));
	}
	
	@Test
	public void testContainsAllFalse() {
		List<Integer> c = new ArrayList<>();
		c.add(10);
		c.add(4);
		c.add(3);
		assertFalse(halfList.containsAll(c));
	}

	@Test
	public void testAddAllCollectionOfQextendsE() {
		List<Integer> c = new ArrayList<>();
		c.add(10);
		c.add(11);
		c.add(12);
		int sz = halfList.size();
		int im = halfList.modifyingStartAtIndex();
		assertTrue(halfList.addAll(c));
		assertTrue(halfList.size() == sz + c.size());
		assertTrue(im == halfList.modifyingStartAtIndex());
	}

	@Test
	public void testAddAllIntCollectionOfQextendsE() {
		List<Integer> c = new ArrayList<>();
		c.add(10);
		c.add(11);
		c.add(12);
		int sz = halfList.size();
		int im = halfList.modifyingStartAtIndex();
		assertTrue(halfList.addAll(halfList.modifyingStartAtIndex(), c));
		assertTrue(halfList.size() == sz + c.size());
		assertTrue(im == halfList.modifyingStartAtIndex());

		try {
			halfList.addAll(halfList.modifyingStartAtIndex() - 1, c);
			fail();
		} catch (UnmodifiedCollectionException e) {
		}
	}
	
	@Test
	public void testAddAllIntCollectionOfQextendsEIndexOutOfBounds() {
		List<Integer> c = new ArrayList<>();
		c.add(10);
		c.add(11);
		c.add(12);
		try {
			halfList.addAll(-1, c);
			fail();
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			halfList.addAll(halfList.size() + 1, c);
			fail();
		} catch (IndexOutOfBoundsException e) {
		}
	}
	
	@Test
	public void testAddAllIntCollectionOfQextendsEUnmodifiedCollectionException() {
		List<Integer> c = new ArrayList<>();
		c.add(10);
		c.add(11);
		c.add(12);
		try {
			halfList.addAll(halfList.modifyingStartAtIndex() - 1, c);
			fail();
		} catch (UnmodifiedCollectionException e) {
		}
	}

	@Test
	public void testRemoveAll() {
		List<Integer> c = new ArrayList<>();
		c.add(5);
		c.add(6);

		int sz = halfList.size();
		assertTrue(halfList.removeAll(c));
		assertTrue(halfList.size() == sz - 2);
		assertTrue(halfList.modifyingStartAtIndex() == list1.size());
	}
	
	@Test
	public void testRemoveAllUnmodifiedCollectionException() {
		List<Integer> c = new ArrayList<>();
		c.add(5);
		c.add(6);
		c.add(4);
		try {
			halfList.removeAll(c);
			fail();
		} catch (UnmodifiedCollectionException e) {
		}
	}

	@Test
	public void testRetainAll() {
		List<Integer> c = new ArrayList<>();
		c.add(1);
		c.add(2);
		c.add(3);
		c.add(4);

		int sz = halfList.size();
		int m_index = halfList.modifyingStartAtIndex();
		assertTrue(halfList.retainAll(c));
		assertTrue(halfList.size() == sz - 2);
		assertTrue(halfList.modifyingStartAtIndex() == m_index);
	}
	
	@Test
	public void testRetainAllUnmodifiedCollectionException() {
		List<Integer> c = new ArrayList<>();
		c.add(3);
		c.add(1);
		c.add(4);
		try {
			halfList.retainAll(c);
			fail();
		} catch (UnmodifiedCollectionException e) {
		}
	}

	@Test
	public void testClear() {
		list1.clear();
		halfList.clear();
		assertTrue(halfList.isEmpty());
	}
	
	@Test
	public void testClearUnmodifiedCollectionException() {
		try {
			halfList.clear();
			fail();
		} catch (UnmodifiedCollectionException e) {
		}
	}

	@Test
	public void testSet() {
		int i = halfList.modifyingStartAtIndex();
		assertTrue(halfList.set(i, 55).equals(5));
	}
	
	@Test
	public void testSetUnmodifiedCollectionException() {
		int i = halfList.modifyingStartAtIndex();
		try {
			halfList.set(i - 1, 55);
			fail();
		} catch (UnmodifiedCollectionException e) {
		}
		try {
			halfList.set(0, 55);
			fail();
		} catch (UnmodifiedCollectionException e) {
		}
	}
	
	@Test
	public void testSetIndexOutOfBoundsException() {
		int i = halfList.modifyingStartAtIndex();
		try {
			halfList.set(-1, 55);
			fail();
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			halfList.set(halfList.size(), 55);
			fail();
		} catch (IndexOutOfBoundsException e) {
		}
	}

	@Test
	public void testAddIntE() {
		int sz = halfList.size();
		halfList.add(halfList.modifyingStartAtIndex(), 66);
		assertTrue(halfList.size() == sz + 1);
		assertTrue(halfList.get(halfList.modifyingStartAtIndex()).equals(66));
	}
	
	@Test
	public void testAddIntEUnmodifiedCollectionException() {
		try {
			halfList.add(halfList.modifyingStartAtIndex() - 1, 66);
			fail();
		} catch (UnmodifiedCollectionException e) {
		}
		try {
			halfList.add(0, 66);
			fail();
		} catch (UnmodifiedCollectionException e) {
		}
	}
	
	@Test
	public void testAddIntEIndexOutOfBoundsException() {
		try {
			halfList.add(-1, 66);
			fail();
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			halfList.add(halfList.size() + 1, 66);
			fail();
		} catch (IndexOutOfBoundsException e) {
		}
	}

	@Test
	public void testRemoveInt() {
		int sz = halfList.size();
		assertTrue(halfList.remove(halfList.modifyingStartAtIndex()).equals(5));
		assertTrue(halfList.size() == sz - 1);
	}
	
	@Test
	public void testRemoveIntUnmodifiedCollectionException() {
		try {
			halfList.remove(0);
			fail();
		} catch (UnmodifiedCollectionException e) {
		}
		try {
			halfList.remove(halfList.modifyingStartAtIndex() - 1);
			fail();
		} catch (UnmodifiedCollectionException e) {
		}
	}
	
	@Test
	public void testRemoveIntIndexOutOfBoundsException() {
		try {
			halfList.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			halfList.remove(halfList.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
		}
	}

	@Test
	public void testIndexOf() {
		assertTrue(halfList.indexOf(new Integer(3)) == 2);
		assertTrue(halfList.indexOf(new Integer(6)) == 5);
	}
	
	@Test
	public void testIndexOfNotFound() {
		assertTrue(halfList.indexOf(new Integer(0)) == -1);
	}

	@Test
	public void testLastIndexOf() {
		assertTrue(halfList.lastIndexOf(new Integer(3)) == 7);
		assertTrue(halfList.lastIndexOf(new Integer(2)) == 1);
	}
	
	@Test
	public void testLastIndexOfNotFound() {
		assertTrue(halfList.lastIndexOf(new Integer(11)) == -1);
	}

	@Test
	public void testSubList() {
		List<Integer> sub = halfList.subList(1, 5);
		assertTrue(sub.size() == 4);
		assertTrue(halfList.containsAll(sub));
	}
	
	@Test
	public void testSubListEmpty() {
		List<Integer> sub = halfList.subList(1, 1);
		assertTrue(sub.isEmpty());
	}
	
	@Test
	public void testSubListIndexOutOfBoundsException() {
		try {
			halfList.subList(-1, 1);
			fail();
		} catch (IndexOutOfBoundsException e) {}
		try {
			halfList.subList(3, 1);
			fail();
		} catch (IndexOutOfBoundsException e) {}
		try {
			halfList.subList(3, halfList.size() + 1);
			fail();
		} catch (IndexOutOfBoundsException e) {}
	}

}

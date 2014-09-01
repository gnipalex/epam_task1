package com.epam.hnyp.task1.subtask3;

import static org.junit.Assert.*;

import org.junit.Test;

import com.epam.hnyp.task1.subtask3.GoodsContainerSI;
import com.epam.hnyp.task1.subtask3.ParameterizedIteratorSI;

public class ParameterizedIteratorSITest {

	@Test
	public void testIteratorDataUnmodifiedOnListModify() {
		GoodsContainerSI<Integer> items = new GoodsContainerSI<>();
		items.add(5);
		items.add(7);//
		items.add(6);
		items.add(8);
		items.add(9);
		
		int sz = items.size();
		
		ParameterizedIteratorSI<Integer> it1 = (ParameterizedIteratorSI<Integer>)items.iterator();
		ParameterizedIteratorSI<Integer> it2 = (ParameterizedIteratorSI<Integer>)items.iterator();
		
		assertTrue(it1.getBridge().getList() == items);
		assertTrue(it2.getBridge().getList() == items);
		assertTrue(it1.getBridge() == it2.getBridge());
		assertTrue(it1.getBridge().getList() == it2.getBridge().getList());
		
		it1.hasNext();
		assertTrue(it1.next().equals(5));
		it2.hasNext();
		assertTrue(it2.next().equals(5));
		
		//removing 7 from list
		items.remove(1);
		
		assertTrue(it1.getBridge().getList() != items);
		assertTrue(it2.getBridge().getList() != items);
		assertTrue(it1.getBridge() == it2.getBridge());
		assertTrue(it1.getBridge() != items.getBridge());
		assertTrue(it1.getBridge().getList() != items.getBridge().getList());
		assertTrue(it1.getBridge().getList() == it2.getBridge().getList());
		assertTrue(it1.getBridge().getList().size() == sz);
		
		//old iterators still see 7
		it1.hasNext();
		assertTrue(it1.next().equals(7));
		it2.hasNext();
		assertTrue(it2.next().equals(7));	
	}

}

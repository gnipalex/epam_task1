package com.epam.hnyp.task2.subtask2;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringKeyWraperLengthTest {

	@Test
	public void testHashCode() {
		StringKeyWraperLength sk1 = new StringKeyWraperLength("12345");
		StringKeyWraperLength sk2 = new StringKeyWraperLength("54321");
		assertEquals(sk1.hashCode(), sk2.hashCode());
	}
	
	@Test
	public void testHashCodeDifferent() {
		StringKeyWraperLength sk1 = new StringKeyWraperLength("12345");
		StringKeyWraperLength sk2 = new StringKeyWraperLength("123456");
		assertFalse(sk1.hashCode() == sk2.hashCode());
	}

}

package com.epam.hnyp.task2.subtask2;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringKeyWraperCodesTest {

	@Test
	public void testEquals() {
		StringKeyWraperCodes sk1 = new StringKeyWraperCodes(new String("123"));
		StringKeyWraperCodes sk2 = new StringKeyWraperCodes(new String("123"));
		assertEquals(sk1, sk2);
	}
	
	@Test
	public void testHashCode() {
		StringKeyWraperCodes sk1 = new StringKeyWraperCodes(new String("12349"));
		StringKeyWraperCodes sk2 = new StringKeyWraperCodes(new String("12345"));
		assertEquals(sk1.hashCode(), sk2.hashCode());
	}
	
	@Test
	public void testHashCodeDifferent() {
		StringKeyWraperCodes sk1 = new StringKeyWraperCodes(new String("10349"));
		StringKeyWraperCodes sk2 = new StringKeyWraperCodes(new String("12345"));
		assertFalse(sk1.hashCode() == sk2.hashCode());
	}

}

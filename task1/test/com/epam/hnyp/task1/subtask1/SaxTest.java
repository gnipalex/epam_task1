package com.epam.hnyp.task1.subtask1;

import static org.junit.Assert.*;

import org.junit.Test;

public class SaxTest {

	@Test
	public void testEqualsSymetry() {
		Sax s1 = new Sax();
		Sax s2 = new Sax();
		assertTrue(s1.equals(s2) && s2.equals(s1));
	}
	
	@Test
	public void testEqualsReflection() {
		Sax s1 = new Sax();
		assertTrue(s1.equals(s1));
	}
	
	@Test
	public void testEqualsTransition() {
		Sax s1 = new Sax();
		Sax s2 = new Sax();
		Sax s3 = new Sax();
		assertTrue(s1.equals(s2) && s2.equals(s3) && s1.equals(s3));
	}
	
	@Test
	public void testEqualsConstancy() {
		Sax s1 = new Sax();
		Sax s2 = new Sax();
		String oldMaterial = s1.getMaterial();
		s1.setMaterial("");
		assertFalse(s1.equals(s2));
		s1.setMaterial(oldMaterial);
		assertTrue(s1.equals(s2));
	}

}

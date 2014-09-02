package com.epam.hnyp.task1.subtask1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.epam.hnyp.task1.subtask1.Guitar.StringType;

public class MusicInstrumentTest {

	@Test
	public void equalsProductsTest() {
		MusicInstrument m1 = new MusicInstrument();
		MusicInstrument m2 = new MusicInstrument();
		MusicInstrument m3 = new MusicInstrument("aaa", 2002);
		Sax s1 = new Sax();
		Sax s2 = new Sax();
 		Sax s3 = new Sax("aaa", 2002, "qwerty");
 		Trumpet t1 = new Trumpet();
 		Trumpet t2 = new Trumpet();
 		Trumpet t3 = new Trumpet("aaa", 2002, "qwerty");
 		Violin v1 = new Violin();
 		Violin v2 = new Violin();
 		Violin v3 = new Violin("aaa", 2002, 4);
 		Guitar g1 = new Guitar();
 		Guitar g2 = new Guitar();
 		Guitar g3 = new Guitar("aaa", 2002, 6, "brown", StringType.METAL);
 		
 		assertTrue(s1.equals(s1));
 		assertTrue(s1.equals(s2));
 		assertTrue(s2.equals(s1));
 		assertFalse(s1.equals(s3));
 		assertFalse(s3.equals(s1));
 		
 		assertTrue(t1.equals(t1));
 		assertTrue(t1.equals(t2));
 		assertFalse(t1.equals(t3));
 		
 		assertFalse(s1.equals(t1));
 		assertFalse(s3.equals(t3));
 		
 		assertTrue(v1.equals(v1));
 		assertTrue(v1.equals(v2));
 		assertFalse(v1.equals(v3));
 		
 		assertFalse(v1.equals(t1));
 	
 		assertFalse(g1.equals(v1));
 		assertTrue(g1.equals(g2));
 		
 		assertFalse(g1.equals(g3));
 		
 		assertFalse(m1.equals(s1));
 		assertFalse(m1.equals(t1));
 		assertFalse(m1.equals(v1));
 		assertFalse(m1.equals(g1));
	}

}

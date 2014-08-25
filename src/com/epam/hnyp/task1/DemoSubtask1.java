package com.epam.hnyp.task1;

import java.util.ArrayList;
import java.util.List;

import com.epam.hnyp.task1.subtask1.Guitar;
import com.epam.hnyp.task1.subtask1.MusicInstrument;
import com.epam.hnyp.task1.subtask1.Guitar.StringType;
import com.epam.hnyp.task1.subtask1.StringInstrument;
import com.epam.hnyp.task1.subtask1.Trumpet;
import com.epam.hnyp.task1.subtask1.Violin;

public class DemoSubtask1 {
	public static void main(String[] args) {
		System.out.println("Demo of Subtask1");
		System.out.println("--------------------");
		List<MusicInstrument> instruments = new ArrayList<>();
		MusicInstrument m1 = new MusicInstrument();
		instruments.add(m1);
		MusicInstrument m2 = new MusicInstrument("yamaha", 2005);
		instruments.add(m2);
		MusicInstrument m3 = new Guitar();
		instruments.add(m3);
		MusicInstrument m4 = new Guitar("fender", 1980, 6, "black", StringType.METAL);
		instruments.add(m4);
		MusicInstrument m5 = new Violin();
		instruments.add(m5);
		MusicInstrument m6 = new Violin("aaaa", 2010, 8);
		instruments.add(m6);
		MusicInstrument m7 = new Trumpet();
		instruments.add(m7);
		MusicInstrument m8 = new Trumpet("truba_vendor", 2006, "coper");
		instruments.add(m8);
		MusicInstrument m9 = new Guitar("fender", 1980, 6, "black", StringType.METAL);
		instruments.add(m9);
		MusicInstrument m10 = new Guitar("fender", 1990, 6, "black", StringType.METAL);
		instruments.add(m10);
		for (int i = 0; i < instruments.size(); i++) {
			System.out.println(i + 1 + " " + instruments.get(i));
			instruments.get(i).play();
		}
		System.out.println("--------------------");
		System.out.println("equals test");
		System.out.println("1 eq 1 --> " + m1.equals(m1));
		System.out.println("1 eq 2 --> " + m1.equals(m2));
		System.out.println("1 eq 7 --> " + m1.equals(m7));
		System.out.println("4 eq 9 --> " + m4.equals(m9));
		System.out.println("9 eq 4 --> " + m9.equals(m4));
		System.out.println("4 eq 10 --> " + m4.equals(m10));
		
		System.out.println("--------------------");
		System.out.println("only string instruments");
		for (MusicInstrument mi : instruments) {
			if (mi instanceof StringInstrument) {
				System.out.println(mi);
			}
		}
	}
}

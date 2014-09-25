package com.epam.hnyp.task6.subtask1;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		List<Integer> res = new ArrayList<>();
		PrimeFinder p = new PrimeFinder(0, 54, res);
		Thread t = new Thread(p);
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(res);
	}
}

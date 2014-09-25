package com.epam.hnyp.task6.subtask1;

public class Test2 {
	public static void main(String[] args) {
		int start = 1;
		int end = 100;
		int thrCount = 10;
		
		int originalLen = end - start;
		int checkLen = 0;
		
		for (int i = thrCount; i >= 1; i--) {
			System.out.println( originalLen * (i / (thrCount * 2d)) );
		}
		
		System.out.println();
		System.out.println(checkLen);
	}
}

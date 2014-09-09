package com.epam.hnyp.task3.subtask1;

public class Demo {
	public static final String FILE_NAME = "aaa.txt";
	
	public static void main(String[] args) {
		TextLineViewer viewer = new TextLineViewer(FILE_NAME);
		try {
			for (String s : viewer) {
				System.out.println(s);
			}
		} catch (IllegalArgumentException e) {
			System.out.println("error of reading, file not found");
		}
	}
}

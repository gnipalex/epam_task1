package com.epam.hnyp.task2.subtask3.util.notused;

import java.util.Formatter;


public class Test {
	public static void main(String[] args) {
		Formatter fmt = new Formatter();
		fmt.format("%1$10s%2$10s\n", "aaa", "bbbbb");
		fmt.format("%1$10s%2$10s\n", "aaa", "bbbbb");
		fmt.format("%1$10s%2$10s\n", "aaa", "bbbbb");
		System.out.println(fmt.toString());
		fmt = new Formatter();
		System.out.println(fmt.toString());
		
	}
}

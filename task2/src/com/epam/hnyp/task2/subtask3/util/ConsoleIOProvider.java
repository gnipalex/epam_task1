package com.epam.hnyp.task2.subtask3.util;

import java.util.Scanner;

public class ConsoleIOProvider implements IOProvider {

	private Scanner scanner = new Scanner(System.in);

	@Override
	public String readLine() {
		return scanner.nextLine();
	}

	@Override
	public void print(String str) {
		System.out.print(str);
	}

	@Override
	public void printLine(String str) {
		System.out.println(str);

	}

	@Override
	public void printLine() {
		System.out.println();
	}
}

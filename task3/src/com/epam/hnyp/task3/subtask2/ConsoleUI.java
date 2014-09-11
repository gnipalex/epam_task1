package com.epam.hnyp.task3.subtask2;

import java.io.File;
import java.util.Scanner;

import com.epam.hnyp.task3.subtask2.condition.BaseFileCondition;

public class ConsoleUI {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Please specify search folder or pass enter to cancel: ");
		String line = sc.nextLine();
		if (line.isEmpty()) {
			return;
		}
		File startDir = new File(line);
		if (!startDir.exists()) {
			System.out.println("--Dir not found--");
			return;
		}
		if (!startDir.isDirectory()) {
			System.out.println("--Specified path is not directory--");
			return;
		}
		
		System.out.println("Search in dir : " + startDir.getAbsolutePath());
		BaseFileCondition filter = null;
		
		while (true) {
			System.out.print("Use name of file ? (1/0)");
			line = sc.nextLine();
			if (line.isEmpty() || line.charAt(0) == '0') {
				break;
			}
			if (line.charAt(0) != '1') {
				continue;
			}
			System.out.print("Enter name of file : ");
			line = sc.nextLine();
			break;
		}
		
		
		
		
		
	}
}

package com.epam.hnyp.task3.subtask2;

import java.io.File;
import java.util.Scanner;

import com.epam.hnyp.task3.subtask2.condition.BaseFileCondition;
import com.epam.hnyp.task3.subtask2.condition.ExtensionFileCondition;
import com.epam.hnyp.task3.subtask2.condition.FileSizeCondition;
import com.epam.hnyp.task3.subtask2.condition.NameFileCondition;

public class ConsoleUI {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out
				.print("Please specify search folder or pass enter to cancel: ");
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

		System.out.print("Use name filter ? (y - yes)");
		line = sc.nextLine();
		if (!line.isEmpty() && line.charAt(0) == 'y') {
			while (true) {
				System.out.print("Enter name of file : ");
				line = sc.nextLine();
				if (line.isEmpty()) {
					continue;
				}
				break;
			}
			filter = new NameFileCondition(line, null);
		}

		System.out.print("Use extension filter ? (y - yes)");
		line = sc.nextLine();
		if (!line.isEmpty() && line.charAt(0) == 'y') {
			while (true) {
				System.out.print("Enter extension of file : ");
				line = sc.nextLine();
				if (line.isEmpty()) {
					continue;
				}
				break;
			}
			filter = new ExtensionFileCondition(line, filter);
		}

		System.out.print("Use size filter ? (y - yes)");
		line = sc.nextLine();
		if (!line.isEmpty() && line.charAt(0) == 'y') {
			long minSz = 0, maxSz = 0;
			while (true) {
				while (true) {
					System.out.print("Enter min size of file : ");
					line = sc.nextLine();
					if (line.isEmpty()) {
						continue;
					}
					try {
						minSz = Long.parseLong(line);
					} catch (NumberFormatException e) {
						System.out.println("##wrong format##");
						continue;
					}
					break;
				}
				while (true) {
					System.out.print("Enter max size of file : ");
					line = sc.nextLine();
					if (line.isEmpty()) {
						continue;
					}
					try {
						maxSz = Long.parseLong(line);
					} catch (NumberFormatException e) {
						System.out.println("##wrong format##");
						continue;
					}
					break;
				}
				if (minSz < 0 || maxSz < minSz) {
					System.out.println("##Sizes must be min >= 0 and min <= max##");
					continue;
				}
				break;
			}
			filter = new FileSizeCondition(minSz, maxSz, filter);
		}
		
		System.out.print("Use size filter ? (y - yes)");
		line = sc.nextLine();
		if (!line.isEmpty() && line.charAt(0) == 'y') {
			
		}
		

	}

}

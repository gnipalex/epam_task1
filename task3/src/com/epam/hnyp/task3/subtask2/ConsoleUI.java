package com.epam.hnyp.task3.subtask2;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.epam.hnyp.task3.subtask2.condition.BaseFileCondition;
import com.epam.hnyp.task3.subtask2.condition.ExtensionFileCondition;
import com.epam.hnyp.task3.subtask2.condition.FileSizeCondition;
import com.epam.hnyp.task3.subtask2.condition.ModifyDateCondition;
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

		System.out.print("Use name filter ? (y - yes) : ");
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

		System.out.print("Use extension filter ? (y - yes) : ");
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

		System.out.print("Use size filter ? (y - yes) : ");
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
		
		System.out.print("Use modify date filter ? (y - yes) : ");
		line = sc.nextLine();
		if (!line.isEmpty() && line.charAt(0) == 'y') {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date dateFrom = null, dateTo = null;
			while (true) {
				while (true) {
					System.out.print("Enter left border of date(dd/MM/yyyy HH:mm): ");
					line = sc.nextLine();
					if (line.isEmpty()) {
						continue;
					}
					try {
						dateFrom = sdf.parse(line);
					} catch (ParseException e) {
						System.out.println("##wrong format##");
						continue;
					}
					break;
				}
				while (true) {
					System.out.print("Enter right border of date(dd/MM/yyyy HH:mm): ");
					line = sc.nextLine();
					if (line.isEmpty()) {
						continue;
					}
					try {
						dateTo = sdf.parse(line);
					} catch (ParseException e) {
						System.out.println("##wrong format##");
						continue;
					}
					break;
				}
				if (dateFrom.compareTo(dateTo) > 0) {
					System.out.println("##left border must be <= to right border##");
					continue;
				}
				break;
			}
			filter = new ModifyDateCondition(dateFrom, dateTo, filter);
		}
		System.out.println("Searching... Please wait...");
		FileSearch search = new FileSearch(startDir, filter);
		List<File> foundFiles = search.find();
		System.out.println("Found files :");
		if (foundFiles.isEmpty()) {
			System.out.println("--nothing was found--");
			return;
		}
		for (File f : foundFiles) {
			System.out.println(f.getAbsolutePath());
		}
	}

}

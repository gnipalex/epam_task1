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
	private static BaseFileCondition filter = null;
	private static File startDir;
	private static Scanner sc = new Scanner(System.in);
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	private static void configureNameFilter() {
		System.out.print("Use name filter ? (y - yes) : ");
		String line = sc.nextLine();
		if (line.isEmpty() || line.charAt(0) != 'y') {
			return;
		}
		do {
			System.out.print("Enter name of file : ");
			line = sc.nextLine();
		} while (line.isEmpty());
		filter = new NameFileCondition(line, filter);

	}

	private static void configureExtensionFilter() {
		System.out.print("Use extension filter ? (y - yes) : ");
		String line = sc.nextLine();
		if (line.isEmpty() || line.charAt(0) != 'y') {
			return;
		}
		do {
			System.out.print("Enter extension of file : ");
			line = sc.nextLine();
		} while (line.isEmpty());
		filter = new ExtensionFileCondition(line, filter);
	}

	private static void configureSizeFilter() {
		System.out.print("Use size filter ? (y - yes) : ");
		String line = sc.nextLine();
		if (line.isEmpty() || line.charAt(0) != 'y') {
			return;
		}
		long minSz = 0, maxSz = 0;
		boolean inputError = true; 
		do {
			minSz = inputSize("Enter min size of file : ");
			maxSz = inputSize("Enter max size of file : ");
			if (minSz < 0 || maxSz < minSz) {
				System.out.println("##Sizes must be min >= 0 and min <= max##");
			} else {
				inputError = false;
			}
		} while (inputError);
		filter = new FileSizeCondition(minSz, maxSz, filter);
	}

	private static long inputSize(String message) {
		long value = 0;
		String line = null;
		boolean inputError = true;
		do {
			System.out.print(message);
			line = sc.nextLine();
			if (!line.isEmpty()) {
				try {
					value = Long.parseLong(line);
					inputError = false;
				} catch (NumberFormatException e) {
					System.out.println("##wrong format##");
				}
			}
		} while (line.isEmpty() || inputError);
		return value;
	}

	private static Date inputDate(String message) {
		boolean inputError = true;
		Date date = null;
		String line = null;
		do {
			System.out.print(message);
			line = sc.nextLine();
			if (!line.isEmpty()) {
				try {
					date = sdf.parse(line);
					inputError = false;
				} catch (ParseException e) {
					System.out.println("##wrong format##");
				}
			}
		} while (line.isEmpty() || inputError);
		return date;
	}

	private static void configureDateFilter() {
		System.out.print("Use modify date filter ? (y - yes) : ");
		String line = sc.nextLine();
		if (!line.isEmpty() && line.charAt(0) == 'y') {
			Date dateFrom = null, dateTo = null;
			boolean inputError = true; 
			do {
				dateFrom = inputDate("Enter left border of date(dd/MM/yyyy HH:mm): ");
				dateTo = inputDate("Enter right border of date(dd/MM/yyyy HH:mm): ");
				if (dateFrom.compareTo(dateTo) > 0) {
					System.out.println("##left border must be <= to right border##");
				} else {
					inputError = false;
				}
			} while(inputError);
			filter = new ModifyDateCondition(dateFrom, dateTo, filter);
		}
	}

	private static void configureSearchDir() {
		System.out
				.print("Please specify search folder or pass enter to cancel: ");
		String line = sc.nextLine();
		if (line.isEmpty()) {
			System.exit(1);
		}
		startDir = new File(line);
		if (!startDir.exists()) {
			System.out.println("--Dir not found--");
			System.exit(1);
		}
		if (!startDir.isDirectory()) {
			System.out.println("--Specified path is not directory--");
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		configureSearchDir();

		System.out.println("Search in dir : " + startDir.getAbsolutePath());

		configureNameFilter();
		configureExtensionFilter();
		configureSizeFilter();
		configureDateFilter();

		System.out.println("Searching... Please wait...");
		FileSearch search = new FileSearch(startDir, filter);
		List<File> foundFiles = search.find();
		System.out.println("Found files :");
		if (foundFiles.isEmpty()) {
			System.out.println("--nothing was found--");
			return;
		}
		for (File f : foundFiles) {
			System.out.println(f.getPath());
		}
	}

}

package com.epam.hnyp.task6.subtask5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ConsoleSequenceFinderWaitNotify {

	public static final long STATUS_CHECK_PERIOD = 500;
	
	public static void main(String[] args) {		
		SequenceFinderRunnableWaitNotify finder = new SequenceFinderRunnableWaitNotify();
		Thread t = new Thread(finder);
		t.setDaemon(true);
		t.start();
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.print("Enter file name (or empty line to quit) : ");
			String line = sc.nextLine();
			if (line.isEmpty()) {
				return;
			}
			
			byte[] data = null;
			try {
				data = readFile(line);
			} catch (IOException e) {
				System.out.println("Error : " + e.getMessage());
				continue;
			}
			
			// passing data to other thread and signaling to start search
			finder.setData(data);

			System.out.println("Searching....");

			SearchStatusExtended curentStatus = null;
			do {
				try {
					Thread.sleep(STATUS_CHECK_PERIOD);
				} catch (InterruptedException e) {
					System.out.println("Main thread was interrupted");
					return;
				}
				//asking finder for search status
				curentStatus = finder.getActualCurentStatus();
				System.out.println(curentStatus);
			} while (!curentStatus.isFinish());
			System.out.println("Longest sequence --> " + curentStatus);
			System.out.println();
		}
	}
	
	private static byte[] readFile(String fileName) throws IOException {
		File f = new File(fileName);
		if (!f.exists()) {
			throw new IOException("file does't exist");
		}
		if (f.length() == 0) {
			throw new IOException("file is empty");
		}
		try (InputStream input = new FileInputStream(f)) {
			byte[] data = new byte[input.available()];
			input.read(data);
			return data;
		}
	}
}

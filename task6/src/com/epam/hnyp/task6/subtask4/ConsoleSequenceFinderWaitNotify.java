package com.epam.hnyp.task6.subtask4;

import java.util.Scanner;

import com.epam.hnyp.task6.subtask3.SearchStatus;

public class ConsoleSequenceFinderWaitNotify {
	private static final SearchParamContainer PARAM_CONTAINER = new SearchParamContainer();
	private static final Object FILE_MONITOR = new Object();
	private static final Object STATUS_MONITOR = new Object();
	
	public static void main(String[] args) {
		startDemon();
		Scanner sc = new Scanner(System.in);
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(1);
			}
			System.out.print("Enter file name (or empty line to quit) : ");
			String line = sc.nextLine();
			if (line.isEmpty()) {
				break;
			}
			synchronized (FILE_MONITOR) {
				PARAM_CONTAINER.setFileName(line);
				FILE_MONITOR.notify();
			}

			System.out.println("Searching....");

			SearchStatus prevStatus = null;
			SearchStatus curentStatus = null;
			do {
				curentStatus = null;
				synchronized (STATUS_MONITOR) {
					System.err.println("SYN_STATUS --> DAEMON");
					try {
						STATUS_MONITOR.wait();
						curentStatus = PARAM_CONTAINER.getSearchStatus();
						STATUS_MONITOR.notify();
					} catch (InterruptedException e) {
						System.out.println("Error : Main thread was interupted");
						return;
					}
					
				}
				if (curentStatus != null) {
					if (curentStatus.isError()) {
						System.out.println("Error in daemon thread : "
								+ curentStatus.getErrorMessage());
						curentStatus = null;
					} else {
						System.out.println(curentStatus);
						prevStatus = curentStatus;
					}
				} else {
					if (prevStatus == null) {
						System.out.println("--longest sequence not found--");
					} else {
						System.out
								.println("Longest sequence --> " + prevStatus);
					}
					System.out.println("---------------------------------");
				}
				
			} while (curentStatus != null);
			System.out.println();
		}
	}

	private static void startDemon() {
		Thread t = new Thread(new SequenceFinderRunnableWaitNotify(PARAM_CONTAINER, FILE_MONITOR, STATUS_MONITOR));
		t.setDaemon(true);
		t.start();
	}
}

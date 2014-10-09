package com.epam.hnyp.task6.subtask4;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class ConsoleSequenceFinderWaitNotify {
	private static final long STATUS_WAIT_TIMEOUT = 1000;

	public static void main(String[] args) {
		final Queue<SearchStatusExtended> statusesQueueMonitor = 
				new ArrayBlockingQueue<>(5);
		final SearchParamContainer paramContainerMonitor = new SearchParamContainer(statusesQueueMonitor);
		
		startDemon(paramContainerMonitor);
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.print("Enter file name (or empty line to quit) : ");
			String line = sc.nextLine();
			if (line.isEmpty()) {
				return;
			}
			// passing file name to other thread
			synchronized (paramContainerMonitor) {
				paramContainerMonitor.setFileName(line);
				paramContainerMonitor.notify();
			}

			System.out.println("Searching....");

			SearchStatusExtended prevStatus = null;
			SearchStatusExtended curentStatus = null;
			do {
				synchronized (statusesQueueMonitor) {
					do {
						curentStatus = statusesQueueMonitor.poll();
						if (curentStatus == null) {
							try {
								statusesQueueMonitor.wait(STATUS_WAIT_TIMEOUT);
							} catch (InterruptedException e) {
								System.out.println("Error : Main thread was interupted");
								return;
							}
							continue;
						} else if (curentStatus.isError()) {
							System.out.println("Error in daemon thread : "
									+ curentStatus.getErrorMessage());
							
							
						} else if (!curentStatus.isFinish()) {
							System.out.println(curentStatus);
							prevStatus = curentStatus;
						}
					} while(curentStatus == null || !curentStatus.isFinish());
					if (prevStatus != null) {
						System.out.println("Longest sequence --> "
							+ curentStatus);
					} else {
						System.out
						.println("--longest sequence not found--");
					}
				}
			} while (!curentStatus.isFinish());
			System.out.println();
		}
	}

	private static void startDemon(SearchParamContainer paramContainer) {
		Thread t = new Thread(new SequenceFinderRunnableWaitNotify(
				paramContainer));
		t.setDaemon(true);
		t.start();
	}
}

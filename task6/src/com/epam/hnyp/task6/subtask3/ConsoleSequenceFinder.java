package com.epam.hnyp.task6.subtask3;

import java.util.Scanner;
import java.util.concurrent.Exchanger;

import com.epam.hnyp.task6.subtask3.SequenceFinderRunnable.SearchStatus;

public class ConsoleSequenceFinder {
	
	private static final Exchanger<String> FILE_NAME_EXCHANGER = new Exchanger<>();
	private static final Exchanger<SearchStatus> SEARCH_STATUS_EXCHANGER = new Exchanger<>();
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("MAIN started");
		startDemon();
		Scanner sc = new Scanner(System.in);
		outer: while (true) {
			System.out.print("Enter file name (empty line to quit) : ");
			String line = sc.nextLine();
			if (line.isEmpty()) {
				break;
			}
			
			FILE_NAME_EXCHANGER.exchange(line);
			
			System.out.println("Searching....");
			
			SearchStatus prevStatus = null;
			inner: while (true) {
				SearchStatus status = SEARCH_STATUS_EXCHANGER.exchange(null);
				if (status != null) {
					if (status.isError()) {
						System.out.println("Error in daemon thread : " + status.getErrorMessage());
						continue outer;
					}
					System.out.println(status);
					prevStatus = status;
				} else {
					if (prevStatus == null) {
						System.out.println("--longest sequence not found--");
						continue outer;
					}
					
					System.out.println("\nLongest sequence --> " + prevStatus);
					System.out.println("---------------------------------");
					break inner;
				}
			}
		}
		

	}
	
	private static void startDemon() {
		Thread t = new Thread(new SequenceFinderRunnable(FILE_NAME_EXCHANGER, SEARCH_STATUS_EXCHANGER));
		t.setDaemon(true);
		t.start();
	}
}

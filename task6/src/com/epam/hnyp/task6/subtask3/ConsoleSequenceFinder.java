package com.epam.hnyp.task6.subtask3;

import java.util.Scanner;
import java.util.concurrent.Exchanger;

import com.epam.hnyp.task6.subtask3.SequenceFinderRunnable.SearchStatus;

public class ConsoleSequenceFinder {

	private static final Exchanger<String> FILE_NAME_EXCHANGER = new Exchanger<>();
	private static final Exchanger<SearchStatus> SEARCH_STATUS_EXCHANGER = new Exchanger<>();

	public static void main(String[] args) {
		startDemon();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("Enter file name (or empty line to quit) : ");
			String line = sc.nextLine();
			if (line.isEmpty()) {
				break;
			}

			try {
				FILE_NAME_EXCHANGER.exchange(line);
			} catch (InterruptedException e) {
				System.out
						.println("Error : main module was interupted while passing parameters to daemon module");
				return;
			}

			System.out.println("Searching....");

			SearchStatus prevStatus = null;
			SearchStatus curentStatus = null;
			do {
				curentStatus = null;
				try {
					curentStatus = SEARCH_STATUS_EXCHANGER.exchange(null);
				} catch (InterruptedException e) {
					System.out.println("Main thread was interupted");
					return;
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
		Thread t = new Thread(new SequenceFinderRunnable(FILE_NAME_EXCHANGER,
				SEARCH_STATUS_EXCHANGER));
		t.setDaemon(true);
		t.start();
	}
}

package com.epam.hnyp.task6.subtask3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.Exchanger;

import com.epam.hnyp.task6.subtask3.ConsoleSequenceFinder.Runner.SearchStatus;

public class ConsoleSequenceFinder {
	
	private static Exchanger<String> fileNameExchanger = new Exchanger<>();
	private static Exchanger<SearchStatus> searchStatusExchanger = new Exchanger<>();
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("MAIN started");
		startDemon();
		Scanner sc = new Scanner(System.in);
		outer: while (true) {
			System.out.print("Enter file name (empty line to quit) :");
			String line = sc.nextLine();
			if (line.isEmpty()) {
				break;
			}
			
			fileNameExchanger.exchange(line);
			
			System.out.println("Searching....");
			
			SearchStatus prevStatus = null;
			inner: while (true) {
				SearchStatus status = searchStatusExchanger.exchange(null);
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
					
					System.out.println("\nLongest sequence --> " + status);
					System.out.println("---------------------------------");
					break inner;
				}
			}
		}
		

	}
	
	private static void startDemon() {
		Thread t = new Thread(new Runner());
		t.setDaemon(true);
		t.start();
	}
	
	static class Runner implements Runnable {
		private SearchStatus status;
		private String fileName;
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " started");
			while (true) {
				status = new SearchStatus();
				exchangeFileName();
				byte[] data = null;
				try {
					readFile(new File(fileName), status);
				} catch (IOException e) {
					exchangeStatus();
					continue;
				}
				for (int i=0; i<data.length; i++) {
					byte item = data[i];
					int offset = i;
					int len = 1;
					for (int j=i; j<data.length; j++) {
						if (j + 1 < data.length && data[j + 1] == item) {
							len++;
							continue;
						}
						break;
					}
					if (len > 1) {
						if (len > status.length) {
							status.item = item;
							status.length = len;
							status.offset = offset;
							exchangeStatus(); //signaling search status 
						}
						i += len - 1;
					}
				}
				status = null;
				exchangeStatus();
			}
		}	
		
		private void exchangeStatus() {
			try {
				searchStatusExchanger.exchange(status);
			} catch (InterruptedException e) {
			}
		}
		
		private void exchangeFileName() {
			try {
				fileName = fileNameExchanger.exchange(null);
			} catch (InterruptedException e) {
			}
		}
		
		private byte[] readFile(File f, SearchStatus st) throws IOException {
			if (!f.exists()) {
				st.error = true;
				st.errorMessage = "file does't exist";
				throw new IOException();
			}
			if (f.length() == 0) {
				st.error = true;
				st.errorMessage = "file is empty";
				throw new IOException();
			}
			try (InputStream input = new FileInputStream(f)) {
				byte[] data = new byte[(int)f.length()];
				int length = 0;
				int offset = 0;
				final int BLOCK_SZ = 1024;
				while ((length = input.read(data, offset, BLOCK_SZ)) >= 0) {//read indexoutofbounds
					offset += length;
				}
				return data;
				
			} catch (IOException e) {
				st.error = true;
				st.errorMessage = "i/o error";
				throw e;
			}
		}
		
		class SearchStatus {
			private boolean error;
			private String errorMessage;
			private int offset;
			private int length;
			private byte item;
			
			public int getOffset() {
				return offset;
			}
			public int getLength() {
				return length;
			}
			public byte getItem() {
				return item;
			}
			public boolean isError() {
				return error;
			}
			public String getErrorMessage() {
				return errorMessage;
			}
			
			@Override
			public String toString() {
				return "item = [" + item + "], offset = " + offset + ", lenght = " + length;
			}
		}
	}
}

package com.epam.hnyp.task6.subtask3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.Exchanger;

import com.epam.hnyp.task6.subtask3.ConsoleSequenceFinder.Runner.SearchStatus;

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
		Thread t = new Thread(new Runner());
		t.setDaemon(true);
		t.start();
	}
	
	static class Runner implements Runnable {
		private String fileName;
		
		@Override
		public void run() {
			while (true) {
				SearchStatus status = new SearchStatus();
				exchangeFileName();
				byte[] data = null;
				try {
					data = readFile(new File(fileName), status);
				} catch (IOException e) {
					exchangeStatus(new SearchStatus(status));
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
							exchangeStatus(new SearchStatus(status)); //signaling search status 
						}
						i += len - 1;
					}
				}
				exchangeStatus(null);
			}
		}	
		
		private boolean sequencesEqual(byte[] data, int offset1, int offset2, int length) {
			for (int i=0; i<length; i++) {
				if (data[offset1 + i] != data[offset2 + i]) {
					return false;
				}
			}
			return true;
		}
		
		private void exchangeStatus(SearchStatus st) {
			try {
				SEARCH_STATUS_EXCHANGER.exchange(st);
			} catch (InterruptedException e) {
			}
		}
		
		private void exchangeFileName() {
			try {
				fileName = FILE_NAME_EXCHANGER.exchange(null);
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
				byte[] data = new byte[input.available()];
				input.read(data);
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
			private int offset2;
			
			public SearchStatus() {
			}
			
			/**
			 * Creates copy of status
			 * @param st
			 */
			public SearchStatus(SearchStatus st) {
				this.error = st.error;
				this.errorMessage = st.errorMessage;
				this.length = st.length;
				this.offset = st.offset;
			}
			
			public int getOffset() {
				return offset;
			}
			public int getLength() {
				return length;
			}
			public boolean isError() {
				return error;
			}
			public String getErrorMessage() {
				return errorMessage;
			}
			
			@Override
			public String toString() {
				return "offset = " + offset + ", lenght = " + length;
			}
		}
	}
}

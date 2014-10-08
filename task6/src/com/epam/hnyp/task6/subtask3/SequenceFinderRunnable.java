package com.epam.hnyp.task6.subtask3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Exchanger;

public class SequenceFinderRunnable implements Runnable {
	private String fileName;
	private final Exchanger<String> fnameExchanger;
	private final Exchanger<SearchStatus> statusExchanger;
	
	public SequenceFinderRunnable(Exchanger<String> fnameExchanger,
			Exchanger<SearchStatus> statusExchanger) {
		this.fnameExchanger = fnameExchanger;
		this.statusExchanger = statusExchanger;
	}

	@Override
	public void run() {
		while (true) {
			SearchStatus status = new SearchStatus();
			try {
				fileName = fnameExchanger.exchange(null);
			} catch (InterruptedException e) {
				continue;
			}
			byte[] data = null;
			try {
				data = readFile(new File(fileName), status);
			} catch (IOException e) {
				exchangeStatus(new SearchStatus(status));
				continue;
			}
			int offset1 = 0, offset2 = 0, len = 0;
			for (int i = 0; i < data.length - 2; i++) {
				offset1 = i;			
				for (int j = 2; j < data.length - i; j++) {
					len = j;
					for (int g = i + 1; g + len <= data.length; g++) {
						offset2 = g;
						if (len > status.length && sequencesEqual(data, offset1, offset2, len)) {
							status.length = len;
							status.offsetFirst = offset1;
							status.offsetSecond = offset2;
							exchangeStatus(new SearchStatus(status));
							break;
						}
					}
					
				}
			}
			exchangeStatus(null);
		}
	}

	private boolean sequencesEqual(byte[] data, int offset1, int offset2,
			int length) {
		for (int i = 0; i < length; i++) {
			if (data[offset1 + i] != data[offset2 + i]) {
				return false;
			}
		}
		return true;
	}

	private void exchangeStatus(SearchStatus st) {
		try {
			statusExchanger.exchange(st);
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

	public static class SearchStatus {
		private boolean error;
		private String errorMessage;
		private int offsetFirst;
		private int offsetSecond;
		private int length;

		public SearchStatus() {
		}

		/**
		 * Creates copy of status
		 * 
		 * @param st
		 */
		public SearchStatus(SearchStatus st) {
			this.error = st.error;
			this.errorMessage = st.errorMessage;
			this.length = st.length;
			this.offsetFirst = st.offsetFirst;
			this.offsetSecond = st.offsetSecond;
		}

		public int getOffsetFirst() {
			return offsetFirst;
		}

		public int getOffsetSecond() {
			return offsetSecond;
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
			return "offset(1) = [" + offsetFirst + "], offset(2) = [" + offsetSecond + "], lenght = " + length;
		}
	}
}

package com.epam.hnyp.task6.subtask4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.epam.hnyp.task6.subtask3.SearchStatus;

public class SequenceFinderRunnableWaitNotify implements Runnable {
	private final SearchParamContainer paramContainer;
	private final Object fileMonitor;
	private final Object statusMonitor;

	public SequenceFinderRunnableWaitNotify(
			SearchParamContainer paramContainer, Object fileMonitor,
			Object statusMonitor) {
		this.paramContainer = paramContainer;
		this.fileMonitor = fileMonitor;
		this.statusMonitor = statusMonitor;
	}

	@Override
	public void run() {
		while (true) {
			SearchStatus status = new SearchStatus();
			synchronized (fileMonitor) {
				while (true) {
					try {
						fileMonitor.wait();
						break;
					} catch (InterruptedException e) {
					}
				}
			}
			byte[] data = null;
			try {
				data = readFile(new File(paramContainer.getFileName()),
						status);
			} catch (IOException e) {
				synchronized (statusMonitor) {
					paramContainer.setSearchStatus(new SearchStatus(status));
					statusMonitor.notify();
				}
				continue;
			}
			int offset1 = 0, offset2 = 0, len = 0;
			for (int i = 0; i < data.length - 2; i++) {
				offset1 = i;
				for (int j = 2; j < data.length - i; j++) {
					len = j;
					for (int g = i + 1; g + len <= data.length; g++) {
						offset2 = g;
						if (len > status.getLength()
								&& sequencesEqual(data, offset1, offset2, len)) {
							status.setLength(len);
							status.setOffsetFirst(offset1);
							status.setOffsetSecond(offset2);
							synchronized (statusMonitor) {
								System.err.println("SYN_STATUS --> MAIN");
								paramContainer.setSearchStatus(new SearchStatus(status));
								statusMonitor.notify();
								try {
									statusMonitor.wait();
								} catch (InterruptedException e) {	}
							}
							break;
						}
					}

				}
			}
			synchronized (statusMonitor) {
				paramContainer.setSearchStatus(null);
				statusMonitor.notify();
			}
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

	private byte[] readFile(File f, SearchStatus st) throws IOException {
		if (!f.exists()) {
			st.setError(true);
			st.setErrorMessage("file does't exist");
			throw new IOException();
		}
		if (f.length() == 0) {
			st.setError(true);
			st.setErrorMessage("file is empty");
			throw new IOException();
		}
		try (InputStream input = new FileInputStream(f)) {
			byte[] data = new byte[input.available()];
			input.read(data);
			return data;
		} catch (IOException e) {
			st.setError(true);
			st.setErrorMessage("i/o error");
			throw e;
		}
	}
}

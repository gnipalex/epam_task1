package com.epam.hnyp.task6.subtask5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

import com.epam.hnyp.task6.subtask3.SearchStatus;

public class SequenceFinderRunnableWaitNotify implements Runnable {
//	private static final long FILE_WAIT_TIMEOUT = 500;
//	private static final long OFFER_WAIT_TIMEOUT = 200;

	private final Object SET_DATA_MONITOR = new Object();
	private final Object GET_STATUS_MONITOR = new Object();
	
	private byte[] data;
	
	private SearchStatusExtended actualCurentStatus;

	public void setData(byte[] data) {
		synchronized (SET_DATA_MONITOR) {
			this.data = data;
			SET_DATA_MONITOR.notify();
		}
	}
	
	public  SearchStatusExtended getActualCurentStatus() {
		return actualCurentStatus;
	}
	
	@Override
	public void run() {
		while (true) {
			synchronized (SET_DATA_MONITOR) {
				try {
					SET_DATA_MONITOR.wait();
				} catch (InterruptedException e) {
					continue;
				}
			}
			curentStatus = new SearchStatusExtended();
			int offset1 = 0, offset2 = 0, len = 0;
			for (int i = 0; i < data.length - 2; i++) {
				offset1 = i;
				for (int j = 2; j < data.length - i; j++) {
					len = j;
					for (int g = i + 1; g + len <= data.length; g++) {
						offset2 = g;
						if (len > curentStatus.getLength()
								&& sequencesEqual(data, offset1, offset2, len)) {
							curentStatus.setLength(len);
							curentStatus.setOffsetFirst(offset1);
							curentStatus.setOffsetSecond(offset2);
							//passing search info and notifying other thread to read data
							//synchExchangeStatus(statusesQueueMonitor, new SearchStatusExtended(status));
							break;
						}
					}

				}
			}
			
			
//			String fileName = null;
//			synchronized (paramContainerMonitor) {
//				//waiting for fileName from other thread
//				while (true) {
//					try {
//						paramContainerMonitor.wait(FILE_WAIT_TIMEOUT);
//						if (paramContainerMonitor.getFileName() == null) {
//							continue;
//						}
//						fileName = paramContainerMonitor.getFileName();
//						paramContainerMonitor.setFileName(null);
//						break;
//					} catch (InterruptedException e) {
//					}
//				}
//			}
//			Queue<SearchStatusExtended> statusesQueueMonitor = paramContainerMonitor.getStatusesQueue();
//			statusesQueueMonitor.clear();
//			byte[] data = null;
//			try {
//				data = readFile(new File(fileName),	status);
//			} catch (IOException e) {
//				synchronized (statusesQueueMonitor) {
//					status.setFinish(true);
//					statusesQueueMonitor.offer(new SearchStatusExtended(status));
//					statusesQueueMonitor.notify();
//				}
//				continue;
//			}
//			int offset1 = 0, offset2 = 0, len = 0;
//			for (int i = 0; i < data.length - 2; i++) {
//				offset1 = i;
//				for (int j = 2; j < data.length - i; j++) {
//					len = j;
//					for (int g = i + 1; g + len <= data.length; g++) {
//						offset2 = g;
//						if (len > status.getLength()
//								&& sequencesEqual(data, offset1, offset2, len)) {
//							status.setLength(len);
//							status.setOffsetFirst(offset1);
//							status.setOffsetSecond(offset2);
//							//passing search info and notifying other thread to read data
//							synchExchangeStatus(statusesQueueMonitor, new SearchStatusExtended(status));
//							break;
//						}
//					}
//
//				}
//			}
//			//passing search info and notifying other thread that search reached end
//			status.setFinish(true);
//			synchExchangeStatus(statusesQueueMonitor, new SearchStatusExtended(status));
		}
	} 
	
//	private void synchExchangeStatus(Queue<SearchStatusExtended> statusesQueueMonitor, 
//			SearchStatusExtended passStatus) {
//		synchronized (statusesQueueMonitor) {
//			while(!statusesQueueMonitor.offer(passStatus)) {
//				statusesQueueMonitor.notify();
//				try {
//				statusesQueueMonitor.wait(OFFER_WAIT_TIMEOUT);
//				} catch (InterruptedException e) {}
//			}
//			statusesQueueMonitor.notify();
//		}
//	}

	private boolean sequencesEqual(byte[] data, int offset1, int offset2,
			int length) {
		for (int i = 0; i < length; i++) {
			if (data[offset1 + i] != data[offset2 + i]) {
				return false;
			}
		}
		return true;
	}

//	private byte[] readFile(File f, SearchStatus st) throws IOException {
//		if (!f.exists()) {
//			st.setError(true);
//			st.setErrorMessage("file does't exist");
//			throw new IOException();
//		}
//		if (f.length() == 0) {
//			st.setError(true);
//			st.setErrorMessage("file is empty");
//			throw new IOException();
//		}
//		try (InputStream input = new FileInputStream(f)) {
//			byte[] data = new byte[input.available()];
//			input.read(data);
//			return data;
//		} catch (IOException e) {
//			st.setError(true);
//			st.setErrorMessage("i/o error");
//			throw e;
//		}
//	}
}

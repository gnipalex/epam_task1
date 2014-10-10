package com.epam.hnyp.task6.subtask5;

public class SequenceFinderRunnableWaitNotify implements Runnable {
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

	public SearchStatusExtended getActualCurentStatus() {
		synchronized (GET_STATUS_MONITOR) {
			return actualCurentStatus;
		}
	}

	@Override
	public void run() {
		synchronized (SET_DATA_MONITOR) {
			while (true) {
				try {
					SET_DATA_MONITOR.wait();
				} catch (InterruptedException e) {
					continue;
				}

				SearchStatusExtended curentStatus = new SearchStatusExtended();
				int offset1 = 0, offset2 = 0, len = 0;
				for (int i = 0; i < data.length - 2; i++) {
					offset1 = i;
					for (int j = 2; j < data.length - i; j++) {
						len = j;
						for (int g = i + 1; g + len <= data.length; g++) {
							offset2 = g;
							if (len > curentStatus.getLength()
									&& sequencesEqual(data, offset1, offset2,
											len)) {
								curentStatus.setLength(len);
								curentStatus.setOffsetFirst(offset1);
								curentStatus.setOffsetSecond(offset2);
								synchronized (GET_STATUS_MONITOR) {
									this.actualCurentStatus = new SearchStatusExtended(
											curentStatus);
								}
								break;
							}
						}
					}
				}
				curentStatus.setFinish(true);
				synchronized (GET_STATUS_MONITOR) {
					this.actualCurentStatus = new SearchStatusExtended(
							curentStatus);
				}
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
}

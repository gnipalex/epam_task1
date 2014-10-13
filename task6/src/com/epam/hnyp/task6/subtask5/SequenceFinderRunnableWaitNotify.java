package com.epam.hnyp.task6.subtask5;

public class SequenceFinderRunnableWaitNotify implements Runnable {
	private final Object dataMonitor = new Object();

	private byte[] data;

	private volatile SearchStatusExtended actualCurentStatus;

	public void setData(byte[] data) {
		synchronized (dataMonitor) {
			this.data = data;
			dataMonitor.notify();
		}
	}

	public SearchStatusExtended getActualCurentStatus() {
		return actualCurentStatus;
	}

	@Override
	public void run() {
		synchronized (dataMonitor) {
			while (true) {
				try {
					dataMonitor.wait();
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
								this.actualCurentStatus = new SearchStatusExtended(
										curentStatus);
								break;
							}
						}
					}
				}
				curentStatus.setFinish(true);
				this.actualCurentStatus = new SearchStatusExtended(curentStatus);
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

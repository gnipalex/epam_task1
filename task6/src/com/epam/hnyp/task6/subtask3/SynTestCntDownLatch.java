package com.epam.hnyp.task6.subtask3;

import java.util.concurrent.CountDownLatch;

public class SynTestCntDownLatch {
	public static void main(String[] args) {
		final int thrCount = 5;
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch endSignal = new CountDownLatch(thrCount);
		System.out.println("MAIN: Thread starting ...");
		for (int i=0; i<thrCount; i++) {
			new Thread(new Runner(startSignal, endSignal)).start();
		}
		startSignal.countDown();
		System.out.println("MAIN: finished");
	}
	
	static class Runner implements Runnable {
		private CountDownLatch startSignal;
		private CountDownLatch endSignal;
		
		public Runner(CountDownLatch startSignal, CountDownLatch endSignal) {
			this.startSignal = startSignal;
			this.endSignal = endSignal;
		}

		@Override
		public void run() {
			try {
				startSignal.await();
				System.out.println(Thread.currentThread().getName());
				endSignal.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}	
	}
}

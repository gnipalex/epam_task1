package com.epam.hnyp.task6;

public class Test {
	private static final Object monitor = new Object();
	
	public static void main(String[] args) {
		Thread t = new Thread(new MyRunner());
		t.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (monitor) {
			monitor.notify();
			System.out.println("MAIN после notify");
		}
	}
	
	public static class MyRunner implements Runnable {

		@Override
		public void run() {
			synchronized (monitor) {
				System.out.println("THREAD Waiting");
				try {
					monitor.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
					return;
				}
			}
			System.out.println("THREAD поток дождался сигнала");
		}
		
	}
}

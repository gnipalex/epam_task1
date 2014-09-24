package com.epam.hnyp.task4.subtask2;

public class Demo {
	public static void main(String[] args) {
		IProduct p = new ProductImpl(1, "aaa", 99);
		IProduct proxy = ProductProxyFactory.createProxyProduct(p);

		System.out.println(proxy.getId());
		p.setId(11);
		System.out.println(proxy.getId());
		try {
			proxy.setId(0);
		} catch (Exception e) {
			System.out.println("All ok, proxy is not modified");
		}
	}
}

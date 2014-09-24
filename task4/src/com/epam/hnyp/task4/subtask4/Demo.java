package com.epam.hnyp.task4.subtask4;

public class Demo {
	public static void main(String[] args) {
		HumanByMap h = HumanByMapProxyFactory.createProxyHuman();
		h.setAge(15);
		
		System.out.println(h.getAge());
		System.out.println(h.getClass());
	}
}

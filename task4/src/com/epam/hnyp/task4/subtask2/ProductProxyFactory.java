package com.epam.hnyp.task4.subtask2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.naming.OperationNotSupportedException;

public class ProductProxyFactory {
	public static IProduct createProxyProduct(IProduct realProduct){
		return (IProduct)Proxy.newProxyInstance(realProduct.getClass().getClassLoader(), 
				realProduct.getClass().getInterfaces(), new MyInvocationHandler(realProduct));
	}
	
	public static class MyInvocationHandler implements InvocationHandler {
		private Object realObject;
		
		public MyInvocationHandler(Object realObject) {
			this.realObject = realObject;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			if (method.getName().startsWith("set")) {
				throw new OperationNotSupportedException();
			}
			return method.invoke(realObject, args);
		}	
	}
}

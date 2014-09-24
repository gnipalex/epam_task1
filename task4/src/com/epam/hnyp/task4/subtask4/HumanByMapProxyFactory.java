package com.epam.hnyp.task4.subtask4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class HumanByMapProxyFactory {
	 public static HumanByMap createProxyHuman() {
		 Class[] interfaces = {HumanByMap.class};
		 return (HumanByMap)Proxy.newProxyInstance(HumanByMap.class.getClassLoader(), interfaces, new HumanByMapInvocationHandler());
	 }

	public static class HumanByMapInvocationHandler implements
			InvocationHandler {
		private Map<String, Object> fieldsMap = new HashMap<>();

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			String name = method.getName();
			String fieldName = getFieldName(name);
			if (fieldName != null) {
				if (name.startsWith("is") || name.startsWith("get")) {
					return fieldsMap.get(fieldName);
				}
				if (name.startsWith("set") && args.length > 0) {
					fieldsMap.put(fieldName, args[0]);
					return Void.TYPE;
				}
			}
			throw new UnsupportedOperationException();
		}

		private String getFieldName(String methodName) {
			if (methodName.startsWith("get") || methodName.startsWith("set")) {
				if (methodName.length() <= 3) {
					return null;
				}
				return methodName.substring(3);
			} else if (methodName.startsWith("is")) {
				if (methodName.length() <= 2) {
					return null;
				}
				return methodName.substring(2);
			}
			return methodName;
		}

	}
}

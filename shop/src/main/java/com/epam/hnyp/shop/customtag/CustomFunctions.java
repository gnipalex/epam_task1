package com.epam.hnyp.shop.customtag;

import java.util.Collection;

public class CustomFunctions {
	public static boolean contains(Collection c, Object item) {
		if (c == null || item == null) {
			return false;
		}
		return c.contains(item);
	}
}

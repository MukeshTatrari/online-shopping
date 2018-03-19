package com.mukesh.onlineshopping.util;


import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public abstract class ObjectUtils {
	public static boolean isEmpty(Object object) {
		if (object == null) {
			return true;
		}
		if (object instanceof String)
			return StringUtils.isEmpty((String) object);
		if (object instanceof Number)
			return (((Number) object).longValue() == 0L);
		if (object instanceof Collection)
			return ((Collection) object).isEmpty();
		if (object instanceof Map)
			return ((Map) object).isEmpty();
		if (object instanceof Object[])
			return (((Object[]) (Object[]) object).length == 0);
		if (object instanceof int[])
			return (((int[]) (int[]) object).length == 0);
		if (object instanceof long[])
			return (((long[]) (long[]) object).length == 0);
		if (object instanceof short[])
			return (((short[]) (short[]) object).length == 0);
		if (object instanceof boolean[])
			return (((boolean[]) (boolean[]) object).length == 0);
		if (object instanceof byte[])
			return (((byte[]) (byte[]) object).length == 0);
		if (object instanceof float[])
			return (((float[]) (float[]) object).length == 0);
		if (object instanceof double[])
			return (((double[]) (double[]) object).length == 0);
		if (object instanceof char[]) {
			return (((char[]) (char[]) object).length == 0);
		}
		return false;
	}

	public static String print(Collection<?> collection) {
		StringBuilder bul = new StringBuilder("{");
		Iterator it = collection.iterator();
		while (it.hasNext()) {
			Object obj = it.next();
			bul.append(obj.toString());
			if (it.hasNext()) {
				bul.append(",");
			}
		}
		bul.append("}");
		return bul.toString();
	}

	public static String print(Long[] collection) {
		StringBuilder bul = new StringBuilder("{");
		for (int i = 0; i < collection.length; ++i) {
			bul.append(collection[i].toString());
			if (i < collection.length - 1) {
				bul.append(",");
			}
		}
		bul.append("}");
		return bul.toString();
	}

	public static String print(Object[] collection) {
		StringBuilder bul = new StringBuilder("{");
		for (int i = 0; i < collection.length; ++i) {
			bul.append(collection[i].toString());
			if (i < collection.length - 1) {
				bul.append(",");
			}
		}
		bul.append("}");
		return bul.toString();
	}
}
package com.mukesh.onlineshopping.util;

public abstract class StringUtils {
	public static boolean isEmpty(String string) {
		return ((string == null) || (string.length() == 0));
	}

	public static String[] addStringToArray(String[] strarr, String inString) {
		if (ObjectUtils.isEmpty(strarr)) {
			return new String[] { inString };
		}
		String[] newArr = new String[strarr.length + 1];
		System.arraycopy(strarr, 0, newArr, 0, strarr.length);
		newArr[strarr.length] = inString;
		return newArr;
	}

	public static String[] concatenateStringArrays(String[][] arrays) {
		String[] newArr = null;
		for (String[] array : arrays) {
			if (!(ObjectUtils.isEmpty(array))) {
				String[] arr = (newArr == null) ? new String[array.length]
						: new String[newArr.length + array.length];
				if (newArr != null) {
					System.arraycopy(newArr, 0, arr, 0, newArr.length);
					System.arraycopy(array, 0, arr, newArr.length, array.length);
				} else {
					System.arraycopy(array, 0, arr, 0, array.length);
				}
				newArr = arr;
			}
		}
		return newArr;
	}

	public static String stackTraceToString(Throwable e) {
		StringBuilder sb = new StringBuilder();
		for (StackTraceElement element : e.getStackTrace()) {
			sb.append(element.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
}
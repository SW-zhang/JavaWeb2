package com.framework.utils;

import java.util.Arrays;

public class BytesUtil {

	/**
	 * byte[]转int
	 * 
	 * @param bytes
	 * @return
	 */
	public static int toInt(byte[] bytes) {
		if (bytes.length > 4) {
			return (int) toLong(Arrays.copyOf(bytes, 4));
		}
		return (int) toLong(bytes);
	}

	/**
	 * byte[]转short
	 * 
	 * @param bytes
	 * @return
	 */
	public static short toShort(byte[] bytes) {
		if (bytes.length > 2) {
			return (short) toLong(Arrays.copyOf(bytes, 2));
		}
		return (short) toLong(bytes);
	}

	/**
	 * byte[]转long
	 * 
	 * @param bytes
	 * @return
	 */
	public static long toLong(byte[] bytes) {
		if (bytes.length == 0) {
			return 0L;
		}
		long addr = 0;
		for (int i = 0; i < bytes.length; i++) {
			addr |= (bytes[i] & 0xFFL) << (8 * i);
		}
		return addr;
	}

	/**
	 * byte[]转float
	 * 
	 * @param bytes
	 * @return
	 */
	public static float toFloat(byte[] bytes) {
		if (bytes.length == 0) {
			return 0L;
		}
		int i = toInt(bytes);
		return Float.intBitsToFloat(i);
	}

	/**
	 * byte[]转double
	 * 
	 * @param bytes
	 * @return
	 */
	public static double toDouble(byte[] bytes) {
		if (bytes.length == 0) {
			return 0L;
		}
		long l = toLong(bytes);
		return Double.longBitsToDouble(l);
	}

	/**
	 * byte[]转化成16进制
	 * 
	 * @param bytes
	 * @return
	 */
	public static String toHex(byte[] bytes) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bytes.length; i++) {
			tmp = (Integer.toHexString(bytes[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}
}

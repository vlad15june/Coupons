package org.coupons.util;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

public class Hasher {
	
	public static String hashEncode(byte[] bytes, Algorithms algorithm) {
		String hashValue = "";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm.getAlgorithm());
			messageDigest.update(bytes);
			byte[] diggestedBytes = messageDigest.digest();
			hashValue = DatatypeConverter.printHexBinary(diggestedBytes).toLowerCase();
		} catch (Exception e) {}
		return hashValue;
	}
	
}
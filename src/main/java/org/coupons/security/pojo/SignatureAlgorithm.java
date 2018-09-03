package org.coupons.security.pojo;

public enum SignatureAlgorithm {

	// symmetric algorithm (only private key)
	HS256;

	public String getFullName() {
		String res = null;
		switch (this) {
		case HS256:
			res = "HmacSHA256";
			break;
		default:
			break;
		}
		return res;

	}
}

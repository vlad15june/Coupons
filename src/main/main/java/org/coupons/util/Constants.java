package org.coupons.util;

public interface Constants {

	int SUCCESSFUL_OPERATION = 1;
	int ERROR = 0;
	
	String LOGIN_END_POINT = "/coupons/login";
	String REFRESH_END_POINT = "/coupons/refresh";
	String POST_HTTP_METHOD = "POST";
	
	String BEARER_TOKEN = "Bearer ";
	String REFRESH_TOKEN = "Refresh ";
	
	String SIGNING_ALGORITHM_NAME = "HS256";
	String SIGNING_ALGORITHM_FULL_NAME = "HmacSHA256";
	long ACCESS_DURATION_IN_MINUTES = 100;
	long REFRESH_DURATION_IN_MINUTES = 10 * 24 * 60;
	String JWT_ISSUER = "Nick.org";
}
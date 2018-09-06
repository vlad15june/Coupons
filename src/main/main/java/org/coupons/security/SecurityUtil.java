package org.coupons.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

import org.coupons.util.JsonUtil;
import org.coupons.util.StrUtil;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public final class SecurityUtil {

	private static long CLOCK_SKEW_IN_SECONDS = 5L;
	
	private SecurityUtil() {
	}

	public static boolean credentialsCheck(final String userId, final String password) {
		boolean result = false;
		if (!StrUtil.isEmpty(userId) && !StrUtil.isEmpty(password)) {

			// TODO: credentials check
			result = true;
		}
		return result;

	}
	
	public static boolean credentialsCheck(final JsonElement userId, final JsonElement password) {
		boolean result = false;
		if( userId != null && password != null) {
			result = credentialsCheck(userId.getAsString(), password.getAsString());
		}
		
		return result;
	}
	
	public static boolean expirationTimeCheck(final String payload) {
		byte[] decoded = Base64.getMimeDecoder().decode(payload);
		String payloadDecoded = new String(decoded);
		JsonObject payloadJson = JsonUtil.strToJsonObject(payloadDecoded);
		JsonElement expirationTimeInSeconds = payloadJson.get("exp");
		long nowTimeInSeconds = Instant.ofEpochSecond(0L).until(Instant.now(),
                ChronoUnit.SECONDS);
		
		// TODO: check if expirationTimeInSeconds doesn't of type long
		long expTimeLong = expirationTimeInSeconds != null ? expirationTimeInSeconds.getAsLong() : -1L;
		
		return nowTimeInSeconds < expTimeLong + CLOCK_SKEW_IN_SECONDS;
	}
}

package org.coupons.security;

import java.util.HashMap;
import java.util.Map;

import org.coupons.pojo.Role;
import org.coupons.security.pojo.JWTComtentType;
import org.coupons.security.pojo.TokenContainer;
import org.coupons.util.Constants;

import com.google.gson.JsonObject;

public class ApiSecurityExample {

	public static void main(String[] args) {
		try {

			JsonObject accessTokenHeader = AuthHelper.createTokenHeader(JWTComtentType.ACCESS_TOKEN);

			Map<String, String> otherClaims = new HashMap<>();
			otherClaims.put("https://www.coupons.org", Boolean.TRUE.toString());
			
			JsonObject accessTokenPayload = AuthHelper.createTokenPayload("userId", Role.ADMIN, "nick.org",
					Constants.ACCESS_DURATION_IN_MINUTES, otherClaims);

			JsonObject refreshTokenPayload = AuthHelper.createTokenPayload("userId", Role.ADMIN, "nick.org",
					Constants.REFRESH_DURATION_IN_MINUTES, otherClaims);
			
			JsonObject refreshTokenHeader = AuthHelper.createTokenHeader(JWTComtentType.REFRESH_TOKEN);

			String accessToken = AuthHelper.createToken(accessTokenHeader, accessTokenPayload);
			String refreshToken = AuthHelper.createToken(refreshTokenHeader, refreshTokenPayload);

			String signedAccessToken = AuthHelper.signingTokenSH256(accessToken);
			String signedRefreshToken = AuthHelper.signingTokenSH256(refreshToken);

			System.out.println(signedAccessToken);
			System.out.println(signedRefreshToken);

			TokenContainer tokenContainer = new TokenContainer(signedRefreshToken, signedAccessToken,
					Constants.ACCESS_DURATION_IN_MINUTES);
			
			System.out.println(tokenContainer.toJsonString());
			
		} catch (Exception e) {
			System.out.println("Error");
		}
	}

}

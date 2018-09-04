package org.coupons.security;

import java.util.HashMap;
import java.util.Map;

import org.coupons.pojo.Role;
import org.coupons.security.pojo.JWTComtentType;
import org.coupons.security.pojo.TokenContainer;
import org.coupons.util.Constants;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.lang.InvalidKeyException;

import com.google.gson.JsonObject;
import com.networknt.handler.Handler;
import com.networknt.security.JwtVerifyHandler;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class CustomJwtVerifyHandler extends JwtVerifyHandler {

	/**
	 * @see com.networknt.security.JwtVerifyHandler#handleRequest(io.undertow.server.HttpServerExchange)
	 * 
	 * 
	 */
	public void handleRequest(final HttpServerExchange exchange) throws Exception {

		final boolean isLoginRequest = Constants.LOGIN_END_POINT.equals(exchange.getRequestPath());

		final boolean isPostMethod = Constants.POST_HTTP_METHOD
				.equalsIgnoreCase(exchange.getRequestMethod().toString());

		if (isLoginRequest && isPostMethod) {

			// Only for login request

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

			TokenContainer tokenContainer = new TokenContainer(signedRefreshToken, signedAccessToken,
					Constants.ACCESS_DURATION_IN_MINUTES);
			
			exchange.getResponseHeaders().add(new HttpString("Authorization"), tokenContainer.toJsonString());
			Handler.next(exchange, super.getNext());
		} else {
			
			// JWT verification
			if(AuthHelper.verifyToken(exchange)) {
				Handler.next(exchange, super.getNext());
			}else {
				throw new Exception("Invalid Jwt Exception");
			}
			
//			super.handleRequest(exchange);
		}

	}
}

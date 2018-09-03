package org.coupons.security;

import org.coupons.util.Const;

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
	public void handleRequest(final HttpServerExchange exchange)
			throws Exception {

		final boolean isLoginRequest = Const.LOGIN_END_POINT
				.equals(exchange.getRequestPath());

		final boolean isPostMethod = Const.POST_HTTP_METHOD
				.equalsIgnoreCase(exchange.getRequestMethod().toString());

		if (isLoginRequest && isPostMethod) {

			// Only for login request
			
			String accessToken = AuthHelper.createAccessToken("userId", 10L);
			String refreshToken = AuthHelper.createAccessToken("userId", 10L);
			
			JsonObject tokenContainer = new JsonObject();
			tokenContainer.addProperty("token_type","bearer");
			tokenContainer.addProperty("access_token", accessToken);
			tokenContainer.addProperty("access_expires_in", 10);
			tokenContainer.addProperty("refresh_token", refreshToken);
			
			System.out.println(tokenContainer);
			exchange.getResponseHeaders().add(new HttpString("Authorization"), tokenContainer.toString());
//			Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//			
//			String jws = Jwts.builder().setSubject("Joe").signWith(key).compact();
//			
//			exchange.getRequestHeaders().add(Headers.AUTHORIZATION, "Bearer " + jws);
//			System.out.println(exchange.getResponseHeaders());
//			super.handleRequest(exchange);
			Handler.next(exchange, super.getNext());
		} else {

			// JWT verification
			super.handleRequest(exchange);
		}

	}
}

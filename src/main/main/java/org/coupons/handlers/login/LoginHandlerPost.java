package org.coupons.handlers.login;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.coupons.security.AuthHelper;
import org.coupons.security.SecurityUtil;
import org.coupons.security.pojo.TokenContainer;
import org.coupons.util.HandlerUtil;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class LoginHandlerPost implements HttpHandler {

	private static String USER_ID = "userId";
	private static String PASSWORD = "password";

	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		
		JsonObject requestBody = HandlerUtil.extractPostRequestBody(exchange);

		JsonElement userId = requestBody.get(USER_ID);
		JsonElement password = requestBody.get(PASSWORD);

		exchange.getRequestHeaders().add(new HttpString("Content-Type"), "application/json");

		if (SecurityUtil.credentialsCheck(userId, password)) {
			
			String jwtId = UUID.randomUUID().toString();
			
			Map<String, String> otherClaimsHeader = new HashMap<>();
			
			// "jti" (JWT ID) Claim 
			otherClaimsHeader.put("jti", jwtId);
			
			Map<String, String> otherClaimsPayload = new HashMap<>();
			
			TokenContainer tokenContainer = AuthHelper.jwtGenerator(userId.getAsString(), otherClaimsHeader, otherClaimsPayload);
			
			// TODO: save refresh token to DB with jti primary key
			
			exchange.getResponseHeaders().add(new HttpString("Authorization"), tokenContainer.toJsonString());
			exchange.getResponseSender().send("Login Handler Works (POST)" + requestBody);
		} else {
			exchange.getResponseSender().send("Wrong credentials" + requestBody);
		}

	}
}

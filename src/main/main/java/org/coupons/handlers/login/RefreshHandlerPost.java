package org.coupons.handlers.login;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.coupons.security.AuthHelper;
import org.coupons.security.pojo.TokenContainer;
import org.coupons.util.Constants;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class RefreshHandlerPost  implements HttpHandler{

	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		
		final String[] parts = AuthHelper.extractToken(exchange);
		final String header = parts[0];
		final String payload = parts[1];
		
		final String jwtId = AuthHelper.extractValueFromToken(header, "jti");
		//TODO: check jwtId against DB, in case of success delete the row in other case send ERROR to client
		
		final String userId = AuthHelper.extractValueFromToken(payload, "userId");
		final String newJwtId = UUID.randomUUID().toString();
		
		final Map<String, String> otherClaimsHeader = new HashMap<>();
		
		// "jti" (JWT ID) Claim 
		otherClaimsHeader.put("jti", newJwtId);
		
		final Map<String, String> otherClaimsPayload = new HashMap<>();
		
		final TokenContainer tokenContainer = AuthHelper.jwtGenerator(userId, otherClaimsHeader, otherClaimsPayload);
		
		// TODO: save refresh token to DB with newJwtId primary key
		
		exchange.getResponseHeaders().add(new HttpString("Authorization"), tokenContainer.toJsonString());
		exchange.getResponseSender().send("Refresh Handler Works (POST)");
	}

}

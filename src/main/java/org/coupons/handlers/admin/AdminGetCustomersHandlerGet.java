package org.coupons.handlers.admin;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class AdminGetCustomersHandlerGet implements HttpHandler{

	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		exchange.getRequestHeaders().add(new HttpString("Content-Type"), "application/json");
		exchange.getResponseSender().send("Admin get customers works (GET)");
	}
	
}
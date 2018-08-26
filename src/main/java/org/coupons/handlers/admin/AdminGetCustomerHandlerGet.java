package org.coupons.handlers.admin;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class AdminGetCustomerHandlerGet implements HttpHandler{

	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		exchange.getRequestHeaders().add(new HttpString("Content-Type"), "application/json");
		String id = exchange.getQueryParameters().get("customerId").getFirst();
		exchange.getResponseSender().send("Admin get customer with id: " + id + " works (GET)");
	}

}

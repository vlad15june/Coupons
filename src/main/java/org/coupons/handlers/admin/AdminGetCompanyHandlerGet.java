package org.coupons.handlers.admin;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class AdminGetCompanyHandlerGet implements HttpHandler{

	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		exchange.getRequestHeaders().add(new HttpString("Content-Type"), "application/json");
		String id = exchange.getQueryParameters().get("companyId").getFirst();
		exchange.getResponseSender().send("Admin get company with id: " + id + " works (GET)");
	}

}

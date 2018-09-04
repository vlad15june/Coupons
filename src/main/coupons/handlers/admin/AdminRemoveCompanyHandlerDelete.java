package org.coupons.handlers.admin;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class AdminRemoveCompanyHandlerDelete implements HttpHandler{

	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		exchange.getRequestHeaders().add(new HttpString("Content-Type"), "application/json");
		String companyId = exchange.getQueryParameters().get("companyId").getFirst();
		exchange.getResponseSender().send("admin delete company with id: " + companyId + " work (DELETE)");
	}
	
}













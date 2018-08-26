package org.coupons.handlers.customer;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class CustomerGetCouponsPriceHandlerGet implements HttpHandler{

	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		exchange.getRequestHeaders().add(new HttpString("Content-Type"), "application/json");
		String customerId = exchange.getQueryParameters().get("customerId").getFirst();
		String price = exchange.getQueryParameters().get("price").getFirst();
		exchange.getResponseSender().send("customer " + customerId + " get coupons price " + price + "works (GET)");
	}

}

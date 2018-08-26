package org.coupons.handlers.customer;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class CustomerPurchaseCouponHandlerPost implements HttpHandler{

	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		exchange.getRequestHeaders().add(new HttpString("Content-Type"), "application/json");
		String customerId = exchange.getQueryParameters().get("customerId").getFirst();
		String couponId = exchange.getQueryParameters().get("couponId").getFirst();
		exchange.getResponseSender().send("Customer " + customerId + " purchased coupon " + couponId + " works (GET)");
	}

}

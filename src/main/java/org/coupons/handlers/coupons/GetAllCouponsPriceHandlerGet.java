package org.coupons.handlers.coupons;

import java.util.Deque;
import java.util.List;

import org.coupons.dbo.CouponDAO;
import org.coupons.pojo.Coupon;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class GetAllCouponsPriceHandlerGet implements HttpHandler {

	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		exchange.getRequestHeaders().add(new HttpString("Content-Type"), "application/json");

		ObjectMapper mapper = new ObjectMapper();
		
		Deque<String> deq = exchange.getQueryParameters().get("price");
		double price = Double.parseDouble(deq.getFirst());

		List<Coupon> coupons = CouponDAO.getCouponsByPrice(price);
		exchange.getResponseSender().send(mapper.writeValueAsString(coupons));
	}

}

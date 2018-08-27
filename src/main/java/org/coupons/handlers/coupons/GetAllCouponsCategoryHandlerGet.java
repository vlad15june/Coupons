package org.coupons.handlers.coupons;

import java.util.Deque;
import java.util.List;

import org.coupons.dbo.CouponDAO;
import org.coupons.pojo.Category;
import org.coupons.pojo.Coupon;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class GetAllCouponsCategoryHandlerGet implements HttpHandler {

	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		exchange.getRequestHeaders().add(new HttpString("Content-Type"), "application/json");

		ObjectMapper mapper = new ObjectMapper();
		
		Deque<String> deq = exchange.getQueryParameters().get("category");
		Category category = Category.valueOf(deq.getFirst());

		List<Coupon> coupons = CouponDAO.getCouponsByCategory(category);
		exchange.getResponseSender().send(mapper.writeValueAsString(coupons));
	}

}

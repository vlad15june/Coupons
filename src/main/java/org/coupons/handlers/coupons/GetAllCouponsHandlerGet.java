package org.coupons.handlers.coupons;

import java.util.List;

import org.coupons.dbo.CouponDAO;
import org.coupons.pojo.Coupon;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class GetAllCouponsHandlerGet implements HttpHandler {

	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		exchange.getRequestHeaders().add(new HttpString("Content-Type"), "application/json");
		
		List<Coupon> coupons = CouponDAO.getAllCoupons();
		ObjectMapper mapper = new ObjectMapper();
		
		String json = mapper.writeValueAsString(coupons);
		exchange.getResponseSender().send(json);
	}

}

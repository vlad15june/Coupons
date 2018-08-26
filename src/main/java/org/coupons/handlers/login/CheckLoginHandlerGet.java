package org.coupons.handlers.login;

import org.coupons.dbo.UserDAO;
import org.coupons.pojo.Role;
import org.coupons.pojo.User;
import org.coupons.util.JSONConvertor;
import org.jose4j.json.internal.json_simple.JSONObject;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class CheckLoginHandlerGet implements HttpHandler{
	
	@SuppressWarnings("unchecked")
	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		exchange.getRequestHeaders().add(new HttpString("Content-Type"), "application/json");
		User user = UserDAO.getUser(new User("kolya666kv@gmail.com", "nick190494", Role.CUSTOMER));
		JSONObject json = JSONConvertor.userToJSON(user);
		exchange.getResponseSender().send(json.toJSONString());
	}

}

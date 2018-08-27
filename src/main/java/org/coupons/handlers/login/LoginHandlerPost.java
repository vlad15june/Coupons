package org.coupons.handlers.login;

import java.util.Map;

import org.coupons.dbo.UserDAO;
import org.coupons.pojo.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.body.BodyHandler;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class LoginHandlerPost implements HttpHandler {

	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		exchange.getRequestHeaders().add(new HttpString("Content-Type"), "application/json");
		
        ObjectMapper mapper = new ObjectMapper();


        // add a new object
        Map s = (Map)exchange.getAttachment(BodyHandler.REQUEST_BODY);
        String json = mapper.writeValueAsString(s);
        User user = mapper.readValue(json, User.class);

		
		user = UserDAO.getUser(user.getEmail(), user.getPassword());
		exchange.getResponseSender().send(mapper.writeValueAsString(user));
	}
}

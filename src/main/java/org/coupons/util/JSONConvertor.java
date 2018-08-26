package org.coupons.util;

import org.coupons.pojo.User;
import org.jose4j.json.internal.json_simple.JSONObject;

public class JSONConvertor {
	
	@SuppressWarnings("unchecked")
	public static JSONObject userToJSON(User user) {
		JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("email", user.getEmail());
		json.put("password", user.getPassword());
		json.put("role", user.getRole());
		return json;
	}
	
}
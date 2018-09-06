package org.coupons.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public final class JsonUtil {

	private JsonUtil() {
	}

	public static JsonObject strToJsonObject(String jsonString) {

		Gson gson = new Gson();
		JsonObject jsonObject = null;
		try {
			jsonObject = gson.fromJson(jsonString, JsonObject.class);
		}catch(Exception e) {
			//TODO: write log JsonSyntaxException
		}
		return jsonObject;
	}
}

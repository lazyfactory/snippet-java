package utils;

import com.google.gson.*;

public class JsonParse {
  public static JsonObject parse(String str) {
    JsonParser parser = new JsonParser();
    JsonElement element = parser.parse(str);
    return element.getAsJsonObject();
 }

	static String getJson(JsonObject respObj) {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		
		return gson.toJson(respObj);
	}
  
}

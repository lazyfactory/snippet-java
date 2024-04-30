package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonParse {
  public static JsonObject parse(String json) {
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

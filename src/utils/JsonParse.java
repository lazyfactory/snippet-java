package utils;

import com.google.gson.*;

public class JsonParse {
	static void makeJson() {
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();        
		jsonObject.addProperty("name", "anna");        
		jsonObject.addProperty("id", 1);         // JsonObject를 Json 문자열로 변환        
		String jsonStr = gson.toJson(jsonObject);
	}
	
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

package utils;

import java.util.*;https://github.com/lazyfactory/snippet-java/tree/main/src/utils
import java.io.*;
import com.google.gson.*;

public class JsonParse {
  public static JsonObject parse(String json) {
    JsonParser parser = new JsonParser();
    JsonElement element = parser.parse(str);
    return element.getAsJsonObject();
 }
}

package utils;

import java.util.*;
import java.io.*;
import com.google.gson.*;

public class FileUtil {
	public static void main(String[] args) {
		List<String> list = readFile("sample.txt");
		System.out.println(list.get(0));

		readJsonFile("sample.json");

		saveFile("test.txt", "test data");
	}

	public static List<String> readFile(String path) {
		BufferedReader in = null;
		String line = null;

		List<String> list = new ArrayList<>();

		try {
			in = new BufferedReader(new FileReader(path));

			while ((line = in.readLine()) != null) {
				list.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public static void readJsonFile(String path) {
		Reader reader;
		try {
			reader = new FileReader(path);

			Gson gson = new Gson();
			JsonObject jsonObj = gson.fromJson(reader, JsonObject.class);

			int port = jsonObj.get("port").getAsInt();
			JsonArray routes = jsonObj.get("routes").getAsJsonArray();

			HashMap<String, String> routesMap = new HashMap<String, String>();
			for (JsonElement route : routes) {
				JsonObject obj = gson.fromJson(route, JsonObject.class);
				routesMap.put(obj.get("pathPrefix").getAsString(), obj.get("url").getAsString());
			}

			System.out.println(port);
			System.out.println(jsonObj.toString());

			// add
			jsonObj.addProperty("newProp", "newValue");
			System.out.println(jsonObj.toString());
			
			JsonElement newElement = gson.fromJson("{\"new\":\"prop\"}", JsonElement.class);
			jsonObj.add("newObj", newElement);
			System.out.println(gson.toJson(jsonObj));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void saveFile(String path, String data) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(path, true);
			fw.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// e.g. path = SUPPORT\\CREATE.EXE
	public static void execute(String path, String data) {

		try {
			ProcessBuilder processBuilder = new ProcessBuilder();
			// Windows

			processBuilder.command("cmd.exe", "/c", path, data);

			// saveFile("OUTFILE/TEMP.TXT", data);
			// processBuilder.redirectInput(new File("OUTFILE", "TEMP.txt"));
			// processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT);

			// processBuilder.command("cmd", "/c", "dir");

			// processBuilder.directory(new File("."));
			Process process = processBuilder.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			// Reader inputString = new StringReader(data);
			// BufferedReader reader = new BufferedReader(inputString);

			String line;
			while ((line = reader.readLine()) != null) {
				line = reader.readLine();
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

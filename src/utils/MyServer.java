package utils;

import java.io.*;
import java.util.*;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.*;
import com.google.gson.*;

public class MyServer {
	int port;
	static HashMap<String, String> routesMap = new HashMap<String, String>();

	public void start(String proxy) throws Exception {
		parse(proxy);

		Server server = new Server();
		ServerConnector http = new ServerConnector(server);
		http.setHost("127.0.0.1");
		http.setPort(port);
		server.addConnector(http);

		ServletHandler servletHandler = new ServletHandler();

		for (String uri : routesMap.keySet()) {
			System.out.println(uri);
			servletHandler.addServletWithMapping(MyServlet.class, uri+"/*");
		}

		server.setHandler(servletHandler);

		server.start();
		server.join();
	}

	public void parse(String proxy) throws FileNotFoundException {
		Reader reader = new FileReader(proxy);
		Gson gson = new Gson();
		JsonObject jsonObj = gson.fromJson(reader, JsonObject.class);

		port = jsonObj.get("port").getAsInt();
		JsonArray routes = jsonObj.get("routes").getAsJsonArray();

		for (JsonElement route : routes) {

			JsonObject obj = gson.fromJson(route, JsonObject.class);
			routesMap.put(obj.get("pathPrefix").getAsString(), obj.get("url").getAsString());
		}

	}
}

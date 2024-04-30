package utils;

import java.io.*;
import java.net.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println(req.getRequestURI());
		System.out.println(req.getMethod());
		System.out.println(req.getQueryString());
		System.out.println(req.getContextPath());

		String pathPrefix = req.getRequestURI().split("/").length > 1 ? "/" + req.getRequestURI().split("/")[1]
				: req.getRequestURI();
		String url = MyServer.routesMap.get(pathPrefix) + req.getRequestURI();
		String queryString = req.getQueryString();
		if (queryString != null) {
			url += "?" + queryString;
		}

		res = request(url, req.getMethod(), res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println(req.getRequestURI());
		System.out.println(req.getMethod());
		System.out.println(req.getQueryString());
		System.out.println(req.getContextPath());

		String pathPrefix = req.getRequestURI().split("/").length > 1 ? "/" + req.getRequestURI().split("/")[1]
				: req.getRequestURI();
		String url = MyServer.routesMap.get(pathPrefix) + req.getRequestURI();
		String queryString = req.getQueryString();
		if (queryString != null) {
			url += "?" + queryString;
		}

		res = request(url, req.getMethod(), res);
	}

	static JsonObject getBody(HttpServletRequest req) {
		StringBuffer json = new StringBuffer();
		String line = null;

		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return (JsonObject) JsonParser.parseString(json.toString());
	}

	// public static String getBody(HttpServletRequest request) throws IOException {
	 
	//         String body = null;
	//         StringBuilder stringBuilder = new StringBuilder();
	//         BufferedReader bufferedReader = null;
	 
	//         try {
	//             InputStream inputStream = request.getInputStream();
	//             if (inputStream != null) {
	//                 bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	//                 char[] charBuffer = new char[128];
	//                 int bytesRead = -1;
	//                 while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	//                     stringBuilder.append(charBuffer, 0, bytesRead);
	//                 }
	//             }
	//         } catch (IOException ex) {
	//             throw ex;
	//         } finally {
	//             if (bufferedReader != null) {
	//                 try {
	//                     bufferedReader.close();
	//                 } catch (IOException ex) {
	//                     throw ex;
	//                 }
	//             }
	//         }
	 
	//         body = stringBuilder.toString();
	//         return body;
	// }

	HttpServletResponse request(String reqUrl, String method, HttpServletResponse res) throws IOException {
		System.out.println(reqUrl + " requested....");
		URL url = new URL(reqUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod(method);
//		connection.setRequestProperty("User-Agent", USER_AGENT); // for header
        connection.setDoOutput(true);

        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeBytes("DATA");
        outputStream.flush();
        outputStream.close();
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuffer stringBuffer = new StringBuffer();
		String inputLine;

		while ((inputLine = bufferedReader.readLine()) != null) {
			stringBuffer.append(inputLine);
		}
		bufferedReader.close();

		String response = stringBuffer.toString();

		System.out.println(connection.getResponseCode());
		System.out.println(connection.getContentType());

		System.out.println(response);

		res.setStatus(connection.getResponseCode());
		res.setHeader("Content-type", connection.getContentType());
		res.getWriter().write(response);

		return res;
	}
}

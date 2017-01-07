package com.fmi.rmi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ApiUtils {

	public static String makeRequestToApi(String url, HashMap<String, String> requestParams) throws IOException {
		StringBuilder sb = new StringBuilder(url);
		Iterator<Map.Entry<String, String>> it = requestParams.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, String> pair = (Map.Entry<String, String>) it.next();
			sb.append(pair.getKey()).append("=").append(pair.getValue());
			if(it.hasNext()) {
				sb.append("&");
			}
		}
		System.out.println("REQUEST URL IS " + sb.toString());
		url = sb.toString();
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}
}

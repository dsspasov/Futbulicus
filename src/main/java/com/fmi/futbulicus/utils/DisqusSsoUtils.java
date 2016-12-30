package com.fmi.futbulicus.utils;

import java.util.HashMap;

import org.apache.commons.codec.digest.HmacUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DisqusSsoUtils {
	
	private static final String SECRET_KEY = "mDiCKBOYdHbvYqkVQR4KqJXB61OK8NXlJCXTlDcOIFlbX6z7eUblqGL3qvCAKXSF";
	private static final String THREAD_ID = "5420484089";
	private static final String FORUM = "futbulicus";
	
	
	/**
	 * Generates remote_auth_s3 parameter needed for performing /posts/create on Disqus.
	 * @return remote_auth_s3 parameter's value
	 * @throws JsonProcessingException
	 */
	public static String generateRemoteAuthS3() throws JsonProcessingException {
		 
		// User data, replace values with authenticated user data
		HashMap<String,String> message = new HashMap<String,String>();
		message.put("id","1");
		message.put("username","qwe");
		message.put("email","qwe@abv.bg");
		 
		// Encode user data
		ObjectMapper mapper = new ObjectMapper();
		 
		String jsonMessage = mapper.writeValueAsString(message);
		 
		String base64EncodedStr = new String(Base64.encodeBase64(jsonMessage.getBytes()));
		System.out.println("message is " + base64EncodedStr);
		// Get the timestamp
		long timestamp = System.currentTimeMillis()/1000;
		 
		// Assemble the HMAC-SHA1 signature
		String signature = HmacUtils.hmacSha1Hex( SECRET_KEY, base64EncodedStr + " " + timestamp);
		 
		// Output string to use in remote_auth_s3 variable
		return (base64EncodedStr + " " + signature + " " + timestamp);
	}
}

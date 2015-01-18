package com.pc.rest.core.builder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UrlBuilder {

	private static final Logger logger = Logger.getLogger(UrlBuilder.class);
	
	@Value("${key}")
	private String key;
	
	@Value("${cx}")
	private String cx;
	
	@Value("${searchUrl}")
	private String searchUrl;

	public String getPlagrismSearchUrl(String query){
		try {
			StringBuilder strBuilder = new StringBuilder(searchUrl);
			strBuilder.append("?").append("key=").append(key).append("&").append("cx=").append(cx);
			strBuilder.append("&q=").append(URLEncoder.encode(query, "UTF-8"));
			System.out.println("Search url is " + strBuilder.toString());
			return strBuilder.toString();
		} catch (UnsupportedEncodingException e) {
           logger.error("Exception occured during building url " + e.getMessage());			
		}
		return null;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCx() {
		return cx;
	}

	public void setCx(String cx) {
		this.cx = cx;
	}
	
}

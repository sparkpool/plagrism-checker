package com.pc.rest.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.rest.common.http.PCHttpClient;
import com.pc.rest.core.builder.UrlBuilder;
import com.pc.rest.pojo.PlagrismSearchResponse;
import com.pc.rest.service.intrface.ISearchContentService;

@Service("searchContentService")
public class SearchContentServiceImpl implements ISearchContentService{

	private static final Logger logger = Logger.getLogger(SearchContentServiceImpl.class);
	
	@Autowired
	private UrlBuilder urlBuilder;

	@Override
    public PlagrismSearchResponse getSearchResult(String query){
		logger.info("Query received is " + query);
    	String url = getUrlBuilder().getPlagrismSearchUrl(query);
    	if(url!=null){
    		return (PlagrismSearchResponse)PCHttpClient.executeGet(url, PlagrismSearchResponse.class);
    	}
    	return null;
    }
	
	public UrlBuilder getUrlBuilder() {
		return urlBuilder;
	}
	
}

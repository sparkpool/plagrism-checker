package com.pc.rest.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.rest.common.http.HkHttpClient;
import com.pc.rest.core.builder.UrlBuilder;
import com.pc.rest.pojo.PlagrismSearchResponse;
import com.pc.rest.service.intrface.ISearchContentService;

@Service("searchContentService")
public class SearchContentServiceImpl implements ISearchContentService{

	private static final Logger logger = Logger.getLogger(SearchContentServiceImpl.class);
	
	@Autowired
	private UrlBuilder urlBuilder;

	@Override
	@SuppressWarnings("unchecked")
    public PlagrismSearchResponse getSearchResult(String query){
    	String url = getUrlBuilder().getPlagrismSearchUrl(query);
    	if(url!=null){
    		return (PlagrismSearchResponse)HkHttpClient.executeGet(url, PlagrismSearchResponse.class);
    	}
    	return null;
    }
	
	public UrlBuilder getUrlBuilder() {
		return urlBuilder;
	}
	
}

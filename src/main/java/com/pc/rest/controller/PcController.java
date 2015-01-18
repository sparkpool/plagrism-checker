package com.pc.rest.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;

import com.pc.listener.InitiateSpringContextListener;
import com.pc.rest.pojo.PlagrismSearchResponse;
import com.pc.rest.service.intrface.ISearchContentService;

@Path("/search")
public class PcController {

	private static final Logger logger = Logger.getLogger(PcController.class);
	
	private ISearchContentService searchContentService;
	
	@GET
	@Path("/content")
	@Produces("application/json")
	public String searchContent(@QueryParam("q") String query){
		logger.info("Query received in request is " + query);
		if(query == null || query.trim().length() == 0){
			return null;
		}
		PlagrismSearchResponse plagrismResponse = getSearchService().getSearchResult(query);
		if(plagrismResponse!=null){
			return plagrismResponse.toJSON();
		}
		return null;
	}
	
	public ISearchContentService getSearchService(){
		if(searchContentService == null){
			searchContentService = (ISearchContentService)InitiateSpringContextListener.getContext().getBean("searchContentService");
		}
		return searchContentService;
	}
}

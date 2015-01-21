package com.pc.rest.controller;

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
		PlagrismSearchResponse plagrismResponse = null;
		if(query == null || query.trim().length() == 0 || query.length()>1500){
			plagrismResponse = new PlagrismSearchResponse();
			plagrismResponse.addMessage("Entered Message Must be Non Empty and Length Must be Less than 1500");
			plagrismResponse.setError("403");
			return plagrismResponse.toJSON();
		}
		plagrismResponse = getSearchService().getSearchResult(query);
		if(plagrismResponse == null){
			plagrismResponse = new PlagrismSearchResponse();
			plagrismResponse.setError("500");
			plagrismResponse.addMessage("There came an Exception, please try again!!");
		}else if(plagrismResponse!=null && plagrismResponse.getErrorResponse()!=null){
			plagrismResponse.addMessage(plagrismResponse.getErrorResponse().getMessage());
			plagrismResponse.setError(plagrismResponse.getErrorResponse().getCode());
		}
		return plagrismResponse.toJSON();
	}
	
	public ISearchContentService getSearchService(){
		if(searchContentService == null){
			searchContentService = (ISearchContentService)InitiateSpringContextListener.getContext().getBean("searchContentService");
		}
		return searchContentService;
	}
}

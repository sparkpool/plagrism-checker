package com.pc.rest.controller;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;

import com.pc.listener.InitiateSpringContextListener;
import com.pc.rest.pojo.PlagrismSearchResponse;
import com.pc.rest.service.intrface.IPDFCreateService;
import com.pc.rest.service.intrface.ISearchContentService;

@Path("/search")
public class PcController {

	private static final Logger logger = Logger.getLogger(PcController.class);

	private ISearchContentService searchContentService;
	private IPDFCreateService pdfCreateService;

	@GET
	@Path("/content")
	@Produces("application/json")
	public String searchContent(@QueryParam("q") String query) {
		logger.info("Query received in request is " + query);
		PlagrismSearchResponse plagrismResponse = validateInput(query);
		if(plagrismResponse == null){
			plagrismResponse = getSearchService().getSearchResult(query);
			if (plagrismResponse == null) {
					plagrismResponse = new PlagrismSearchResponse();
					plagrismResponse.setError("500");
					plagrismResponse
							.addMessage("There came an Exception, please try again!!");
			} else if (plagrismResponse != null
						&& plagrismResponse.getErrorResponse() != null) {
					plagrismResponse.addMessage(plagrismResponse.getErrorResponse()
							.getMessage());
					plagrismResponse.setError(plagrismResponse.getErrorResponse()
							.getCode());
			}	
		}
		return plagrismResponse.toJSON();
	}

	@GET
	@Path("/content/pdf")
	@Produces("application/pdf")
	public Response getFile(@QueryParam("q") String query) {
		logger.info("Query received in request is " + query);
		PlagrismSearchResponse plagrismResponse = validateInput(query);
		if(plagrismResponse == null){
			File file = getPDFCreateService().createPDF(query);
            if(file == null){
            	plagrismResponse = new PlagrismSearchResponse();
				plagrismResponse.setError("500");
				plagrismResponse.addMessage("There came an Exception, please try again!!");
				ResponseBuilder response = Response.ok(plagrismResponse);
				return response.build();
            }else{
    			ResponseBuilder response = Response.ok((Object) file);
    			response.header("Content-Disposition","attachment; filename=PCResponse.pdf");
    			return response.build();	
            }	
		}else{
			ResponseBuilder response = Response.ok(plagrismResponse);
			return response.build();
		}
	}
	
	private PlagrismSearchResponse validateInput(String query){
		PlagrismSearchResponse plagrismResponse = null;
		if (query == null || query.trim().length() == 0	|| query.length() > 1500) {
			plagrismResponse = new PlagrismSearchResponse();
			plagrismResponse
					.addMessage("Entered Message Must be Non Empty and Length Must be Less than 1500");
			plagrismResponse.setError("403");
		}
		return plagrismResponse;
	}
	
	public ISearchContentService getSearchService() {
		if (searchContentService == null) {
			searchContentService = (ISearchContentService) InitiateSpringContextListener
					.getContext().getBean("searchContentService");
		}
		return searchContentService;
	}
	
	public IPDFCreateService getPDFCreateService(){
		if(pdfCreateService == null){
			pdfCreateService = (IPDFCreateService) InitiateSpringContextListener.
					getContext().getBean("pdfCreateService");
		}
		return pdfCreateService;
	}
	
}

package com.pc.rest.service.intrface;

import com.pc.rest.pojo.PlagrismSearchResponse;

public interface ISearchContentService {

	public PlagrismSearchResponse getSearchResult(String query);
	
}

package com.fmi.futbulicus.service;

import java.io.IOException;
import java.util.List;

import com.fmi.futbulicus.model.Fixture;

public interface SearchService {
		
	List<Fixture> getFootballerByName(String name) throws IOException;

}

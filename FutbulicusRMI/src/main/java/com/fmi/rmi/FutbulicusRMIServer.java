package com.fmi.rmi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;

import com.fmi.rmi.service.SearchService;
import com.fmi.rmi.service.SearchServiceImpl;

public class FutbulicusRMIServer {
	
	@Bean(name="SearchService")
	public SearchService getSearchService(){
		return new SearchServiceImpl();
	}
	
	@Bean
	public RmiServiceExporter registerService(SearchService searchService) {
	    RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
	    rmiServiceExporter.setServiceName("SearchService");
	    rmiServiceExporter.setService(searchService);
	    rmiServiceExporter.setServiceInterface(SearchService.class);
	    rmiServiceExporter.setRegistryPort(1099);
	    
	
	    return rmiServiceExporter;
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(FutbulicusRMIServer.class);
		System.out.println(context);
	}

}

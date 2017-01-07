package com.fmi.futbulicus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fmi.futbulicus.utils.DisqusSsoUtils;

@SpringBootApplication
public class App {

	 public static void main( String[] args ) throws JsonProcessingException
	    {
	    	SpringApplication.run(App.class, args);
	    	System.err.println("greshka:" + DisqusSsoUtils.generateRemoteAuthS3());
	    }
}

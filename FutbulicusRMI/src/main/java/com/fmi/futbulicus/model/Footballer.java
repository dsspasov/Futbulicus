package com.fmi.futbulicus.model;

import java.io.Serializable;

import com.google.gson.JsonObject;

public class Footballer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JsonObject data;
	
	public Footballer(){
		
	}

	public JsonObject getData() {
		return data;
	}

	public void setData(JsonObject data) {
		this.data = data;
	}	
}

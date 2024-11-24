package com.learning.kafka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDto {

	private int id;
	//@JsonProperty("name")
	private String name;
	
	
	@Override
	public String toString() {
		return "CustomerDto [id=" + id + ", name=" + name + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getString() {
		return name;
	}
	public void setString(String name) {
		this.name = name;
	} 
	
	
}

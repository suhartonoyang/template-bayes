package com.project.templatebayes.bean;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request {
	private String nameAttribute;
	private String valueAttribute;

	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Request(String nameAttribute, String valueAttribute) {
		super();
		this.nameAttribute = nameAttribute;
		this.valueAttribute = valueAttribute;
	}

	@Override
	public String toString() {
		return "Request [nameAttribute=" + nameAttribute + ", valueAttribute=" + valueAttribute + "]";
	}

}

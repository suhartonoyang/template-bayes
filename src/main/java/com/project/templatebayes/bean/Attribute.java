package com.project.templatebayes.bean;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Attribute {

	private String name;
	private List<String> values;

	public Attribute() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attribute(String name, List<String> values) {
		super();
		this.name = name;
		this.values = values;
	}

	@Override
	public String toString() {
		return "Attribute [name=" + name + ", values=" + values + "]";
	}

}

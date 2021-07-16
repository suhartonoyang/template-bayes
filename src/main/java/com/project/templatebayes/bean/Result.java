package com.project.templatebayes.bean;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
	private String name;
	private double value;
	private boolean isSelected;
	private String journey;

	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Result(String name, double value, boolean isSelected) {
		super();
		this.name = name;
		this.value = value;
		this.isSelected = isSelected;
	}

	@Override
	public String toString() {
		return "Result [name=" + name + ", value=" + value + ", isSelected=" + isSelected + ", journey=" + journey + "]";
	}

}

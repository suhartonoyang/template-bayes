package com.project.templatebayes.bean;

import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapAttribute {
	private String nameAttribute;
	private String valueAttribute;
	private String result;
	private int count;
	private double prob;
	private boolean isSelected;

	private MapAttribute newMapAttribute;

	public MapAttribute() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MapAttribute(String nameAttribute, String valueAttribute, String result, int count, double prob) {
		super();
		this.nameAttribute = nameAttribute;
		this.valueAttribute = valueAttribute;
		this.result = result;
		this.count = count;
		this.prob = prob;
	}

	@Transient
	private boolean isAnyZeroCount;

	public boolean isAnyZeroCount() {
		return this.newMapAttribute != null;
	}

	@Override
	public String toString() {
		return "MapAttribute [nameAttribute=" + nameAttribute + ", valueAttribute=" + valueAttribute + ", result=" + result + ", count="
				+ count + ", prob=" + prob + ", isSelected=" + isSelected + ", newMapAttribute=" + newMapAttribute + ", isAnyZeroCount="
				+ isAnyZeroCount + "]";
	}

}

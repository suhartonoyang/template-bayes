// Generated with g9.

package com.project.templatebayes.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "data_training")
public class DataTraining implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(name = "parameter_1", nullable = false, length = 255)
	private String parameter1;
	@Column(name = "parameter_2", nullable = false, length = 255)
	private String parameter2;
	@Column(name = "parameter_3", nullable = false, length = 255)
	private String parameter3;
	@Column(name = "parameter_4", nullable = false, length = 255)
	private String parameter4;
	@Column(name = "parameter_5", nullable = false, length = 255)
	private String parameter5;
	@Column(name = "parameter_6", nullable = false, length = 255)
	private String parameter6;
	@Column(name = "parameter_7", nullable = false, length = 255)
	private String parameter7;
	@Column(name = "parameter_8", nullable = false, length = 255)
	private String parameter8;
	@Column(name = "parameter_9", nullable = false, length = 255)
	private String parameter9;
	@Column(name = "parameter_10", nullable = false, length = 255)
	private String parameter10;
	@Column(nullable = false, length = 255)
	private String result;
	@ManyToOne(optional = false)
	@JoinColumn(name = "type_id", nullable = false)
	@JsonIgnoreProperties(value = "dataTrainings")
	private MstType mstType;

	@Override
	public String toString() {
		return "DataTraining [id=" + id + ", parameter1=" + parameter1 + ", parameter2=" + parameter2 + ", parameter3=" + parameter3
				+ ", parameter4=" + parameter4 + ", parameter5=" + parameter5 + ", parameter6=" + parameter6 + ", parameter7=" + parameter7
				+ ", parameter8=" + parameter8 + ", parameter9=" + parameter9 + ", parameter10=" + parameter10 + ", result=" + result
				+ ", mstType=" + mstType + "]";
	}

}

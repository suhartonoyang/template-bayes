// Generated with g9.

package com.project.templatebayes.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity(name = "mst_type")
public class MstType implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(nullable = false, length = 255)
	private String type;
	@OneToMany(mappedBy = "mstType")
	@JsonIgnoreProperties(value = "mstType")
	private List<DataTraining> dataTrainings;
	@OneToMany(mappedBy = "mstType")
	@JsonIgnoreProperties(value = "mstType")
	private List<MstMapTypeParameter> mstMapTypeParameters;

	@Override
	public String toString() {
		return "MstType [id=" + id + ", type=" + type + ", dataTrainings=" + dataTrainings + ", mstMapTypeParameters="
				+ mstMapTypeParameters + "]";
	}

}

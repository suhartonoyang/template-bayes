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

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "mst_parameter")
public class MstParameter implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(nullable = false, length = 255)
	private String name;
	@OneToMany(mappedBy = "mstParameter")
	@JsonIgnoreProperties(value = "mstParameter")
	private List<MstMapTypeParameter> mstMapTypeParameters;

	@Override
	public String toString() {
		return "MstParameter [id=" + id + ", name=" + name + ", mstMapTypeParameters=" + mstMapTypeParameters + "]";
	}

}

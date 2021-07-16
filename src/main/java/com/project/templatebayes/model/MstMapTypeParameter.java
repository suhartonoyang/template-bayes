// Generated with g9.

package com.project.templatebayes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "mst_map_type_parameter")
public class MstMapTypeParameter implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(name = "parameter_seq", nullable = false, precision = 10)
	private int parameterSeq;
	@ManyToOne(optional = false)
	@JoinColumn(name = "type_id", nullable = false)
	@JsonIgnoreProperties(value = "mstMapTypeParameters")
	private MstType mstType;
	@ManyToOne(optional = false)
	@JoinColumn(name = "parameter_id", nullable = false)
	@JsonIgnoreProperties(value = "mstMapTypeParameters")
	private MstParameter mstParameter;

	@Override
	public String toString() {
		return "MstMapTypeParameter [id=" + id + ", parameterSeq=" + parameterSeq + ", mstType=" + mstType + ", mstParameter="
				+ mstParameter + "]";
	}

}

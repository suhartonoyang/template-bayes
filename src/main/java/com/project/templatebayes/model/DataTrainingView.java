package com.project.templatebayes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "data_training_v")
public class DataTrainingView implements Serializable {

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "data_training_id")
	private int dataTrainingId;
	@Column(name = "type_id")
	private int typeId;
	@Column(name = "type")
	private String type;
	@Column(name = "parameter_id")
	private int parameterId;
	@Column(name = "parameter_name")
	private String parameterName;
	@Column(name = "parameter_seq")
	private int parameterSeq;
	@Column(name = "parameter_value")
	private String parameterValue;
	@Column(name = "result")
	private String result;

	@Override
	public String toString() {
		return "DataTrainingView [id=" + id + ", dataTrainingId=" + dataTrainingId + ", typeId=" + typeId + ", type=" + type
				+ ", parameterId=" + parameterId + ", parameterName=" + parameterName + ", parameterSeq=" + parameterSeq
				+ ", parameterValue=" + parameterValue + ", result=" + result + "]";
	}

}

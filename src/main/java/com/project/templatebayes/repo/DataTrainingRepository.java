package com.project.templatebayes.repo;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.templatebayes.model.DataTraining;

@Repository
public interface DataTrainingRepository extends CrudRepository<DataTraining, Integer>{

	public List<DataTraining> findByMstType_id(Integer typeId);
	
}

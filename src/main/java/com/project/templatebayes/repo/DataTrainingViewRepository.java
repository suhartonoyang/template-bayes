package com.project.templatebayes.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.templatebayes.model.DataTrainingView;

@Repository
public interface DataTrainingViewRepository extends CrudRepository<DataTrainingView, String> {

	public List<DataTrainingView> findByTypeId(Integer typeId);
	
}

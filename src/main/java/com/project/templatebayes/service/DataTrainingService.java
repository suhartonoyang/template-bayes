package com.project.templatebayes.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.templatebayes.model.DataTraining;
import com.project.templatebayes.model.DataTrainingView;
import com.project.templatebayes.repo.DataTrainingRepository;
import com.project.templatebayes.repo.DataTrainingViewRepository;

@Service
public class DataTrainingService {

	@Autowired
	private DataTrainingRepository dataTrainingHdrRepository;

	@Autowired
	private DataTrainingViewRepository dataTrainingViewRepository;

	public List<DataTraining> getAllData() {
		return StreamSupport.stream(dataTrainingHdrRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public List<DataTraining> getAllDataByType(Integer typeId) {
		List<DataTraining> dataTraining = dataTrainingHdrRepository.findByMstType_id(typeId);
		return dataTraining;
	}

	public List<DataTrainingView> getAllDataView() {
		return StreamSupport.stream(dataTrainingViewRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public List<DataTrainingView> getAllDataByTypeView(Integer typeId) {
		List<DataTrainingView> dataTrainingView = dataTrainingViewRepository.findByTypeId(typeId);
		return dataTrainingView;
	}
	
}

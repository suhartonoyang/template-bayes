package com.project.templatebayes.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.templatebayes.model.MstType;
import com.project.templatebayes.repo.MstTypeRepository;

@Service
public class MstTypeService {

	@Autowired
	private MstTypeRepository mstTypeRepo;

	public List<MstType> getAllData() {
		return StreamSupport.stream(mstTypeRepo.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public MstType getDataById(Integer typeId) {
		return mstTypeRepo.findById(typeId).orElse(null);
	}
}

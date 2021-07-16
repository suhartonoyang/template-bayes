package com.project.templatebayes.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.templatebayes.model.MstParameter;
import com.project.templatebayes.repo.MstParameterRepository;

@Service
public class MstParameterService {

	@Autowired
	private MstParameterRepository mstParameterRepository;

	public List<MstParameter> getAllData() {
		return StreamSupport.stream(mstParameterRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}
}

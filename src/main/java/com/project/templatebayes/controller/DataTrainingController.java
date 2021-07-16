package com.project.templatebayes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.templatebayes.bean.Response;
import com.project.templatebayes.model.DataTraining;
import com.project.templatebayes.model.DataTrainingView;
import com.project.templatebayes.service.DataTrainingService;
import com.project.templatebayes.util.Constant;

@RestController
@RequestMapping("/api/data-training")
@CrossOrigin
public class DataTrainingController {

	@Autowired
	private DataTrainingService dataTrainingService;

	@GetMapping
	public ResponseEntity<Response> getAllData() {
		Response resp = new Response();
		List<DataTraining> data = dataTrainingService.getAllData();

		resp.setCode(Constant.SUCCESS_CODE);
		resp.setMessage(Constant.SUCCESS_MESSAGE);
		if (data.isEmpty()) {
			resp.setCode(Constant.EMPTY_DATA_CODE);
			resp.setMessage(Constant.EMPTY_DATA_DEFAULT_MESSAGE);
		}

		resp.setData(data);

		return ResponseEntity.ok(resp);
	}

	@GetMapping("/type/{typeId}")
	public ResponseEntity<Response> getAllDataByType(@PathVariable Integer typeId) {
		Response resp = new Response();
		List<DataTraining> data = dataTrainingService.getAllDataByType(typeId);

		resp.setCode(Constant.SUCCESS_CODE);
		resp.setMessage(Constant.SUCCESS_MESSAGE);
		if (data.isEmpty()) {
			resp.setCode(Constant.EMPTY_DATA_CODE);
			resp.setMessage(Constant.EMPTY_DATA_DEFAULT_MESSAGE);
		}

		resp.setData(data);

		return ResponseEntity.ok(resp);
	}
	
	@GetMapping("/view")
	public ResponseEntity<Response> getAllDataView() {
		Response resp = new Response();
		List<DataTrainingView> data = dataTrainingService.getAllDataView();

		resp.setCode(Constant.SUCCESS_CODE);
		resp.setMessage(Constant.SUCCESS_MESSAGE);
		if (data.isEmpty()) {
			resp.setCode(Constant.EMPTY_DATA_CODE);
			resp.setMessage(Constant.EMPTY_DATA_DEFAULT_MESSAGE);
		}

		resp.setData(data);

		return ResponseEntity.ok(resp);
	}
	
	@GetMapping("/view/type/{typeId}")
	public ResponseEntity<Response> getAllDataByTypeView(@PathVariable Integer typeId) {
		Response resp = new Response();
		List<DataTrainingView> data = dataTrainingService.getAllDataByTypeView(typeId);

		resp.setCode(Constant.SUCCESS_CODE);
		resp.setMessage(Constant.SUCCESS_MESSAGE);
		if (data.isEmpty()) {
			resp.setCode(Constant.EMPTY_DATA_CODE);
			resp.setMessage(Constant.EMPTY_DATA_DEFAULT_MESSAGE);
		}

		resp.setData(data);

		return ResponseEntity.ok(resp);
	}
}

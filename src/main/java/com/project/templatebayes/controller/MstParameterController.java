package com.project.templatebayes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.templatebayes.bean.Response;
import com.project.templatebayes.model.MstParameter;
import com.project.templatebayes.service.MstParameterService;
import com.project.templatebayes.util.Constant;

@RestController
@RequestMapping("/api/parameter")
@CrossOrigin
public class MstParameterController {
	
	@Autowired
	private MstParameterService mstParameterService;

	@GetMapping
	public ResponseEntity<Response> getAllData() {
		Response resp = new Response();
		List<MstParameter> data = mstParameterService.getAllData();

		resp.setCode(Constant.SUCCESS_CODE);
		if (data.isEmpty()) {
			resp.setCode(Constant.EMPTY_DATA_CODE);
			resp.setMessage(Constant.EMPTY_DATA_DEFAULT_MESSAGE);
		}

		resp.setMessage(Constant.SUCCESS_MESSAGE);
		resp.setData(data);

		return ResponseEntity.ok(resp);
	}
}

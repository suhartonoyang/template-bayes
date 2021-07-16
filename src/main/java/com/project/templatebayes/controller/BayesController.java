package com.project.templatebayes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.templatebayes.bean.Request;
import com.project.templatebayes.bean.Response;
import com.project.templatebayes.bean.Result;
import com.project.templatebayes.model.MstType;
import com.project.templatebayes.service.BayesNaiveService;
import com.project.templatebayes.service.MstTypeService;
import com.project.templatebayes.util.Constant;

@RestController
@RequestMapping("/api/bayes")
@CrossOrigin
public class BayesController {

	@Autowired
	private BayesNaiveService bayesNaiveService;

	@Autowired
	private MstTypeService mstTypeService;

	@PostMapping("/run/type/{typeId}")
	public ResponseEntity<?> run(@PathVariable Integer typeId, @RequestBody List<Request> requests) {
		Response resp = new Response();

		resp.setCode(Constant.SUCCESS_CODE);
		resp.setMessage(Constant.SUCCESS_MESSAGE);

		// validate typeId
		MstType type = mstTypeService.getDataById(typeId);
		if (type == null) {
			resp.setCode(Constant.EMPTY_DATA_CODE);
			resp.setMessage(Constant.EMPTY_DATA_DEFAULT_MESSAGE + " for typeId " + typeId);
			return ResponseEntity.ok(resp);
		}

		// validate request
		String validate = bayesNaiveService.validateRequests(type, requests);
		if (validate!=null) {
			resp.setCode(Constant.ERROR_VALIDATE_CODE);
			resp.setMessage(validate);
			return ResponseEntity.ok(resp);
		}
		
		List<Result> result = bayesNaiveService.run(typeId, requests);

		if (result == null) {
			resp.setCode(Constant.EMPTY_DATA_CODE);
			resp.setMessage(Constant.EMPTY_DATA_DEFAULT_MESSAGE + " for data training with typeId " + typeId);
			return ResponseEntity.ok(resp);
		}

		resp.setData(result);
		return ResponseEntity.ok(resp);
	}
}

package com.project.templatebayes.bean;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {

	private String code;
	private String message;
	private List<?> data;

	public Response() {

	}

	public Response(String code, String message, List<?> data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response [code=" + code + ", message=" + message + ", data=" + data + "]";
	}

}

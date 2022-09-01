package com.elcallao.emit.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;


@Data
@JsonInclude(value = Include.NON_NULL)
public class Response {
    
	private Integer statusCode;
	private Object data;
	private String message;
	private List<Map<String, String>> messages;

	private Integer duplicated;


	public Response(Integer statusCode, Object data) {
		super();
		this.statusCode = statusCode;
		this.data = data;
	}

	public Response(Integer statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	public Response(List<Map<String, String>> messages ,Integer statusCode) {
		super();
		this.statusCode = statusCode;
		this.messages = messages;
	}
}

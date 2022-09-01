package com.elcallao.emit.advice;

import com.elcallao.emit.constant.Constants;
import com.elcallao.emit.model.ErrorResponse;
import com.elcallao.emit.util.EmitUtil;
import com.elcallao.emit.util.TRBUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import com.elcallao.emit.model.Response;
import org.springframework.web.client.ResourceAccessException;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author chetan d
 * @since 28 Aug 2022
 * @desc Global Exception Handler.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response> handleMethodArgNotValidException(MethodArgumentNotValidException ex){
		StringBuilder message = new StringBuilder()
				.append(((FieldError)ex.getBindingResult().getAllErrors().get(0)).getField()).append(" ")
				.append(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(new Response(422 , message.toString())  , HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<Response> handleClientErrorException(HttpClientErrorException ex){
		ErrorResponse errorResponse  = ex.getResponseBodyAsString()!= null ? buildErrorResponse(ex.getResponseBodyAsString()) : null;
		if (errorResponse == null)
			log.error(Constants.TRB_UNKNOWN_EXCEPTION);
		return new ResponseEntity<>(new Response(422, errorResponse.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(ResourceAccessException.class)
	public ResponseEntity<Response> handleClientErrorException(ResourceAccessException ex){
		String message = (ex.getMessage() != null && ex.getMessage().contains("Connection timed out")) ? Constants.TRB_SERVICE_TIMEOUT : Constants.TRB_SERVICE_DOWN;
		log.error(ex.getMessage());
		return new ResponseEntity<>(new Response(500, message), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Response> handleNullException(NullPointerException ex){
		log.error(ex.getMessage());
		return new ResponseEntity<>(new Response(500, null), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public static ErrorResponse buildErrorResponse(String errorRes){
		ErrorResponse errorResponse = new ErrorResponse();
		Map<String, Object> mapRes = (LinkedHashMap<String, Object>) EmitUtil.convertJsonStringToObject(errorRes);
		return ErrorResponse.builder().status(mapRes.get("status").toString()).status_code(mapRes.get("status_code").toString())
				.message(mapRes.get("message").toString()).duplicated(mapRes.get("duplicated").toString()).build();
	}
}

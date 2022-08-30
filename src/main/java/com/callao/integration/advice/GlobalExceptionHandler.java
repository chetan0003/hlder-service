package com.callao.integration.advice;

import com.callao.integration.constant.Constants;
import com.callao.integration.util.TRBUtil;
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

import com.callao.integration.model.Response;
import org.springframework.web.client.ResourceAccessException;

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
		String responseAsJson = ex.getResponseBodyAsString();
		String message = responseAsJson != null ? TRBUtil.getFieldFromJson(responseAsJson, Constants.MESSAGE_KEY) : null;
		if (StringUtils.isEmpty(message))
			log.error(Constants.TRB_UNKNOWN_EXCEPTION);
		return new ResponseEntity<>(new Response(422, message), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(ResourceAccessException.class)
	public ResponseEntity<Response> handleClientErrorException(ResourceAccessException ex){
		log.error(ex.getMessage());
		return new ResponseEntity<>(new Response(500, "Service down..."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

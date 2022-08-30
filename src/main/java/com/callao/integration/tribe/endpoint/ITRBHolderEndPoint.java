package com.callao.integration.tribe.endpoint;

import org.springframework.http.ResponseEntity;

import com.callao.integration.model.Holder;
import com.callao.integration.model.Response;

public interface ITRBHolderEndPoint {
	public abstract ResponseEntity<Response> postHolder(Holder _holder);
	public abstract ResponseEntity<Response> getHolder(Long id);
}

package com.elcallao.emit.endpoint;

import org.springframework.http.ResponseEntity;

import com.elcallao.emit.model.Holder;
import com.elcallao.emit.model.Response;

public interface ITRBHolderEndPoint {
	public abstract ResponseEntity<Response> postHolder(Holder _holder);
	public abstract ResponseEntity<Response> getHolder(Long id);
	public abstract ResponseEntity<Response> getHolderAndAccounts(Long holderId);
}

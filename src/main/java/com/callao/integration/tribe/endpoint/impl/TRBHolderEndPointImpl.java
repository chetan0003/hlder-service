package com.callao.integration.tribe.endpoint.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.callao.integration.model.Holder;
import com.callao.integration.model.Response;
import com.callao.integration.tribe.endpoint.ITRBHolderEndPoint;
import com.callao.integration.tribe.service.ITRBHolderService;

@RestController
@RequestMapping("/holder")
public class TRBHolderEndPointImpl implements ITRBHolderEndPoint {
    
	@Autowired
	private ITRBHolderService holderService;
	
	@Override
	@PostMapping()
	public ResponseEntity<Response> postHolder(@Valid @RequestBody Holder _holder) {
		return new ResponseEntity<Response>(this.holderService.postHolder(_holder), HttpStatus.CREATED);
	}

    @Override
	@GetMapping("/{id}")
	public ResponseEntity<Response> getHolder(@PathVariable Long id) {
		return new ResponseEntity<Response>(this.holderService.getHolder(id), HttpStatus.OK);
	}

}

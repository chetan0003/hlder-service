package com.elcallao.emit.endpoint.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.elcallao.emit.model.Holder;
import com.elcallao.emit.model.Response;
import com.elcallao.emit.endpoint.ITRBHolderEndPoint;
import com.elcallao.emit.service.ITRBHolderService;

/**
 * @author chetan d
 * @since 28 Aug 2022
 * @desc Interface.
 */
@RestController
@RequestMapping("/holder")
public class TRBHolderEndPointImpl implements ITRBHolderEndPoint {
    
	@Autowired
	private ITRBHolderService holderService;
	
	@Override
	@PostMapping("/create")
	public ResponseEntity<Response> postHolder(@Valid @RequestBody Holder _holder) {
		return new ResponseEntity<Response>(this.holderService.postHolder(_holder), HttpStatus.CREATED);
	}

    @Override
	@GetMapping("/{holderId}")
	public ResponseEntity<Response> getHolder(@PathVariable Long holderId) {
		return new ResponseEntity<Response>(this.holderService.getHolder(holderId), HttpStatus.OK);
	}

	@Override
	@GetMapping("getHolderAndAccounts/{holderId}")
	public ResponseEntity<Response> getHolderAndAccounts(@PathVariable Long holderId) {
		return new ResponseEntity<Response>(this.holderService.getHolderAndAccounts(holderId), HttpStatus.OK);
	}

}

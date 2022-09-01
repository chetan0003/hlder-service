package com.elcallao.emit.service.impl;


import com.callao.emit.FeignService;
import com.elcallao.emit.advice.GlobalExceptionHandler;
import com.elcallao.emit.model.*;
import com.elcallao.emit.endpoint.service.TRBEndpointService;
import com.elcallao.emit.util.EmitUtil;
import com.elcallao.emit.util.TRBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import com.elcallao.emit.constant.Constants;
import com.elcallao.emit.service.ITRBHolderService;

import java.util.*;

/**
 * @author chetan d
 * @since 28 Aug 2022
 * Service.
 */
@Service
public class TRBHolderServiceImpl extends TRBEndpointService implements ITRBHolderService {
	Logger log = LoggerFactory.getLogger(TRBHolderServiceImpl.class);
	@Autowired
	private RestTemplate restTemplate;


	@Override
	public Response postHolder(Holder _holder) {
		_holder = buildCreateHolderRequest(_holder, Constants.TRB_CREATE_HOLDER_ACTION);
		log.info("Post Holder Request {} : " + EmitUtil.maskCredential(getTRBCredential(),_holder));
		ResponseEntity<Object> _response = this.restTemplate.postForEntity(System.getenv(Constants.TRB_API_URL),_holder,Object.class);
		log.info("Post Holder Response {} : " + EmitUtil.convertObjectToJsonString(_response.getBody()));
		Map<String, Object> _responseMap  = (Map<String, Object>) _response.getBody();
		Holder _holderRes = Holder.holderBuilder().holder_id(Long.valueOf(_responseMap.get(Constants.ID_KEY).toString())).build();
		return new Response(201,_holderRes);
	}
	@Override
	public Response getHolder(Long holderId) {
		GetHolder _getHolder = buildGetHolderRequest(holderId ,Constants.TRB_GET_HOLDER_ACTION);
		log.info("Get Holder Request {} : " + EmitUtil.maskCredential(getTRBCredential(),_getHolder));
		ResponseEntity<Object> _response = this.restTemplate.postForEntity(System.getenv(Constants.TRB_API_URL), _getHolder , Object.class);
		log.info("Get Holder Response {} : " + EmitUtil.convertObjectToJsonString(_response.getBody()));
		Map<String, Object> _responseMap  = (Map<String, Object>) _response.getBody();
		return new Response(200,_responseMap.get(Constants.DATA_KEY));
	}

	@Override
	public Response getHolderAndAccounts(Long holderId) {
		GetHolderAndAccounts _getHolderAndAccounts = buildGetHolderAndAccountsRequest(holderId , Constants.TRB_GET_HOLDER_AND_ACCOUNTS_ACTION);
		log.info("Get HolderAccount Request {} : " + EmitUtil.maskCredential(getTRBCredential(),_getHolderAndAccounts));
		ResponseEntity<Object> _response = this.restTemplate.postForEntity(System.getenv(Constants.TRB_API_URL), _getHolderAndAccounts , Object.class);
		log.info("Get HolderAccount Response {} : " + EmitUtil.convertObjectToJsonString(_response.getBody()));
		List<LinkedHashMap<String, Object>> _data =
				(ArrayList<LinkedHashMap<String, Object>>) ((Map<String, Object>) _response.getBody()).get(Constants.DATA_KEY);
		List<ResHolderAndAccount> _holderAndAccountsList = new ArrayList<>();
        buildCloHolderAndAccountsRes(_data , _holderAndAccountsList);
		return new Response(200,_holderAndAccountsList);
	}
}

package com.callao.integration.tribe.service.impl;

import com.callao.integration.model.GetHolder;
import com.callao.integration.tribe.endpoint.service.TRBEndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import com.callao.integration.constant.Constants;
import com.callao.integration.model.Holder;
import com.callao.integration.model.Response;
import com.callao.integration.tribe.service.ITRBHolderService;

import java.util.Map;

/**
 * @author chetan d
 * @since 28 Aug 2022
 * Service.
 */
@Service
public class TRBHolderServiceImpl extends TRBEndpointService implements ITRBHolderService {
    
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Response postHolder(Holder _holder) {
		_holder = buildCreateHolderRequest(_holder, Constants.TRB_CREATE_HOLDER_ACTION);
		ResponseEntity<Object> response = this.restTemplate.postForEntity(System.getenv(Constants.TRB_API_URL),_holder,Object.class);
		Map<String, Object> responseMap  = (Map<String, Object>) response.getBody();
		Holder _holderRes = Holder.holderBuilder().holder_id(Long.valueOf(responseMap.get(Constants.ID_KEY).toString())).build();
		return new Response(201,_holderRes);
	}

	@Override
	public Response getHolder(Long id) {
		GetHolder getHolder = buildGetHolderRequest(id ,Constants.TRB_GET_HOLDER_ACTION);
		ResponseEntity<Object> response = this.restTemplate.postForEntity(System.getenv(Constants.TRB_API_URL),getHolder , Object.class);
		Map<String, Object> responseMap  = (Map<String, Object>) response.getBody();
		return new Response(200,responseMap.get(Constants.DATA_KEY));
	}

}

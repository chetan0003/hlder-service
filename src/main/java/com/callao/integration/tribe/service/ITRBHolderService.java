package com.callao.integration.tribe.service;

import com.callao.integration.model.Holder;
import com.callao.integration.model.Response;

/**
 * @author chetan d
 * @since 28 Aug 2022
 * @desc Interface.
 */
public interface ITRBHolderService {
	public abstract Response postHolder(Holder _holder);
	public abstract Response getHolder(Long id);
}

package com.elcallao.emit.service;

import com.elcallao.emit.model.Holder;
import com.elcallao.emit.model.Response;

/**
 * @author chetan d
 * @since 28 Aug 2022
 * @desc Interface.
 */
public interface ITRBHolderService {
	public abstract Response postHolder(Holder _holder);
	public abstract Response getHolder(Long holderId);
	public abstract Response getHolderAndAccounts(Long holderId);

}

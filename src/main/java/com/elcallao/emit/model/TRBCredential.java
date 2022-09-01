package com.elcallao.emit.model;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chetan d
 * @since 28 Aug 2022
 * @desc This class is hold the TRB Credential.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TRBCredential implements Serializable {

	private static final long serialVersionUID = 1L;
	private String access_key;
	private String access_api_id;
	private String access_api_key;
	private String api_version;
	
}

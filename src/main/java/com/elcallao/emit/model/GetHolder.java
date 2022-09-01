package com.elcallao.emit.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter@Setter
@NoArgsConstructor
public class GetHolder extends TRBCredential implements Serializable {
    private static final long serialVersionUID = 1L;

    private String action;
    private String request_reference_id;
    private Integer access_level;
    private Long holder_id;
    private Long card_id;

    public GetHolder(String access_key, String access_api_id, String access_api_key, String api_version) {
        super(access_key, access_api_id, access_api_key, api_version);
    }
}

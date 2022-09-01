package com.elcallao.emit.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "holderAccountsBuilder")
public class GetHolderAndAccounts extends TRBCredential implements Serializable {

    private String action;
    private String request_reference_id;
    private Integer access_level;
    @NotNull
    private Long holder_id;

    public GetHolderAndAccounts(String access_key, String access_api_id, String access_api_key, String api_version) {
        super(access_key, access_api_id, access_api_key, api_version);
    }
}

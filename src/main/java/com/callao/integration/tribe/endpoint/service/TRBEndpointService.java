package com.callao.integration.tribe.endpoint.service;

import com.callao.integration.constant.Constants;
import com.callao.integration.model.GetHolder;
import com.callao.integration.model.GetHolderAndAccounts;
import com.callao.integration.model.Holder;
import com.callao.integration.model.TRBCredential;
import com.callao.integration.util.AES_Util;
/**
 * @author chetan d
 * @since 28 Aug 2022
 * @desc this class provide service to Tribe Endpoint
 */
public abstract class TRBEndpointService {

    /**
     * @desc this method will use to build req for create holder.
     */
    public static Holder buildCreateHolderRequest(Holder _holder , String _action) {
        TRBCredential credential = getTRBCredential();
        _holder.setAccess_key(credential.getAccess_key());
        _holder.setAccess_api_key(credential.getAccess_api_key());
        _holder.setAccess_api_id(credential.getAccess_api_id());
        _holder.setApi_version(credential.getApi_version());
        _holder.setAction(_action);
        return _holder;
    }
    /**
     * @desc this method will use to build req for Get holder.
     */
    public static GetHolder buildGetHolderRequest(Long id , String _action) {
        TRBCredential _credential = getTRBCredential();
        GetHolder _getHolder = new GetHolder(_credential.getAccess_key(),_credential.getAccess_api_id()
                ,_credential.getAccess_api_key(),_credential.getApi_version());
        _getHolder.setAccess_key(_credential.getAccess_key());
        _getHolder.setAccess_api_key(_credential.getAccess_api_key());
        _getHolder.setAccess_api_id(_credential.getAccess_api_id());
        _getHolder.setApi_version(_credential.getApi_version());
        _getHolder.setAction(_action);
        _getHolder.setHolder_id(id);
        return _getHolder;
    }
    /**
     * @desc this method will use to build req for GetHolderAndAccounts.
     */
    public static  GetHolderAndAccounts buildGetHolderAndAccountsRequest(String _action) {
        TRBCredential _credential = getTRBCredential();
        GetHolderAndAccounts _getHolderAndAccounts = new GetHolderAndAccounts(_credential.getAccess_key(),_credential.getAccess_api_id()
                ,_credential.getAccess_api_key(),_credential.getApi_version());
        _getHolderAndAccounts.setAccess_key(_credential.getAccess_key());
        _getHolderAndAccounts.setAccess_api_key(_credential.getAccess_api_key());
        _getHolderAndAccounts.setAccess_api_id(_credential.getAccess_api_id());
        _getHolderAndAccounts.setApi_version(_credential.getApi_version());
        _getHolderAndAccounts.setAction(_action);
        return _getHolderAndAccounts;
    }
    /**
     * @desc  this method returns the Tribe Credential.
     */
    public static TRBCredential getTRBCredential() {
        return TRBCredential.builder()
                .access_key(AES_Util.decrypt(System.getenv(Constants.TRB_ACCESS_KEY)))
                .access_api_id(AES_Util.decrypt(System.getenv(Constants.TRB_ACCESS_API_ID)))
                .access_api_key(AES_Util.decrypt(System.getenv(Constants.TRB_ACCESS_API_KEY)))
                .api_version(System.getenv(Constants.TRB_API_VERSION)).build();
    }

}

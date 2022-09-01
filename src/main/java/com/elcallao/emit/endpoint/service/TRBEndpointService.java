package com.elcallao.emit.endpoint.service;

import com.elcallao.emit.constant.Constants;
import com.elcallao.emit.model.*;
import com.elcallao.emit.service.impl.TRBHolderServiceImpl;
import com.elcallao.emit.util.AES_Util;
import com.elcallao.emit.util.EmitUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author chetan d
 * @since 28 Aug 2022
 * @desc this class provide service to Tribe Endpoint
 */
public abstract class TRBEndpointService {

    Logger log = LoggerFactory.getLogger(TRBEndpointService.class);
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
    public static GetHolder buildGetHolderRequest(Long holderId , String _action) {
        TRBCredential _credential = getTRBCredential();
        GetHolder _getHolder = new GetHolder(_credential.getAccess_key(),_credential.getAccess_api_id()
                ,_credential.getAccess_api_key(),_credential.getApi_version());
        _getHolder.setAccess_key(_credential.getAccess_key());
        _getHolder.setAccess_api_key(_credential.getAccess_api_key());
        _getHolder.setAccess_api_id(_credential.getAccess_api_id());
        _getHolder.setApi_version(_credential.getApi_version());
        _getHolder.setAction(_action);
        _getHolder.setHolder_id(holderId);
        return _getHolder;
    }
    /**
     * @desc this method will use to build req for GetHolderAndAccounts.
     */
    public static  GetHolderAndAccounts buildGetHolderAndAccountsRequest(Long holderId , String _action) {
        TRBCredential _credential = getTRBCredential();
        GetHolderAndAccounts _getHolderAndAccounts = new GetHolderAndAccounts(_credential.getAccess_key(),_credential.getAccess_api_id()
                ,_credential.getAccess_api_key(),_credential.getApi_version());
        _getHolderAndAccounts.setAccess_key(_credential.getAccess_key());
        _getHolderAndAccounts.setAccess_api_key(_credential.getAccess_api_key());
        _getHolderAndAccounts.setAccess_api_id(_credential.getAccess_api_id());
        _getHolderAndAccounts.setApi_version(_credential.getApi_version());
        _getHolderAndAccounts.setAction(_action);
        _getHolderAndAccounts.setHolder_id(holderId);
        return _getHolderAndAccounts;
    }
    /**
     * @desc this method will use to build response of GetHolderAndAccounts for callao.
     */
    public static List<ResHolderAndAccount> buildCloHolderAndAccountsRes(List<LinkedHashMap<String, Object>> data ,List<ResHolderAndAccount> holderAndAccountsList){
        data.stream().forEach(i-> {
            ResHolderAndAccount resHolderAndAccounts  = new ResHolderAndAccount();
            resHolderAndAccounts.setAccount_id(StringUtils.isNotEmpty(i.get("id").toString())?i.get("id").toString():null);
            resHolderAndAccounts.setHolder_id(i.get("holder_id").toString());
            holderAndAccountsList.add(resHolderAndAccounts);
        });
        return holderAndAccountsList;
    }
    public static Object maskCredential(Object obj){
        TRBCredential credential = getTRBCredential();
        return EmitUtil.convertObjectToJsonString(obj).replace(credential.getAccess_key(),Constants.ASTERISK_SYMBOLS)
                .replace(credential.getAccess_api_id(), Constants.ASTERISK_SYMBOLS).replace(credential.getAccess_api_key(),Constants.ASTERISK_SYMBOLS)
                .replace(credential.getApi_version(),Constants.ASTERISK_SYMBOLS);
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

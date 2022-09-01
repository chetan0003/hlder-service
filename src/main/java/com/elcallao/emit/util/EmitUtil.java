package com.elcallao.emit.util;

import com.elcallao.emit.constant.Constants;
import com.elcallao.emit.model.ErrorResponse;
import com.elcallao.emit.model.TRBCredential;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class EmitUtil {

    public static final ObjectMapper mapper = new ObjectMapper();

    public static String convertObjectToJsonString(Object obj)  {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public static Object convertJsonStringToObject(String json) {
        try {
            return mapper.readValue(json, Object.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

      public static Object maskCredential(TRBCredential credential , Object obj){
          return convertObjectToJsonString(obj).replace(credential.getAccess_key(),Constants.ASTERISK_SYMBOLS)
                  .replace(credential.getAccess_api_id(), Constants.ASTERISK_SYMBOLS).replace(credential.getAccess_api_key(),Constants.ASTERISK_SYMBOLS)
                  .replace(credential.getApi_version(),Constants.ASTERISK_SYMBOLS);
    }

    public static String getFieldFromJson(String json ,String filed){
        Object obj= JSONValue.parse(json);
        JSONObject jsonObject = (JSONObject) obj;
        return  (String) jsonObject.get(filed);
    }

}

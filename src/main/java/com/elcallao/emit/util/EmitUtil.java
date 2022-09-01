package com.elcallao.emit.util;

import com.elcallao.emit.constant.Constants;
import com.elcallao.emit.model.TRBCredential;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


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
    
}

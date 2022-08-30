package com.callao.integration.util;

import com.callao.integration.constant.Constants;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class TRBUtil {


    public static String getFieldFromJson(String json ,String filed){
        Object obj= JSONValue.parse(json);
        JSONObject jsonObject = (JSONObject) obj;
        return  (String) jsonObject.get(filed);
    }
}

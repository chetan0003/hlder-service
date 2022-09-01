package com.elcallao.emit.util;

import com.elcallao.emit.model.ErrorResponse;

import java.util.LinkedHashMap;
import java.util.Map;

public class Test {
    String s= "{\"status\":\"error\",\"status_code\":\"460\",\"message\":\"Holder not found\",\"duplicated\":0}";
    void m1(){
        //String res = s.replace("\\\"", "\"");
//        Object obj= JSONValue.parse(s);
//        JSONObject jsonObject = (JSONObject) obj;
//        String mess = (String) jsonObject.get("message");
//        System.out.println(mess);


        Map<String, Object> mapRes = (LinkedHashMap<String, Object>) EmitUtil.convertJsonStringToObject(s);
        Object o =EmitUtil.convertJsonStringToObject(s);
        System.out.println(o);

    }

    public static ErrorResponse buildErrorResponse(String errorRes){
        ErrorResponse errorResponse = new ErrorResponse();
        Map<String, Object> mapRes = (LinkedHashMap<String, Object>) EmitUtil.convertJsonStringToObject(errorRes);
        return ErrorResponse.builder().status(mapRes.get("status").toString()).status_code(mapRes.get("status_code").toString())
                .message(mapRes.get("message").toString()).duplicated(mapRes.get("duplicated").toString()).build();
    }

    public static void main(String[] args) {
        String s= "{\"status\":\"error\",\"status_code\":\"460\",\"message\":\"Holder not found\",\"duplicated\":0}";
        ErrorResponse errorResponse =Test.buildErrorResponse(s);
        System.out.println(errorResponse);

    }
}

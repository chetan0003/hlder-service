package com.callao.integration.util;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Test {
    String s= "{\"status\":\"error\",\"status_code\":\"460\",\"message\":\"Holder not found\",\"duplicated\":0}";
    void m1(){
        //String res = s.replace("\\\"", "\"");
//        Object obj= JSONValue.parse(s);
//        JSONObject jsonObject = (JSONObject) obj;
//        String mess = (String) jsonObject.get("message");
//        System.out.println(mess);

    }

    public static void main(String[] args) {
        Test t = new Test();
        t.m1();
    }
}

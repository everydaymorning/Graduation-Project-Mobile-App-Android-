package com.example.smartproject3.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class WriteRequest extends StringRequest {

    final static private  String URL= "http://zmflrql.cafe24.com/board1.php";

    private Map<String, String> parameters;

    public WriteRequest(String bulletinUser, String bulletinTitle, String bulletinType, String bulletinReg, String bulletinDate, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("bulletinUser", bulletinUser);
        parameters.put("bulletinTitle", bulletinTitle);
        parameters.put("bulletinType", bulletinType);
        parameters.put("bulletinReg", bulletinReg);
        parameters.put("bulletinDate", bulletinDate);
    }
    @Override
    public Map<String, String> getParams(){
        return parameters;
    }

}

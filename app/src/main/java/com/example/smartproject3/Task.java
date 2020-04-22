package com.example.smartproject3;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.EditText;

import com.example.smartproject3.fragment.Menu3Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class Task extends AsyncTask<String, Void, String[]> {

    String clientKey = "";

    private String str, receiveMsg;
    ArrayList<String> list1;
    ArrayList<String> list2;
    ArrayList<String> list3;
    ArrayList<String> list4;
    String[] arr = new String[15];
    HttpURLConnection conn;
    @Override
    protected String[] doInBackground(String... params) {
        URL url = null;
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        list4 = new ArrayList<>();



        try {

            if(params[0] == null){
                url = new URL("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?serviceKey=&numOfRows=10&pageNo=1&stationName=%EC%A2%85%EB%A1%9C%EA%B5%AC&dataTerm=DAILY&ver=1.3&_returnType=json");
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                conn.setRequestProperty("x-waple-authorization", clientKey);
            }else{
                String location = params[0];
                String encode = URLEncoder.encode(location,"UTF-8");
                url = new URL("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?serviceKey=&numOfRows=10&pageNo=1&stationName=" + encode + "&dataTerm=DAILY&ver=1.3&_returnType=json");

                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                conn.setRequestProperty("x-waple-authorization", clientKey);
            }


            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();

                Log.i("receiveMsg : ", receiveMsg);

                try {
                    JSONObject jsonObject = new JSONObject(receiveMsg);
                    JSONArray jarray = jsonObject.getJSONArray("list");

                        HashMap map = new HashMap<>();

                        JSONObject jObject = jarray.getJSONObject(0);


                        String dataTime = jObject.getString("dataTime");
                        String so2value = jObject.getString("so2Value");
                        String coValue = jObject.getString("coValue");
                        String o3Value = jObject.getString("o3Value");
                        String no2Value = jObject.getString("no2Value");
                        String grade = jObject.getString("pm10Grade");
                        String pm10Value = jObject.getString("pm10Value");
                        String pm25Value = jObject.getString("pm25Value");
                        String so2Grade = jObject.getString("so2Grade");
                        String coGrade = jObject.getString("coGrade");
                        String o3Grade = jObject.getString("o3Grade");
                        String no2Grade = jObject.getString("no2Grade");
                        String pm25Grade = jObject.getString("pm25Grade");
                        arr[0] = dataTime;
                        arr[1] = so2value;
                        arr[2] = coValue;
                        arr[3] = o3Value;
                        arr[4] = no2Value;
                        arr[5] = params[0];
                        arr[6] = grade;
                        arr[7] = pm10Value;
                        arr[8] = pm25Value;
                        arr[9] = so2Grade;
                        arr[10] = coGrade;
                        arr[11] = o3Grade;
                        arr[12] = no2Grade;
                        arr[13] = pm25Grade;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                reader.close();
            } else {
                Log.i("통신 결과", conn.getResponseCode() + "에러");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

}

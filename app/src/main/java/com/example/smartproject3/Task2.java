package com.example.smartproject3;

import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartproject3.fragment.Menu1Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Task2 extends AsyncTask<String, Void, Document> {

    private TextView textView;
    String clientKey = "";

    private String str, receiveMsg;
    String[] arr = new String[15];
    HttpURLConnection conn;

    public Task2(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected Document doInBackground(String... params) {
        URL url;
        Document doc = null;

        try {

            url = new URL("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastGrib?serviceKey=2uGyJBgCPweeBAJgGsn66sQhzC1Pxavtb0Km1Jxeql5EN9Abflr41RPz0X%2BO19kbwUKRbhUh1gQX2yvQYG2bIQ%3D%3D&base_date=20190223&base_time=1000&nx=60&ny=127&numOfRows=10&pageNo=1");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();

            doc = db.parse(new InputSource(url.openStream()));

            doc.getDocumentElement().normalize();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }


    @Override
    protected void onPostExecute(Document doc) {

        String s = "";

        NodeList nodeList = doc.getElementsByTagName("item");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            Element fstElmnt = (Element) node;

            NodeList idx = fstElmnt.getElementsByTagName("category");

            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("T1H")) {

                NodeList gugun = fstElmnt.getElementsByTagName("obsrValue");

                s += "obsrValue 온도 = " + gugun.item(0).getChildNodes().item(0).getNodeValue() + "'C \n";

            }




        }
        textView.setText(s);
        super.onPostExecute(doc);
    }
}

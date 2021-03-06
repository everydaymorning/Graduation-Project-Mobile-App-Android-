package com.example.smartproject3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class GetAlImages {

    public static String[] category;
    public static String[] season;
    public static String[] photos;
    public static Bitmap[] bitmaps;

    public static final String JSON_ARRAY="result";

    private String json;
    private JSONArray urls;

    public GetAlImages(String json){
        this.json = json;
        try {
            JSONObject jsonObject = new JSONObject(json);
            urls = jsonObject.getJSONArray(JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Bitmap getImage(JSONObject jo){
        URL url = null;
        Bitmap image = null;
        try {
            url = new URL(jo.getString("photo"));
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void getAllImages() throws JSONException {
        bitmaps = new Bitmap[urls.length()];
        photos = new String[urls.length()];
        category = new String[urls.length()];
        season = new String[urls.length()];

        for(int i=0;i<urls.length();i++){
            category[i] = urls.getJSONObject(i).getString("category");
            season[i] = urls.getJSONObject(i).getString("season");
            photos[i] = urls.getJSONObject(i).getString("photo");
            JSONObject jsonObject = urls.getJSONObject(i);
            bitmaps[i]=getImage(jsonObject);
        }
    }
}

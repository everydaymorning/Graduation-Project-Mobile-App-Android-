package com.example.smartproject3.fragment;

import android.app.ProgressDialog;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.smartproject3.CustomDTO;
import com.example.smartproject3.CustomList;
import com.example.smartproject3.GetAlImages;
import com.example.smartproject3.GetHatImages;
import com.example.smartproject3.MainActivity;
import com.example.smartproject3.Menu2Button.ListActivity;
import com.example.smartproject3.R;
import com.example.smartproject3.adapter.CustomAdapter;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ChildOneFragment extends Fragment {
    String userID;
    public static final String hat = "모자";
    private CustomAdapter adapter;
    private ListView listView;
    private Bitmap[] bitmaps;
    ImageView hatImage;
    public static final String GET_IMAGE_URL="http://zmflrql.cafe24.com/getHatImage.php";

    public GetHatImages getHatImages;

    public static final String BITMAP_ID = "BITMAP_ID";

    public static ChildOneFragment newInstance(){
        Bundle args = new Bundle();

        ChildOneFragment fragment = new ChildOneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.fragment_child_one, container, false);
        hatImage = (ImageView) fv.findViewById(R.id.hatImage);
        if(getActivity() != null && getActivity() instanceof MainActivity) {
            userID = ((MainActivity) getActivity()).getUserID();
        }
        getURLs();
        return fv;
    }

    private void getHatImages(){
        class GetImages extends AsyncTask<String,Void,Bitmap> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getContext(),"Downloading images...","Please wait...",false,false);
            }

            @Override
            protected void onPostExecute(Bitmap b) {
                super.onPostExecute(b);
                loading.dismiss();
                //Toast.makeText(ImageListView.this,"Success",Toast.LENGTH_LONG).show();

            }

            @Override
            protected Bitmap doInBackground(String... params) {
                String add = "http://zmflrql.cafe24.com/getHatImage.php?name=" + userID + "?category=" + hat;
                Log.i("add",add);
                URL url = null;
                Bitmap image = null;
                try {
                    getHatImages.getHatImages();
                }catch(JSONException e){
                    e.printStackTrace();
                }
                return image;
            }
        }
        GetImages getImages = new GetImages();
        getImages.execute();
    }

    private void getURLs() {
        class GetHatURLs extends AsyncTask<String,Void,String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getContext(),"Loading...","Please Wait...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                getHatImages = new GetHatImages(s);
                getHatImages();
            }

            @Override
            protected String doInBackground(String... strings) {
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }
            }
        }
        GetHatURLs gu = new GetHatURLs();
        gu.execute(GET_IMAGE_URL +"?name=" + userID + "?category=" + hat);
    }

}

package com.example.smartproject3.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartproject3.MainActivity;
import com.example.smartproject3.R;
import com.example.smartproject3.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Menu3Fragment extends Fragment {
    private Context context;
    String[] resultText;
    String[] items;
    ImageView gradeImage;
    TextView so2value;
    TextView coValue;
    TextView o3Value;
    TextView no2Value;
    TextView stationName;
    TextView dataTime;

    EditText search;
    Button searchButton;
    String grade;
    String grade2;
    String data;

    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.fragment_menu3, container, false);
        context = container.getContext();
        dataTime = (TextView) fv.findViewById(R.id.dataTime);
        stationName = (TextView) fv.findViewById(R.id.stationName);
        search = (EditText) fv.findViewById(R.id.search);
        so2value = (TextView) fv.findViewById(R.id.so2Value);
        coValue = (TextView) fv.findViewById(R.id.coValue);
        o3Value = (TextView) fv.findViewById(R.id.o3Value);
        no2Value = (TextView) fv.findViewById(R.id.no2Value);
        gradeImage = (ImageView) fv.findViewById(R.id.grade);
        searchButton = (Button) fv.findViewById(R.id.searchButton);


        resultText = new String[7];
        data = search.getText().toString();


        try {

            resultText = new Task().execute(data).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        dataTime.setText(resultText[0]);
        so2value.setText(resultText[1]);
        coValue.setText(resultText[2]);
        o3Value.setText(resultText[3]);
        no2Value.setText(resultText[4]);
        stationName.setText(resultText[5]);
        grade = resultText[6];

        if(grade.equals("1")){
            gradeImage.setImageResource(R.drawable.goood);
        }else if(grade.equals("2")){
            gradeImage.setImageResource(R.drawable.normal);
        }else if(grade.equals("3")){
            gradeImage.setImageResource(R.drawable.baddd);
        }else if(grade.equals("4")){
            gradeImage.setImageResource(R.drawable.verybad);
        }else{
            return null;
        }

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    data = search.getText().toString();
                    Task task = new Task();
                    items = task.execute(data).get();
                    dataTime.setText(items[0]);
                    so2value.setText(items[1]);
                    coValue.setText(items[2]);
                    o3Value.setText(items[3]);
                    no2Value.setText(items[4]);
                    stationName.setText(items[5]);
                    grade2 = items[6];
                    if(grade2.equals("1")){
                        gradeImage.setImageResource(R.drawable.goood);
                    }else if(grade2.equals("2")){
                        gradeImage.setImageResource(R.drawable.normal);
                    }else if(grade2.equals("3")) {
                        gradeImage.setImageResource(R.drawable.baddd);
                    }else if(grade2.equals("4")) {
                        gradeImage.setImageResource(R.drawable.verybad);
                    }else{
                        return;
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });




        return fv;
    }

}

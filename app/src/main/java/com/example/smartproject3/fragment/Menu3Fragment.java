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
    ImageView so2GradeImage;
    ImageView coGradeImage;
    ImageView o3GradeImage;
    ImageView no2GradeImage;
    ImageView pm10ValueImage;
    ImageView pm25ValueImage;
    TextView so2value;
    TextView coValue;
    TextView o3Value;
    TextView no2Value;
    TextView stationName;
    TextView dataTime;
    TextView pm10Value;
    TextView pm25Value;
    EditText search;
    Button searchButton;
    String grade;
    String grade2;
    String so2ValueGrade;
    String coValueGrade;
    String o3ValueGrade;
    String no2ValueGrade;
    String pm25ValueGrade;
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
        pm10Value = (TextView) fv.findViewById(R.id.mise);
        pm25Value = (TextView) fv.findViewById(R.id.chomise);
        so2GradeImage = (ImageView) fv.findViewById(R.id.so2ValueImage);
        coGradeImage = (ImageView) fv.findViewById(R.id.coValueImage);
        o3GradeImage = (ImageView) fv.findViewById(R.id.o3ValueImage);
        no2GradeImage = (ImageView) fv.findViewById(R.id.no2ValueImage);
        pm10ValueImage = (ImageView) fv.findViewById(R.id.pm10ValueImage);
        pm25ValueImage = (ImageView) fv.findViewById(R.id.pm25ValueImage);
        resultText = new String[15];
        data = search.getText().toString();


        try {

            resultText = new Task().execute(data).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        dataTime.setText(resultText[0]);
        so2value.setText(resultText[1] + " ppm");
        coValue.setText(resultText[2] + " ppm");
        o3Value.setText(resultText[3] + " ppm");
        no2Value.setText(resultText[4] + " ppm");
        stationName.setText(resultText[5]);
        grade = resultText[6];
        pm10Value.setText(resultText[7] + " ㎍/㎥");
        pm25Value.setText(resultText[8] + " ㎍/㎥");
        so2ValueGrade = resultText[9];
        coValueGrade = resultText[10];
        o3ValueGrade = resultText[11];
        no2ValueGrade = resultText[12];
        pm25ValueGrade = resultText[13];
        if(grade.equals("1")){
            gradeImage.setImageResource(R.drawable.goood);
            pm10ValueImage.setImageResource(R.drawable.goood);
        }else if(grade.equals("2")){
            gradeImage.setImageResource(R.drawable.normal);
            pm10ValueImage.setImageResource(R.drawable.normal);
        }else if(grade.equals("3")){
            gradeImage.setImageResource(R.drawable.baddd);
            pm10ValueImage.setImageResource(R.drawable.baddd);
        }else if(grade.equals("4")){
            gradeImage.setImageResource(R.drawable.verybad);
            pm10ValueImage.setImageResource(R.drawable.verybad);
        }else{
            return null;
        }

        if(pm25ValueGrade.equals("1")){
            pm25ValueImage.setImageResource(R.drawable.goood);
        }else if(pm25ValueGrade.equals("2")){
            pm25ValueImage.setImageResource(R.drawable.normal);
        }else if(pm25ValueGrade.equals("3")){
            pm25ValueImage.setImageResource(R.drawable.baddd);
        }else if(pm25ValueGrade.equals("4")){
            pm25ValueImage.setImageResource(R.drawable.verybad);
        }else{
            return null;
        }

        if(so2ValueGrade.equals("1")){
            so2GradeImage.setImageResource(R.drawable.goood);
        }else if(so2ValueGrade.equals("2")){
            so2GradeImage.setImageResource(R.drawable.normal);
        }else if(so2ValueGrade.equals("3")){
            so2GradeImage.setImageResource(R.drawable.baddd);
        }else if(so2ValueGrade.equals("4")){
            so2GradeImage.setImageResource(R.drawable.verybad);
        }else{
            return null;
        }

        if(coValueGrade.equals("1")){
            coGradeImage.setImageResource(R.drawable.goood);
        }else if(coValueGrade.equals("2")){
            coGradeImage.setImageResource(R.drawable.normal);
        }else if(coValueGrade.equals("3")){
            coGradeImage.setImageResource(R.drawable.baddd);
        }else if(coValueGrade.equals("4")){
            coGradeImage.setImageResource(R.drawable.verybad);
        }else{
            return null;
        }

        if(o3ValueGrade.equals("1")){
            o3GradeImage.setImageResource(R.drawable.goood);
        }else if(o3ValueGrade.equals("2")){
            o3GradeImage.setImageResource(R.drawable.normal);
        }else if(o3ValueGrade.equals("3")){
            o3GradeImage.setImageResource(R.drawable.baddd);
        }else if(o3ValueGrade.equals("4")){
            o3GradeImage.setImageResource(R.drawable.verybad);
        }else{
            return null;
        }


        if(no2ValueGrade.equals("1")){
            no2GradeImage.setImageResource(R.drawable.goood);
        }else if(no2ValueGrade.equals("2")){
            no2GradeImage.setImageResource(R.drawable.normal);
        }else if(no2ValueGrade.equals("3")){
            no2GradeImage.setImageResource(R.drawable.baddd);
        }else if(no2ValueGrade.equals("4")){
            no2GradeImage.setImageResource(R.drawable.verybad);
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
                    so2value.setText(items[1] + " ppm");
                    coValue.setText(items[2] + " ppm");
                    o3Value.setText(items[3] + " ppm");
                    no2Value.setText(items[4] + " ppm");
                    stationName.setText(items[5]);
                    grade = items[6];
                    pm10Value.setText(items[7] + " ㎍/㎥");
                    pm25Value.setText(items[8] + " ㎍/㎥");
                    so2ValueGrade = items[9];
                    coValueGrade = items[10];
                    o3ValueGrade = items[11];
                    no2ValueGrade = items[12];
                    pm25ValueGrade = items[13];
                    if(grade.equals("1")){
                        gradeImage.setImageResource(R.drawable.goood);
                        pm10ValueImage.setImageResource(R.drawable.goood);
                    }else if(grade.equals("2")){
                        gradeImage.setImageResource(R.drawable.normal);
                        pm10ValueImage.setImageResource(R.drawable.normal);
                    }else if(grade.equals("3")) {
                        gradeImage.setImageResource(R.drawable.baddd);
                        pm10ValueImage.setImageResource(R.drawable.baddd);
                    }else if(grade.equals("4")) {
                        gradeImage.setImageResource(R.drawable.verybad);
                        pm10ValueImage.setImageResource(R.drawable.verybad);
                    }else{
                        return;
                    }

                    if(pm25ValueGrade.equals("1")){
                        pm25ValueImage.setImageResource(R.drawable.goood);
                    }else if(pm25ValueGrade.equals("2")){
                        pm25ValueImage.setImageResource(R.drawable.normal);
                    }else if(pm25ValueGrade.equals("3")){
                        pm25ValueImage.setImageResource(R.drawable.baddd);
                    }else if(pm25ValueGrade.equals("4")){
                        pm25ValueImage.setImageResource(R.drawable.verybad);
                    }else{
                        return ;
                    }

                    if(so2ValueGrade.equals("1")){
                        so2GradeImage.setImageResource(R.drawable.goood);
                    }else if(so2ValueGrade.equals("2")){
                        so2GradeImage.setImageResource(R.drawable.normal);
                    }else if(so2ValueGrade.equals("3")){
                        so2GradeImage.setImageResource(R.drawable.baddd);
                    }else if(so2ValueGrade.equals("4")){
                        so2GradeImage.setImageResource(R.drawable.verybad);
                    }else{
                        return ;
                    }

                    if(coValueGrade.equals("1")){
                        coGradeImage.setImageResource(R.drawable.goood);
                    }else if(coValueGrade.equals("2")){
                        coGradeImage.setImageResource(R.drawable.normal);
                    }else if(coValueGrade.equals("3")){
                        coGradeImage.setImageResource(R.drawable.baddd);
                    }else if(coValueGrade.equals("4")){
                        coGradeImage.setImageResource(R.drawable.verybad);
                    }else{
                        return ;
                    }

                    if(o3ValueGrade.equals("1")){
                        o3GradeImage.setImageResource(R.drawable.goood);
                    }else if(o3ValueGrade.equals("2")){
                        o3GradeImage.setImageResource(R.drawable.normal);
                    }else if(o3ValueGrade.equals("3")){
                        o3GradeImage.setImageResource(R.drawable.baddd);
                    }else if(o3ValueGrade.equals("4")){
                        o3GradeImage.setImageResource(R.drawable.verybad);
                    }else{
                        return ;
                    }


                    if(no2ValueGrade.equals("1")){
                        no2GradeImage.setImageResource(R.drawable.goood);
                    }else if(no2ValueGrade.equals("2")){
                        no2GradeImage.setImageResource(R.drawable.normal);
                    }else if(no2ValueGrade.equals("3")){
                        no2GradeImage.setImageResource(R.drawable.baddd);
                    }else if(no2ValueGrade.equals("4")){
                        no2GradeImage.setImageResource(R.drawable.verybad);
                    }else{
                        return ;
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });




        return fv;
    }

}

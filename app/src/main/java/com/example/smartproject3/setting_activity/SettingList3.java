package com.example.smartproject3.setting_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.smartproject3.R;

public class SettingList3 extends AppCompatActivity {
    Button registerButton;
    Button findButton;
    Button dustButton;
    Button oneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_list3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        registerButton = (Button) findViewById(R.id.registerButton);
        findButton = (Button) findViewById(R.id.findButton);
        dustButton = (Button) findViewById(R.id.dustButton);
        oneButton = (Button) findViewById(R.id.oneButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HelpRegister.class);
                startActivity(intent);
            }
        });

        findButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), FindRegister.class);
                startActivity(intent);
            }
        });

        dustButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), DustRegister.class);
                startActivity(intent);
            }
        });

        oneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), OneRegister.class);
                startActivity(intent);
            }
        });

    }
}


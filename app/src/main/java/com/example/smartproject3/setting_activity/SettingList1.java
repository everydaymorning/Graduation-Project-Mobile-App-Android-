package com.example.smartproject3.setting_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.smartproject3.R;

public class SettingList1 extends AppCompatActivity {
    String userID;
    String userType;
    String userAddress;
    TextView textUser;
    TextView textType;
    TextView textAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_list1);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            userID = bundle.getString("userID");
            userType = bundle.getString("userType");
            userAddress = bundle.getString("userAddress");
        }

        textUser = (TextView) findViewById(R.id.userID);
        textType = (TextView) findViewById(R.id.userType);
        textAddress = (TextView) findViewById(R.id.userAddress);

        textUser.setText(userID);
        textType.setText(userType);
        textAddress.setText(userAddress);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }
}

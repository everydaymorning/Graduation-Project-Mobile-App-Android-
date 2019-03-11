package com.example.smartproject3;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.smartproject3.request.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    final static String TAG = "LoginActivity";
    AlertDialog.Builder builder;
    AlertDialog dialog;
    @Override
    protected void onPause() {
        super.onPause();

        finish();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText loginIdText = (EditText) findViewById(R.id.Id);
        final EditText passwordText = (EditText) findViewById(R.id.psw);

        final Button loginButton = (Button) findViewById(R.id.loginButton);
        final TextView registerButton = (TextView) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 버튼을 클릭했을 때 registerIntent를 통해서 registerActivity를 실행할 수 있음
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userID = loginIdText.getText().toString();
                final String userPassword = passwordText.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                String userID = jsonResponse.getString("userID");
                                String userPassword = jsonResponse.getString("userPassword");
                                String userType = jsonResponse.getString("userType");
                                String userAddress = jsonResponse.getString("userAddress");
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                AlertDialog ad = builder.create();
                                ad.setMessage("로그인에 성공하셨습니다.");
                                ad.show();


                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("userID", userID);
                                intent.putExtra("userPassword", userPassword);
                                intent.putExtra("userType", userType);
                                intent.putExtra("userAddress", userAddress);
                                Log.i("userLogin",userID);
                                startActivity(intent);
                                ad.dismiss();

                            }else{
                                builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("로그인에 실패하셨습니다.")
                                        .setNegativeButton("다시 시도", null)
                                        .create()
                                        .show();

                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}

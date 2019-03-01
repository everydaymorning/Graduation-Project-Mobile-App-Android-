package com.example.smartproject3;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.smartproject3.request.WriteRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteActivity extends AppCompatActivity {
    String type;
    DrawerLayout drawerLayout;
    ImageView backButton;
    EditText board_title;
    TextView writeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        board_title = (EditText) findViewById(R.id.board_title);
        writeButton = (TextView) findViewById(R.id.finish);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);



        backButton = (ImageView) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Spinner type_spinner = (Spinner) findViewById(R.id.type_spin);
        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String content = board_title.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){

                                AlertDialog.Builder builder = new AlertDialog.Builder(WriteActivity.this);
                                builder.setMessage("게시글 등록에 성공하셨습니다.")
                                        .setPositiveButton("확인", null)
                                        .create()
                                        .show();

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(WriteActivity.this);
                                builder.setMessage("게시글 등록에 실패하셨습니다.")
                                        .setNegativeButton("다시 시도", null)
                                        .create()
                                        .show();
                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }

                };
                long now = System.currentTimeMillis();
                Date date2 = new Date(now);
                SimpleDateFormat hour = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String getDate = hour.format(date2);
                WriteRequest writeRequest = new WriteRequest("이해강", content, type, "쌍령동", getDate, responseListener);
                RequestQueue queue = Volley.newRequestQueue(WriteActivity.this);
                queue.add(writeRequest);

            }
        });

    }
}

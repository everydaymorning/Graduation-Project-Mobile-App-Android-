package com.example.smartproject3;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.smartproject3.adapter.CommunityAdapter;
import com.example.smartproject3.fragment.Menu4Fragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    EditText editSearch;
    List<Comm_item> userList;
    final private CommunityAdapter adapter = new CommunityAdapter();
    private ListView listView;
    ArrayList<Comm_item> disList;
    Button searchbutton;
    String sText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchbutton = (Button) findViewById(R.id.searchButton1);
        editSearch = (EditText) findViewById(R.id.searchIcon);
        userList = new ArrayList<Comm_item>();
        listView = (ListView) findViewById(R.id.filterListView);
        disList = new ArrayList<Comm_item>();
        disList.addAll(userList);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clear();
                BackgroundTask1 task = new BackgroundTask1();
                task.execute();
            }
        });

    }

    class BackgroundTask1 extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://zmflrql.cafe24.com/load.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) { // 다음 액티비티로 넘어가게 해줌
            sText = editSearch.getText().toString();
            try{
                Log.i("json msg: ", result);
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                String bulletinUser, bulletinTitle, bulletinType, bulletinReg, bulletinDate;
                for(int i=jsonArray.length()-1; i>=0; i--) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    bulletinUser = object.getString("bulletinUser");
                    bulletinTitle = object.getString("bulletinTitle");
                    bulletinType = object.getString("bulletinType");
                    bulletinReg = object.getString("bulletinReg");
                    bulletinDate = object.getString("bulletinDate");
                    if (bulletinTitle.toLowerCase().contains(sText) || bulletinReg.toLowerCase().contains(sText)) {

                        Comm_item user = new Comm_item(bulletinUser, bulletinTitle, bulletinType, bulletinReg, bulletinDate);
                        user.setBoardUser(bulletinUser);
                        user.setBoardContent(bulletinTitle);
                        user.setBoardType(bulletinType);
                        user.setBoardReg(bulletinReg);
                        user.setBoardDate(bulletinDate);

                        adapter.addItem(user);

                        listView.setAdapter(adapter);
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}

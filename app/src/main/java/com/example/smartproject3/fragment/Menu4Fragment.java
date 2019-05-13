package com.example.smartproject3.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.smartproject3.Comm_item;
import com.example.smartproject3.CustomDTO;
import com.example.smartproject3.MainActivity;
import com.example.smartproject3.R;
import com.example.smartproject3.SettingItem;
import com.example.smartproject3.WriteActivity;
import com.example.smartproject3.adapter.CommunityAdapter;
import com.example.smartproject3.adapter.CustomAdapter;
import com.example.smartproject3.request.WriteRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Menu4Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    List<Comm_item> userList;
    final private CommunityAdapter adapter = new CommunityAdapter();
    private ListView listView;
    FloatingActionButton writeButton;
    SwipeRefreshLayout swipeLayout;
    String userID;
    String userType;
    String userAddress;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(getActivity() != null && getActivity() instanceof MainActivity){
            userID = ((MainActivity)getActivity()).getUserID();
            userType = ((MainActivity)getActivity()).getUserType();
            userAddress = ((MainActivity) getActivity()).getUserAddress();

            Log.i("fragID", userID);
            Log.i("fragType", userType);
            Log.i("fragAddress", userAddress);

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.fragment_menu4, container, false);


        userList = new ArrayList<Comm_item>();
        listView = (ListView) fv.findViewById(R.id.board_listview);


        swipeLayout = (SwipeRefreshLayout) fv.findViewById(R.id.swipeLayout);
        swipeLayout.setOnRefreshListener(this);
        BackgroundTask task = new BackgroundTask();
        task.execute();


        writeButton = (FloatingActionButton) fv.findViewById(R.id.write);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WriteActivity.class);
                intent.putExtra("userID", userID);
                intent.putExtra("userType", userType);
                intent.putExtra("userAddress", userAddress);


                startActivity(intent);

            }
        });




        return fv;
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
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

                    Comm_item user = new Comm_item(bulletinUser, bulletinTitle, bulletinType, bulletinReg, bulletinDate);
                    user.setBoardUser(bulletinUser);
                    user.setBoardContent(bulletinTitle);
                    user.setBoardType(bulletinType);
                    user.setBoardReg(bulletinReg);
                    user.setBoardDate(bulletinDate);

                    adapter.addItem(user);

                    listView.setAdapter(adapter);
                }


            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRefresh() {
        adapter.clear();
        swipeLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                BackgroundTask task = new BackgroundTask();
                task.execute();
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
                swipeLayout.setRefreshing(false);
            }
        }, 1000);

    }
}

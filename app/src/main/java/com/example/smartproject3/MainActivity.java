package com.example.smartproject3;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartproject3.fragment.Menu1Fragment;
import com.example.smartproject3.fragment.Menu2Fragment;
import com.example.smartproject3.fragment.Menu3Fragment;
import com.example.smartproject3.fragment.Menu4Fragment;
import com.example.smartproject3.fragment.Menu5Fragment;
import com.example.smartproject3.setting_activity.SettingList1;
import com.example.smartproject3.setting_activity.SettingList2;
import com.example.smartproject3.setting_activity.SettingList3;
import com.example.smartproject3.setting_activity.SettingList4;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    FragmentTransaction transaction;
    TextView userText;
    String userID;
    String userType;
    String userAddress;
    // FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
    private FragmentManager fragmentManager = getSupportFragmentManager();
    // 4개의 메뉴에 들어갈 Fragment들
    private Menu1Fragment menu1Fragment = new Menu1Fragment();
    private Menu2Fragment menu2Fragment = new Menu2Fragment();
    private Menu3Fragment menu3Fragment = new Menu3Fragment();
    private Menu4Fragment menu4Fragment = new Menu4Fragment();
    private Menu5Fragment menu5Fragment = new Menu5Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final ImageButton button = (ImageButton) findViewById(R.id.calendarButton);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setTitle("SMART WEATHER");
        button.setImageResource(R.drawable.ic_arrow_back_black_24dp);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        drawerLayout.setVisibility(View.VISIBLE);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        // 첫 화면 지정
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, menu1Fragment).commitAllowingStateLoss();


        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.navigation_menu1: {
                        transaction.replace(R.id.frameLayout, menu1Fragment).commitAllowingStateLoss();
                        toolbar.setTitle("SMART WEATHER");
                        button.setImageResource(R.drawable.ic_arrow_back_black_24dp);
                        break;
                    }
                    case R.id.navigation_menu2: {
                        transaction.replace(R.id.frameLayout, menu2Fragment).commitAllowingStateLoss();
                        toolbar.setTitle("오늘 정보");
                        button.setImageResource(R.drawable.ic_arrow_back_black_24dp);
                        break;
                    }
                    case R.id.navigation_menu3: {
                        transaction.replace(R.id.frameLayout, menu3Fragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_menu4: {
                        transaction.replace(R.id.frameLayout, menu4Fragment).commitAllowingStateLoss();
                        toolbar.setTitle("날씨 정보 공유 게시판");
                        button.setImageResource(R.drawable.ic_search_black_24dp);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                                startActivity(intent);
                            }
                        });
                        break;
                    }
                    case R.id.navigation_menu5: {
                        transaction.replace(R.id.frameLayout, menu5Fragment).commitAllowingStateLoss();
                        toolbar.setTitle("설정");
                        button.setImageResource(R.drawable.ic_arrow_back_black_24dp);
                    }
                }

                return true;
            }
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.main_nav);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            userID = bundle.getString("userID");
            userType = bundle.getString("userType");
            userAddress = bundle.getString("userAddress");
        }

        View v = navigationView.getHeaderView(0);
        userText = (TextView) v.findViewById(R.id.userText);
        userText.setText(userID);

        Fragment fragment = new Menu4Fragment();
        Bundle fragBundle = new Bundle();
        bundle.putString("userID", userID);
        bundle.putString("userType", userType);
        bundle.putString("userAddress", userAddress);
        fragment.setArguments(fragBundle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                int id = menuItem.getItemId();


                if (id == R.id.version) {
                    Intent loginIntent = new Intent(getApplicationContext(), SettingList1.class);
                    startActivity(loginIntent);
                }
                if (id == R.id.alarm_setting) {
                    Intent loginIntent = new Intent(getApplicationContext(), SettingList2.class);
                    startActivity(loginIntent);
                }
                if (id == R.id.evaluation) {
                    Intent loginIntent = new Intent(getApplicationContext(), SettingList3.class);
                    startActivity(loginIntent);
                }
                if (id == R.id.help) {
                    Intent loginIntent = new Intent(getApplicationContext(), SettingList4.class);
                    startActivity(loginIntent);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        alarm_on();
    }


    public String getUserID() {
        return userID;
    }

    public String getUserType() {
        return userType;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void alarm_on(){
        // 알람 등록하기
        Log.i("alarm", "setAlarm");
        AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(MainActivity.this, AlarmReceive.class);   //AlarmReceive.class이클레스는 따로 만들꺼임 알람이 발동될때 동작하는 클레이스임

        PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        //알람시간 calendar에 set해주기

        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 8,50);//시간을 10시 01분으로 일단 set했음
        calendar.set(Calendar.SECOND, 0);

        //알람 예약
        //am.set(AlarmManager.RTC, calendar.getTimeInMillis(), sender);//이건 한번 알람
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24*60*60*1000, sender);//이건 여러번 알람 24*60*60*1000 이건 하루에한번 계속 알람한다는 뜻.
        Toast.makeText(MainActivity.this,"시간설정:"+ Integer.toString(calendar.get(calendar.HOUR_OF_DAY))+":"+Integer.toString(calendar.get(calendar.MINUTE)),Toast.LENGTH_LONG).show();
    }
}

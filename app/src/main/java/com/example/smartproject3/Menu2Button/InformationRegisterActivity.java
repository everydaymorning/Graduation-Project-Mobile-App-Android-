package com.example.smartproject3.Menu2Button;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartproject3.GPSInfo;
import com.example.smartproject3.GeoVariable;
import com.example.smartproject3.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InformationRegisterActivity extends AppCompatActivity {
    TextView test1;
    TextView test2;

    GeoVariable geovariable = new GeoVariable();  // 클래스 변수 사용 위해
    Geocoder geocoder; // 역지오코딩 하기 위해
    double latitude, longitube; // 위도, 경도 전역변수

    private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;

    private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;

    private boolean isAccessFineLocation = false;

    private boolean isAccessCoarseLocation = false;

    private boolean isPermission = false;


    private GPSInfo gps;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informationregister);

        test1 = (TextView) findViewById(R.id.test1);
        test2 = (TextView) findViewById(R.id.test2);

        latitude = geovariable.getLatitude(); // 위도 경도 클래스변수에서 가져옴
        longitube = geovariable.getLongitube();

        geocoder = new Geocoder(this);


        Button btn_gps = findViewById(R.id.gps);

        btn_gps.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                if(!isPermission){



                    return;

                }


                gps = new GPSInfo(getApplicationContext());


                // GPS 사용유무 가져오기

                if (gps.isGetLocation()) {


                    //GPSInfo를 통해 알아낸 위도값과 경도값

                    double latitude = gps.getLatitude();

                    double longitude = gps.getLongitude();


                    //Geocoder

                    Geocoder gCoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                    List<Address> addr = null;

                    try{

                        addr = gCoder.getFromLocation(latitude,longitude,5);

                        Address a = addr.get(0);
                        String add = a.getThoroughfare();
                        test1.setText(add);
                        for (int i=0;i <= a.getMaxAddressLineIndex();i++) {

                            //여기서 변환된 주소 확인할  수 있음

                            Log.v("알림", "AddressLine(" + i + ")" + a.getLocality() + "\n");

                        }


                    } catch (IOException e){

                        e.printStackTrace();

                    }

                    if (addr != null) {

                        if (addr.size()==0) {

                            Toast.makeText(getApplicationContext(),"주소정보 없음", Toast.LENGTH_LONG).show();

                        }

                    }

                } else {

                    // GPS 를 사용할수 없으므로

                    gps.showSettingsAlert();

                }

            }

        });
        callPermission();



    }


    @Override

    public void onRequestPermissionsResult(int requestCode, String[] permissions,

                                           int[] grantResults) {

        if (requestCode == PERMISSIONS_ACCESS_FINE_LOCATION

                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


            isAccessFineLocation = true;


        } else if (requestCode == PERMISSIONS_ACCESS_COARSE_LOCATION

                && grantResults[0] == PackageManager.PERMISSION_GRANTED){


            isAccessCoarseLocation = true;

        }


        if (isAccessFineLocation && isAccessCoarseLocation) {

            isPermission = true;

        }

    }


    private void callPermission() {

        // Check the SDK version and whether the permission is already granted or not.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

                && ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)

                != PackageManager.PERMISSION_GRANTED) {



            requestPermissions(

                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},

                    PERMISSIONS_ACCESS_FINE_LOCATION);


        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

                && ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION)

                != PackageManager.PERMISSION_GRANTED){


            requestPermissions(

                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},

                    PERMISSIONS_ACCESS_COARSE_LOCATION);

        } else {

            isPermission = true;

        }

    }

}

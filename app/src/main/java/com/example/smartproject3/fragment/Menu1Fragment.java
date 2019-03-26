package com.example.smartproject3.fragment;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartproject3.GPSInfo;
import com.example.smartproject3.GeoVariable;
import com.example.smartproject3.R;
import com.example.smartproject3.Task2;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Menu1Fragment extends Fragment {
    private LineChart lineChart;
    private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;

    private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;

    private boolean isAccessFineLocation = false;

    private boolean isAccessCoarseLocation = false;

    private boolean isPermission = false;

    GeoVariable geovariable = new GeoVariable();  // 클래스 변수 사용 위해
    Geocoder geocoder; // 역지오코딩 하기 위해
    Double latitude, longitube; // 위도, 경도 전역변수
    public static int TO_GRID = 0;
    public static int TO_GPS = 1;
    TextView temp;
    TextView reh;

    private GPSInfo gps;
    String[] cut;
    TextView curLct;

    SimpleDateFormat formatter
            = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
    Date date = new Date();
    String weather_date = formatter.format(date);
    long now = System.currentTimeMillis();
    Date date2 = new Date(now);
    SimpleDateFormat hour = new SimpleDateFormat("HH");
    String getTime = hour.format(date2);
    Integer hour_1 = Integer.parseInt(getTime) - 1;


    String strHour_1 = hour_1.toString();

    String strHour = strHour_1 + "00";

    String weather_url = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastGrib?serviceKey=2uGyJBgCPweeBAJgGsn66sQhzC1Pxavtb0Km1Jxeql5EN9Abflr41RPz0X%2BO19kbwUKRbhUh1gQX2yvQYG2bIQ%3D%3D&base_date=" + weather_date + "&base_time=" + strHour + "&nx=60&ny=127&numOfRows=10&pageNo=1";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu1, container, false);

        cut = new String[5];
        curLct = (TextView) v.findViewById(R.id.curLct);
        gps = new GPSInfo(getContext());
        // GPS 사용유무 가져오기
        if (gps.isGetLocation()) {
            //GPSInfo를 통해 알아낸 위도값과 경도값
            latitude = gps.getLatitude();
            longitube = gps.getLongitude();
            //Geocoder
            Geocoder gCoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addr = null;
            try {
                addr = gCoder.getFromLocation(latitude, longitube, 5);
                Address a = addr.get(0);
                for (int i = 0; i <= a.getMaxAddressLineIndex(); i++) {
                    //여기서 변환된 주소 확인할  수 있음
                    Log.v("알림", "AddressLine(" + i + ")" + a.getAddressLine(i) + "\n");
                    cut = a.getAddressLine(0).split(" ");
                    curLct.setText(cut[4]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (addr != null) {
                if (addr.size() == 0) {
                    Toast.makeText(getContext(), "주소정보 없음", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            // GPS 를 사용할수 없으므로
            gps.showSettingsAlert();
        }

        callPermission();  // 권한 요청을 해야 함

        LatXLngY tmp = convertGRID_GPS(TO_GRID, latitude, longitube);

        if(hour_1 < 10){
            strHour = "0" + strHour;
        }
        weather_url = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastGrib?serviceKey=2uGyJBgCPweeBAJgGsn66sQhzC1Pxavtb0Km1Jxeql5EN9Abflr41RPz0X%2BO19kbwUKRbhUh1gQX2yvQYG2bIQ%3D%3D&base_date=" + weather_date + "&base_time=" + strHour + "&nx=" + tmp.x.intValue() + "&ny=" + tmp.y.intValue() + "&numOfRows=10&pageNo=1";
        Log.i("url",weather_url);
        lineChart = (LineChart) v.findViewById(R.id.chart);

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(10, hour_1 - 3));
        entries.add(new Entry(11, hour_1 - 2));
        entries.add(new Entry(13, hour_1 - 1));
        entries.add(new Entry(14, hour_1));
        entries.add(new Entry(15, hour_1 + 1));


        LineDataSet lineDataSet = new LineDataSet(entries, "온도");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(3);
        lineDataSet.setCircleColor(Color.parseColor("#ffffff"));
        lineDataSet.setCircleColorHole(Color.BLUE);
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.enableGridDashedLine(12, 24, 0);



        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("");

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setDescription(description);
        lineChart.animateY(2000, Easing.EasingOption.EaseInCubic);
        lineChart.invalidate();



        temp = (TextView) v.findViewById(R.id.temp);
        reh = (TextView) v.findViewById(R.id.reh);
        try{
            new Task2().execute();
        }catch(Exception e){
            e.printStackTrace();
        }

        return v;
    }

    class Task2 extends AsyncTask<String, Void, Document> {

        @Override
        protected Document doInBackground(String... params) {
            URL url;

            Document doc = null;

            try {

                url = new URL(weather_url);


                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

                DocumentBuilder db = dbf.newDocumentBuilder();

                doc = db.parse(new InputSource(url.openStream()));





            } catch (Exception e) {
                e.printStackTrace();
            }
            return doc;
        }


        @Override
        protected void onPostExecute(Document doc) {

            String tmp = "";
            String reh_ = "";
            NodeList nodeList = doc.getElementsByTagName("item");


            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                Element fstElmnt = (Element) node;


                NodeList idx = fstElmnt.getElementsByTagName("category");


                if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("T1H"))
                         {

                    NodeList gugun = fstElmnt.getElementsByTagName("obsrValue");


                    tmp += "온도 = " + gugun.item(0).getChildNodes().item(0).getNodeValue() + "'C ";

                }

                if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("REH")) {

                    NodeList gugun = fstElmnt.getElementsByTagName("obsrValue");

                    reh_ += "습도 = " + gugun.item(0).getChildNodes().item(0).getNodeValue() + "% ";

                }



            }
            temp.setText(tmp);
            reh.setText(reh_);

            super.onPostExecute(doc);
        }
    }

    private LatXLngY convertGRID_GPS(int mode, double lat_X, double lng_Y )
    {
        double RE = 6371.00877; // 지구 반경(km)
        double GRID = 5.0; // 격자 간격(km)
        double SLAT1 = 30.0; // 투영 위도1(degree)
        double SLAT2 = 60.0; // 투영 위도2(degree)
        double OLON = 126.0; // 기준점 경도(degree)
        double OLAT = 38.0; // 기준점 위도(degree)
        double XO = 43; // 기준점 X좌표(GRID)
        double YO = 136; // 기1준점 Y좌표(GRID)

        //
        // LCC DFS 좌표변환 ( code : "TO_GRID"(위경도->좌표, lat_X:위도,  lng_Y:경도), "TO_GPS"(좌표->위경도,  lat_X:x, lng_Y:y) )
        //


        double DEGRAD = Math.PI / 180.0;
        double RADDEG = 180.0 / Math.PI;

        double re = RE / GRID;
        double slat1 = SLAT1 * DEGRAD;
        double slat2 = SLAT2 * DEGRAD;
        double olon = OLON * DEGRAD;
        double olat = OLAT * DEGRAD;

        double sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
        double sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
        double ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
        ro = re * sf / Math.pow(ro, sn);
        LatXLngY rs = new LatXLngY();

        if (mode == TO_GRID) {
            rs.lat = lat_X;
            rs.lng = lng_Y;
            double ra = Math.tan(Math.PI * 0.25 + (lat_X) * DEGRAD * 0.5);
            ra = re * sf / Math.pow(ra, sn);
            double theta = lng_Y * DEGRAD - olon;
            if (theta > Math.PI) theta -= 2.0 * Math.PI;
            if (theta < -Math.PI) theta += 2.0 * Math.PI;
            theta *= sn;
            rs.x = Math.floor(ra * Math.sin(theta) + XO + 0.5);
            rs.y = Math.floor(ro - ra * Math.cos(theta) + YO + 0.5);
        }
        else {
            rs.x = lat_X;
            rs.y = lng_Y;
            double xn = lat_X - XO;
            double yn = ro - lng_Y + YO;
            double ra = Math.sqrt(xn * xn + yn * yn);
            if (sn < 0.0) {
                ra = -ra;
            }
            double alat = Math.pow((re * sf / ra), (1.0 / sn));
            alat = 2.0 * Math.atan(alat) - Math.PI * 0.5;

            double theta = 0.0;
            if (Math.abs(xn) <= 0.0) {
                theta = 0.0;
            }
            else {
                if (Math.abs(yn) <= 0.0) {
                    theta = Math.PI * 0.5;
                    if (xn < 0.0) {
                        theta = -theta;
                    }
                }
                else theta = Math.atan2(xn, yn);
            }
            double alon = theta / sn + olon;
            rs.lat = alat * RADDEG;
            rs.lng = alon * RADDEG;
        }
        return rs;
    }

    class LatXLngY
    {
        public Double lat;
        public Double lng;

        public Double x;
        public Double y;

    }

    private void callPermission() {

        // Check the SDK version and whether the permission is already granted or not.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

                && ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)

                != PackageManager.PERMISSION_GRANTED) {



            requestPermissions(

                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},

                    PERMISSIONS_ACCESS_FINE_LOCATION);


        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

                && ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION)

                != PackageManager.PERMISSION_GRANTED){


            requestPermissions(

                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},

                    PERMISSIONS_ACCESS_COARSE_LOCATION);

        } else {

            isPermission = true;

        }

    }
}

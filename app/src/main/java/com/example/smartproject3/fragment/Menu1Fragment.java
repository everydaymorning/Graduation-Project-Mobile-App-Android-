package com.example.smartproject3.fragment;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    TextView temp;
    TextView reh;
    TextView test;
    SimpleDateFormat formatter
            = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
    Date date = new Date();
    String weather_date = formatter.format(date);
    long now = System.currentTimeMillis();
    Date date2 = new Date(now);
    SimpleDateFormat hour = new SimpleDateFormat("HH");
    String getTime = hour.format(date2);
    Integer hour_1 = Integer.parseInt(getTime);


    String strHour_1 = hour_1.toString();

    String strHour = strHour_1 + "00";

    String weather_url = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastGrib?serviceKey=2uGyJBgCPweeBAJgGsn66sQhzC1Pxavtb0Km1Jxeql5EN9Abflr41RPz0X%2BO19kbwUKRbhUh1gQX2yvQYG2bIQ%3D%3D&base_date=" + weather_date + "&base_time=" + strHour + "&nx=60&ny=127&numOfRows=10&pageNo=1";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu1, container, false);


        lineChart = (LineChart) v.findViewById(R.id.chart);

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(12, 10));
        entries.add(new Entry(13, 12));
        entries.add(new Entry(14, 14));
        entries.add(new Entry(15, 15));
        entries.add(new Entry(16, 15));


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
        test = (TextView) v.findViewById(R.id.hour);
        test.setText(strHour);
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
}

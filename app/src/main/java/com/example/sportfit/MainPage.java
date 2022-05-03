package com.example.sportfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainPage extends AppCompatActivity {
    TextView tv_belki, tv_zhiri,tv_uglevody ;
    Button button;
    AnyChartView anyChartView;
    String[] names;
    int[] values;
    ImageView btn_calc,iv_exit;
    ImageButton btn_home;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        DataBaseHelper db = new DataBaseHelper(this);
        MainMenuCalculator calculator = new MainMenuCalculator(db.getEveryUser().get(0));
        tv_belki = findViewById(R.id.tv_belki);
        tv_zhiri = findViewById(R.id.tv_zhiri);
        anyChartView = findViewById(R.id.chart_viev);
        tv_uglevody = findViewById(R.id.tv_uglevody);
        btn_calc = findViewById(R.id.btn_calc);
        button = findViewById(R.id.button);
        iv_exit = findViewById(R.id.iv_exit);
        btn_home = findViewById(R.id.btn_home);

        names = new String[]{"Белки", "Жиры", "Углеводы"};
        values = new int[]{calculator.getRbelki(), calculator.getRzhiri(), calculator.getRuglev()};

        button.setText(String.valueOf(calculator.getCalories())+ " Ккал");
        tv_belki.setText(String.valueOf(calculator.getRbelki()/4)+" г");
        tv_zhiri.setText(String.valueOf(calculator.getRzhiri()/9)+" г");
        tv_uglevody.setText(String.valueOf(calculator.getRuglev()/4)+" г");

        setupChart();

        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this, Calculator.class);
                startActivity(intent);

            }
        });

        iv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.Exit(2);
                Intent intent = new Intent(MainPage.this, Start.class);
                startActivity(intent);
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainPage.this, db.getEveryUser().get(0).toString(), Toast.LENGTH_SHORT).show();
            }
        });




        //tv_info.setText(calculator.toString()+ "\n  "+ calculator.caloriesCalculator());
    }
    public void setupChart(){
        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();
        for (int i=0 ; i< names.length; i++){
            dataEntries.add(new ValueDataEntry(names[i],values[i]));

        }
        pie.data(dataEntries);
        pie.palette(new String[]{"#FBC968", "#B7DF76", "#FB9468"});
        anyChartView.setChart(pie);


    }
}
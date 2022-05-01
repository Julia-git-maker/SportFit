package com.example.sportfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    Button btn_female_main, btn_male_main;
    ProgressBar pb_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_female_main = findViewById(R.id.btn_female_main);
        btn_male_main = findViewById(R.id.btn_male_main);
        pb_main = findViewById(R.id.pb_main);
        pb_main.setProgress(17);


        btn_male_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, age.class);
                intent.putExtra("sex","male");
                User.totalsex = "male";
                startActivity(intent);

            }
        });

        btn_female_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, age.class);
                User.totalsex = "female";
                startActivity(intent);


            }
        });
    }


}
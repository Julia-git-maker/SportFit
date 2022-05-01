package com.example.sportfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Life extends AppCompatActivity {
    ProgressBar pb_Life;
    ImageView iv_arrow_Life;
    Button btn_mid_active, btn_active_life, btn_low_life,btn_sit_life;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);

        iv_arrow_Life = findViewById(R.id.iv_arrow_life);
        pb_Life = findViewById(R.id.pb_life);
        pb_Life.setProgress(84);

        btn_mid_active = findViewById(R.id.btn_mid_active);
        btn_active_life = findViewById(R.id.btn_active_life);
        btn_low_life = findViewById(R.id.btn_low_life);
        btn_sit_life = findViewById(R.id.btn_sit_life);

        iv_arrow_Life.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Life.this, height.class);
                startActivity(intent);

            }
        });

        btn_mid_active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getdata(1.4);
            }
        });

        btn_active_life.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getdata(1.5);
            }
        });

        btn_low_life.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getdata(1.3);
            }
        });

        btn_sit_life.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getdata(1.2);
            }
        });
    }

    public void getdata(double buton){
        Intent intent = new Intent(Life.this, Target.class);
        User.totallifestyle = buton;
        startActivity(intent);

    }
}
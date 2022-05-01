package com.example.sportfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class age extends AppCompatActivity {
    EditText et_Age;
    ProgressBar pb_age;
    ImageView iv_arrow_age;
    Button btn_next_age;
    Tester tester = new Tester();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);
        et_Age = findViewById(R.id.et_Age);
        iv_arrow_age = findViewById(R.id.iv_arrow_age);
        pb_age = findViewById(R.id.pb_age);
        btn_next_age = findViewById(R.id.btn_next_age);
        pb_age.setProgress(34);

        iv_arrow_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(age.this, MainActivity.class);
                startActivity(intent);

            }
        });

        btn_next_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!tester.getData(et_Age)){
                    Toast.makeText(age.this, "Введите данные!", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(age.this, Weight.class);
                    User.totalage = Double.parseDouble(et_Age.getText().toString());
                    startActivity(intent);
                }

            }
        });
    }




}
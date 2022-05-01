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

public class height extends AppCompatActivity {

    EditText et_height;
    ProgressBar pb_height;
    ImageView iv_arrow_height;
    Button btn_next_height;
    Tester tester = new Tester();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);

        et_height = findViewById(R.id.et_height);
        iv_arrow_height = findViewById(R.id.iv_arrow_height);
        pb_height = findViewById(R.id.pb_height);
        btn_next_height = findViewById(R.id.btn_next_height);
        pb_height.setProgress(67);

        iv_arrow_height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(height.this, Weight.class);
                startActivity(intent);

            }
        });

        btn_next_height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tester.getData(et_height)){
                    Toast.makeText(height.this, "Введите данные!", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(height.this, Life.class);
                    User.totalheight = Double.parseDouble(et_height.getText().toString());
                    startActivity(intent);

                }
            }
        });
    }

}
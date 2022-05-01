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

public class Weight extends AppCompatActivity {

    EditText et_Weight;
    ProgressBar pb_Weight;
    ImageView iv_arrow_Weight;
    Button btn_next_Weight;
    Tester tester = new Tester();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        et_Weight = findViewById(R.id.et_Weight);
        iv_arrow_Weight = findViewById(R.id.iv_arrow_weight);
        pb_Weight = findViewById(R.id.pb_weight);
        btn_next_Weight = findViewById(R.id.btn_next_weight);
        pb_Weight.setProgress(50);

        iv_arrow_Weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Weight.this, age.class);
                startActivity(intent);
            }
        });

        btn_next_Weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tester.getData(et_Weight)){
                    Toast.makeText(Weight.this, "Введите данные!", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(Weight.this, height.class);
                    User.totalweight = Double.parseDouble(et_Weight.getText().toString());
                    startActivity(intent);
                }

            }
        });
    }
}

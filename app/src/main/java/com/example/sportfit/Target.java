package com.example.sportfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Target extends AppCompatActivity {

    ProgressBar pb_target;
    ImageView iv_arrow_target;
    Button btn_lose,btn_buff,btn_keep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
        iv_arrow_target = findViewById(R.id.iv_arrow_target);
        pb_target = findViewById(R.id.pb_target);
        btn_lose = findViewById(R.id.btn_lose);
        btn_buff = findViewById(R.id.btn_buff);
        btn_keep = findViewById(R.id.btn_keep);
        pb_target.setProgress(100);


        iv_arrow_target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Target.this, Life.class);
                startActivity(intent);
            }
        });

        btn_buff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getdata("buff");
            }
        });

        btn_lose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getdata("lose");
            }
        });

        btn_keep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getdata("keep");
            }
        });
    }
    public void getdata(String buton){
        DataBaseHelper db = new DataBaseHelper(this);
        Intent intent = new Intent(Target.this, MainPage.class);
        User.totaltarget = buton;
        User user = new User(User.totalsex,User.totalage,User.totalheight,User.totalweight,User.totallifestyle,User.totaltarget);
        db.addOneToUser(user);
        startActivity(intent);
    }
}
package com.example.sportfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Start extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        DataBaseHelper dataBaseHelper = new DataBaseHelper(Start.this);
//        user = new User("male",20,160,55,1.2,"lose");
//        Food food = new Food(2, "Банан",100,1.66, 0.23, 23.99,104.51);
        if (dataBaseHelper.getEveryUser().toString() == "[]"){
            Intent intent = new Intent(Start.this,MainActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(Start.this,MainPage.class);
            startActivity(intent);
        }








    }
}
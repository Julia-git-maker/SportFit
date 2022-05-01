package com.example.sportfit;

import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseHelper {
    private DatabaseReference db;
    private String Food_key = "Food";


    public void addOneFood(Food food){
        db = FirebaseDatabase.getInstance().getReference(Food_key);
        db.push().setValue(food);
    }



}

package com.example.sportfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddToList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    EditText et_weight_add;
    ImageView iv_arrow_add;
    private DatabaseReference db;
    DataBaseHelper dataBaseHelper;
    RecyclerView rv_fireFood;
    private DatabaseReference myDataBase;
    List<Food> foodList = new ArrayList<>();
    private String FOOD_KEY = "Food";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_add_to_list);
        rv_fireFood = findViewById(R.id.rv_fireFood);
        iv_arrow_add = findViewById(R.id.iv_arrow_add);
        foodList = new ArrayList<Food>();
        myDataBase = FirebaseDatabase.getInstance().getReference(FOOD_KEY);


        myDataBase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }else {
                    for (DataSnapshot ds:task.getResult().getChildren()){
                        Food food = new Food(1,ds.child("Name").getValue().toString(),100,Double.parseDouble(ds.child("bel").getValue().toString()),Double.parseDouble(ds.child("zhir").getValue().toString()),Double.parseDouble(ds.child("ugl").getValue().toString()),Double.parseDouble(ds.child("cal").getValue().toString()));
                        foodList.add(food);
//                        Toast.makeText(AddToList.this, foodList.toString(), Toast.LENGTH_SHORT).show();
                        layoutManager = new LinearLayoutManager(AddToList.this);
                        rv_fireFood.setLayoutManager(layoutManager);
                        Log.d("Before", foodList.toString());
                        mAdapter = new RecyclerVievAdapter(foodList,AddToList.this);
                        rv_fireFood.setAdapter(mAdapter);
                        Log.d("After", foodList.toString());



                    }
                    Log.d("TAG", foodList.toString());
                }

            }
        });














        iv_arrow_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddToList.this,Calculator.class);
                startActivity(intent);

            }
        });


    }
}

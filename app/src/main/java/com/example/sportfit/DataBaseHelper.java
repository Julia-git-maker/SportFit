package com.example.sportfit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_USER = "TABLEUSER";
    public static final String TABLE_FOOD = "TABLE_FOOD";
    public static final String COLUMN_PRODUCT_ID = "COLUMN_PRODUCT_ID";
    public static final String COLUMN_PRODUCT_NAME = "COLUMN_PRODUCT_NAME";
    public static final String USER_WEIGHT = "WEIGHT";
    public static final String COLUMN_PRODUCT_WEIGHT = "COLUMN_PRODUCT_" + USER_WEIGHT;
    public static final String COLUMN_PRODUCT_BELKI = "COLUMN_PRODUCT_BELKI";
    public static final String COLUMN_PRODUCT_ZHIRI = "COLUMN_PRODUCT_ZHIRI";
    public static final String COLUMN_PRODUCT_UGLEV = "COLUMN_PRODUCT_UGLEV";
    public static final String COLUMN_PRODUCT_CALORIES = "COLUMN_PRODUCT_CALORIES";
    public static final String USER_SEX = "USER_SEX";
    public static final String USER_AGE = "AGE";
    public static final String USER_HEIGHT = "HEIGHT";
    public static final String USER_LIFESTYLE = "LIFESTYLE";
    public static final String USER_TARGET = "TARGET";
    public static final String USER_ID = "USER_ID";
    private DatabaseReference myDataBase;
    List<Food> foodList = new ArrayList<>();
    private String FOOD_KEY = "Food";
    String[] strings = new String[6];
    String[] strings2 = new String[6];

    double weighta = 0;
    double belkia = 0;
    double zhiria = 0;
    double ugleva = 0;
    double caloriesa = 0;
    double belkia2 = 0;
    double zhiria2 = 0;
    double ugleva2 = 0;
    double caloriesa2 = 0;
    public DataBaseHelper(@Nullable Context context) { super(context, "Sportfit.db", null, 1); }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUserTable = "CREATE TABLE " + TABLE_USER + " (" + USER_ID + " INTEGER PRIMARY KEY, " + USER_SEX + " TEXT, " + USER_AGE + " INTEGER, " + USER_HEIGHT + " INTEGER, " + USER_WEIGHT + " INTEGER, " + USER_LIFESTYLE + " INTEGER, " + USER_TARGET + " TEXT)";
        sqLiteDatabase.execSQL(createUserTable);
        String createTableStatement = "CREATE TABLE " + TABLE_FOOD + " (" + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PRODUCT_NAME + " TEXT, " + COLUMN_PRODUCT_WEIGHT + " INTEGER, " + COLUMN_PRODUCT_BELKI + " INTEGER, " + COLUMN_PRODUCT_ZHIRI + " INTEGER, " + COLUMN_PRODUCT_UGLEV + " INTEGER, " + COLUMN_PRODUCT_CALORIES + " INTEGER)";
        sqLiteDatabase.execSQL(createTableStatement);
        strings[0] = "Итого: ";
        strings[1] = "0";
        strings[2] = "0";
        strings[3] = "0";
        strings[4] = "0";
        strings[5] = "0";
        strings2[0] = "Итого на \n 100 гр. ";
        strings2[1] = "0";
        strings2[2] = "0";
        strings2[3] = "0";
        strings2[4] = "0";
        strings2[5] = "0";
    }


    public List<Food> getFoodForRecycler(){
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

                    }
                }

            }
        });
        return foodList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOneToFood(Food food){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PRODUCT_NAME,food.getName());
        cv.put(COLUMN_PRODUCT_WEIGHT,food.getWeight());
        cv.put(COLUMN_PRODUCT_BELKI,food.getBel());
        cv.put(COLUMN_PRODUCT_ZHIRI,food.getZhiri());
        cv.put(COLUMN_PRODUCT_UGLEV,food.getUgl());
        cv.put(COLUMN_PRODUCT_CALORIES,food.getCalories());

        long insert = db.insert(TABLE_FOOD, null, cv);
        if (insert == -1){
            return false;
        }
        else {
            return true;
        }

    }

    public boolean addOneToUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_ID,2);
        cv.put(USER_SEX,user.getSex());
        cv.put(USER_AGE,user.getAge());
        cv.put(USER_HEIGHT,user.getHeight());
        cv.put(USER_WEIGHT,user.getWeight());
        cv.put(USER_LIFESTYLE,user.getLifestyle());
        cv.put(USER_TARGET,user.getTarget());

        long insert = db.insert(TABLE_USER, null, cv);
        if (insert == -1){
            return false;
        }
        else {
            return true;
        }

    }

    public boolean Exit ( int index){
        //finds customerModel in the DB. if found and deleted returns true, in not returns false

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TABLE_USER + " WHERE " + USER_ID + " = " +index;

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean ClearTable (){


        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TABLE_FOOD;

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        }
        else {
            return false;
        }
    }

    public List<User> getEveryUser(){
        List<User> returnList = new ArrayList<>();
        int Index = 2;
        String queryString = "SELECT * FROM " + TABLE_USER+ " WHERE " + USER_ID + " = " + Index ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if (cursor.moveToFirst()){
            do {
                String sex = cursor.getString(1);
                double age = cursor.getDouble(2);
                double height = cursor.getDouble(3);
                double weight = cursor.getDouble(4);
                double lifestyle = cursor.getDouble(5);
                String target = cursor.getString(6);


                User newUser = new User(sex,age,height,weight,lifestyle, target);
                returnList.add(newUser);
            } while (cursor.moveToNext());
        }
        else {
            //Something if failed
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public List<String[]> getEveryFood(){
        List<String[]> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLE_FOOD;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                double weight = cursor.getDouble(2);
                double belki = cursor.getDouble(3);
                double zhiri = cursor.getDouble(4);
                double uglev = cursor.getDouble(5);
                double calories = cursor.getDouble(6);

                Food newFood = new Food(id,name,weight,belki, zhiri, uglev,calories);
                returnList.add(newFood.getForTable());
            } while (cursor.moveToNext());
        }
        else {
            //Something if failed
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public String[] getEveryFoodWeight(){
        List<String[]> returnList = new ArrayList<>();


        String queryString = "SELECT * FROM " + TABLE_FOOD;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                double weight = cursor.getDouble(2);
                double belki = cursor.getDouble(3);
                double zhiri = cursor.getDouble(4);
                double uglev = cursor.getDouble(5);
                double calories = cursor.getDouble(6);

                Food newFood = new Food(id,name,weight,belki, zhiri, uglev,calories);
                returnList.add(newFood.getForTable());


            } while (cursor.moveToNext());
        }
        else {
            //Something if failed
        }
        for (int i = 0; i<returnList.size();i++){
            weighta += Double.parseDouble(returnList.get(i)[1]);
            belkia += Double.parseDouble(returnList.get(i)[2]);
            zhiria += Double.parseDouble(returnList.get(i)[3]);
            ugleva += Double.parseDouble(returnList.get(i)[4]);
            caloriesa += Double.parseDouble(returnList.get(i)[5]);
        }
        strings[0] = "Итого: ";
        strings[1] = String.valueOf(weighta);
        strings[2] = String.valueOf(belkia);
        strings[3] = String.valueOf(zhiria);
        strings[4] = String.valueOf(ugleva);
        strings[5] = String.valueOf(caloriesa);


        cursor.close();
        db.close();
        return strings;
    }
    public String[] getEveryFoodWeight100(){
        List<String[]> returnList = new ArrayList<>();


        String queryString = "SELECT * FROM " + TABLE_FOOD;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                double weight = cursor.getDouble(2);
                double belki = cursor.getDouble(3)/weight * 100;
                double zhiri = cursor.getDouble(4)/weight * 100;
                double uglev = cursor.getDouble(5)/weight * 100;
                double calories = cursor.getDouble(6)/weight * 100;

                Food newFood = new Food(id,name,weight,belki, zhiri, uglev,calories);
                returnList.add(newFood.getForTable());


            } while (cursor.moveToNext());
        }
        else {
            //Something if failed
        }
        for (int i = 0; i<returnList.size();i++){
            weighta = 100;
            belkia2 += Double.parseDouble(returnList.get(i)[2]);
            zhiria2 += Double.parseDouble(returnList.get(i)[3]);
            ugleva2 += Double.parseDouble(returnList.get(i)[4]);
            caloriesa2 += Double.parseDouble(returnList.get(i)[5]);
        }
        strings2[0] = "Итого на\n100 гр. ";
        strings2[1] = String.valueOf(weighta);
        strings2[2] = String.valueOf(belkia2);
        strings2[3] = String.valueOf(zhiria2);
        strings2[4] = String.valueOf(ugleva2);
        strings2[5] = String.valueOf(caloriesa2);


        cursor.close();
        db.close();
        return strings2;
    }

    public List<Food> getEveryFoodforAdd(){
        List<Food> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLE_FOOD;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                double weight = cursor.getDouble(2);
                double belki = cursor.getDouble(3);
                double zhiri = cursor.getDouble(4);
                double uglev = cursor.getDouble(5);
                double calories = cursor.getDouble(6);

                Food newFood = new Food(id,name,weight,belki, zhiri, uglev,calories);
                returnList.add(newFood);
            } while (cursor.moveToNext());
        }
        else {
            //Something if failed
        }
        cursor.close();
        db.close();
        return returnList;
    }
}

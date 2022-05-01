package com.example.sportfit;

import android.widget.EditText;

public class Tester {
    public boolean getData(EditText data){
        if (data.getText().toString().equals("")){

            return false;
        }
        else {
            return true;
        }
    }
}

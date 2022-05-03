package com.example.sportfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.SwipeToRefreshListener;
import de.codecrafters.tableview.model.TableColumnDpWidthModel;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class Calculator extends AppCompatActivity {
    ImageView iv_arrow_calc;
    Button btn_add_to_List,btn_clean;
    TextView tv_test;
    TableView<String[]> tv_calc;
    DataBaseHelper dataBaseHelper;
    String[][] columns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        iv_arrow_calc = findViewById(R.id.iv_arrow_calc);
        btn_add_to_List = findViewById(R.id.btn_add_to_List);
        btn_clean = findViewById(R.id.btn_clean);
        tv_calc = (TableView<String[]>)findViewById(R.id.tv_calc);
        Bundle data = getIntent().getExtras();
        if (data!= null){
            tv_test.setText(data.get("test").toString());
        }
        String[] headers = {"Продукт","Вес","бел. гр","Жир. гр","угл. гр","Ккал."};
        columns = getColumns();
//        Toast.makeText(Calculator.this, Arrays.deepToString(getColumns()), Toast.LENGTH_SHORT).show();
        tv_calc.setHeaderAdapter(new SimpleTableHeaderAdapter(this,headers));
        tv_calc.setDataAdapter(new SimpleTableDataAdapter(this, columns));

        TableColumnDpWidthModel columnModel = new TableColumnDpWidthModel(Calculator.this,6,100);
        columnModel.setColumnWidth(0,175);

        tv_calc.setColumnModel(columnModel);





        iv_arrow_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Calculator.this, MainPage.class);
                startActivity(intent);
            }
        });

        btn_add_to_List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Calculator.this, AddToList.class);
                startActivity(intent);
            }
        });

        btn_clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseHelper.ClearTable();
                columns = getColumns();
                tv_calc.setHeaderAdapter(new SimpleTableHeaderAdapter(Calculator.this,headers));
                tv_calc.setDataAdapter(new SimpleTableDataAdapter(Calculator.this, columns));

            }
        });


    }

    public String[][] getColumns(){
        dataBaseHelper = new DataBaseHelper(this);

        String[][] strings = new String[dataBaseHelper.getEveryFood().size()+2][];
        for (int i = 0; i < dataBaseHelper.getEveryFood().size(); i++){

            strings[i] = dataBaseHelper.getEveryFood().get(i);
        }
//        strings[-2] = dataBaseHelper.getEveryFoodWeight();
        strings[strings.length-1] = dataBaseHelper.getEveryFoodWeight();
        strings[strings.length-2] = dataBaseHelper.getEveryFoodWeight100();
//        Toast.makeText(this, dataBaseHelper.getEveryFoodWeight(), Toast.LENGTH_SHORT).show();

        return strings;
    }
}
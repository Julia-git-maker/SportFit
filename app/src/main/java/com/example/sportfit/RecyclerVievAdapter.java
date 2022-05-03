package com.example.sportfit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerVievAdapter extends RecyclerView.Adapter<RecyclerVievAdapter.ViewHolder> {
    List<Food> foodList;
    Context context;
    DataBaseHelper dataBaseHelper;


    public RecyclerVievAdapter(List<Food> foodList, Context context) {
        this.foodList = foodList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_food, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerVievAdapter.ViewHolder holder, int position) {
        holder.tv_food_name.setText(String.valueOf(foodList.get(position).getName()));
        holder.tv_food_bel.setText(String.valueOf(foodList.get(position).getBel()));
        holder.tv_food_zhir.setText(String.valueOf(foodList.get(position).getZhiri()));
        holder.tv_food_ugl.setText(String.valueOf(foodList.get(position).getUgl()));
        holder.tv_food_calories.setText(String.valueOf(foodList.get(position).getCalories()));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);

                alert.setTitle("Рассчитать вес для продукта "+'"'+holder.tv_food_name.getText().toString()+'"');
                alert.setMessage("Введите вес продукта");
// Set an EditText view to get user input
                final EditText input = new EditText(context);
                input.setText("0");
                input.setHint("Вес продукта");
                alert.setView(input);


                alert.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        double value = Double.parseDouble(input.getText().toString());

                        if (value <= 0){
                            Toast.makeText(context, "Введите вес!", Toast.LENGTH_SHORT).show();

                        }else {
                            dataBaseHelper = new DataBaseHelper(context);
                            Food food = new Food(-1,holder.tv_food_name.getText().toString(),value,Double.parseDouble(holder.tv_food_bel.getText().toString()),Double.parseDouble(holder.tv_food_zhir.getText().toString()),Double.parseDouble(holder.tv_food_ugl.getText().toString()),Double.parseDouble(holder.tv_food_calories.getText().toString()));
                            BZDUCalculator calc = new BZDUCalculator(food);
                            dataBaseHelper.addOneToFood(calc.GetEditedFood());
                            Toast.makeText(context, "Готово!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context,Calculator.class);
                            context.startActivity(intent);
                        }
                        // Do something with value!
                    }
                });

                alert.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_food_name;
        TextView tv_food_bel;
        TextView tv_food_zhir;
        TextView tv_food_ugl;
        TextView tv_food_calories;

        com.google.android.material.card.MaterialCardView parentLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_food_name = itemView.findViewById(R.id.tv_food_name);
            tv_food_bel = itemView.findViewById(R.id.tv_food_bel);
            tv_food_zhir = itemView.findViewById(R.id.tv_food_zhir);
            tv_food_ugl = itemView.findViewById(R.id.tv_food_ugl);
            tv_food_calories = itemView.findViewById(R.id.tv_food_calories);
            parentLayout = itemView.findViewById(R.id.card_food);
        }
    }
}

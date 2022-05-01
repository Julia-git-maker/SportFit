package com.example.sportfit;

public class BZDUCalculator {
    private Food food;

    public BZDUCalculator(Food food) {
        this.food = food;
    }

    public Food GetEditedFood(){
        Double bel = (this.food.getBel()/100)*this.food.getWeight();
        Double zhir = (this.food.getZhiri()/100)*this.food.getWeight();
        Double ugl = (this.food.getUgl()/100)*this.food.getWeight();
        Double cal = (this.food.getCalories()/100)*this.food.getWeight();

        Food result = new Food(-1,this.food.getName().toString(),this.food.getWeight(),bel,zhir,ugl,cal);
        return result;
    }
}

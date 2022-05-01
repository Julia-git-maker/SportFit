package com.example.sportfit;

public class Food {
    private int id;
    public String name;
    private double weight;
    private double bel;
    private double zhiri;
    private double ugl;
    private double calories;


    public Food(int id, String name,double weight, double bel, double zhiri, double ugl, double calories) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.bel = bel;
        this.zhiri = zhiri;
        this.ugl = ugl;
        this.calories = calories;
    }

    @Override
    public String toString() {
        return " <---" + name + "---> \n Вес: " + weight + " гр. \n Белки: " + bel + " гр. \n Жиры: " + zhiri + " гр. \n Углеводы: " + ugl + " гр. \n Калории: " + calories + "Ккал.";
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBel() {
        return bel;
    }

    public void setBel(double bel) {
        this.bel = bel;
    }

    public double getZhiri() {
        return zhiri;
    }

    public void setZhiri(double zhiri) {
        this.zhiri = zhiri;
    }

    public double getUgl() {
        return ugl;
    }

    public void setUgl(double ugl) {
        this.ugl = ugl;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public String[] getForTable(){
        String[] strings = new String[7];
        strings[0] = String.valueOf(getName());
        strings[1] = String.valueOf(getWeight());
        strings[2] = String.valueOf(getBel());
        strings[3] = String.valueOf(getZhiri());
        strings[4] = String.valueOf(getUgl());
        strings[5] = String.valueOf(getCalories());


        return strings;

    }
}

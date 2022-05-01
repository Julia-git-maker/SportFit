package com.example.sportfit;

public class MainMenuCalculator {
    private String sex;
    private double age;
    private double height;
    private double weight;
    private double lifestyle;
    private String target;
    private double belki;
    private double zhiri;
    private double uglev;
    private double rbelki;
    private double rzhiri;
    private double ruglev;
    private double calories;

    public MainMenuCalculator(User user) {
        this.sex = user.getSex();
        this.age = user.getAge();
        this.height = user.getHeight();
        this.weight = user.getWeight();
        this.lifestyle = user.getLifestyle();
        this.target = user.getTarget();
        caloriesCalculator();
        preDef();
        bzu();

    }

    public double caloriesCalculator(){
        double result;
        double A;
        double B;

        if (this.sex.equals("male")){
            A = (this.weight*10)+(this.height*6.25)+(this.age*5)+5;
        }else {
            A = (this.weight*10)+(this.height*6.25)+(this.age*5)-161;
        }
        B = A*this.lifestyle;
        if (this.target.equals("buff")){
            result = B + B*0.2;
        }else {
            if (this.target.equals("lose")){
                result = B - B*0.15;
            }else {
                result = B;
            }
        }

        this.calories = result;
        return A;
    }

    public void preDef(){
        if (this.target.equals("buff")){
            this.uglev = 0.50;
            this.belki = 0.35;
            this.zhiri = 0.15;
        }else {
            if (this.target.equals("lose")){
                this.uglev = 0.20;
                this.belki = 0.50;
                this.zhiri = 0.30;
            }else {
                this.uglev = 0.50;
                this.belki = 0.30;
                this.zhiri = 0.20;
            }
        }
    }

    public void bzu(){
        this.rbelki = calories*this.belki;
        this.ruglev = calories*this.uglev;
        this.rzhiri = calories*this.zhiri;
    }

    public int getRbelki() {
        return (int) rbelki;
    }

    public int getRzhiri() {
        return (int) rzhiri;
    }

    public int getRuglev() {
        return (int) ruglev;
    }

    public int getBelki() {
        return (int) belki;
    }

    public int getZhiri() {
        return (int) zhiri;
    }

    public int getUglev() {
        return (int) uglev;
    }

    public int getCalories(){
        return (int) calories;
    }

    @Override
    public String toString() {
        return "MainMenuCalculator{" +
                "sex='" + sex + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", lifestyle=" + lifestyle +
                ", target='" + target + '\'' +
                ", belki=" + belki +
                ", zhiri=" + zhiri +
                ", uglev=" + uglev +
                ", rbelki=" + rbelki +
                ", rzhiri=" + rzhiri +
                ", ruglev=" + ruglev +
                ", calories=" + calories +
                '}';
    }

    public String test(Food food){
        String bel = String.format("%.2f",food.getBel()/100*food.getWeight());
        String zhir = String.format("%.2f", food.getZhiri()/100*food.getWeight());
        String ugl = String.format("%.2f",food.getUgl()/100*food.getWeight());
        String calories = String.format("%.2f",food.getCalories()/100*food.getWeight());
        String weight = String.format("%.2f",food.getWeight());

        return "bel: "+bel +" zhir: "+zhir +" ugl: "+ugl +" calories: "+calories +" weight: "+weight;
    }

}

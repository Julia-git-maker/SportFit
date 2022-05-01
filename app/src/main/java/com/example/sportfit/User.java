package com.example.sportfit;

public class User {
    public static String totalsex = "";
    public static double totalage = 0;
    public static double totalheight = 0;
    public static double totalweight = 0;
    public static double totallifestyle = 0;
    public static String totaltarget = "";

    public String sex;
    private double age;
    private double height;
    private double weight;
    private double lifestyle;
    private String target;

    public User(String sex, double age, double height, double weight, double lifestyle, String target) {
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.lifestyle = lifestyle;
        this.target = target;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(double lifestyle) {
        this.lifestyle = lifestyle;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "User{" +
                "sex='" + sex + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", lifestyle='" + lifestyle + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}

package com.diezavala.project02;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.diezavala.project02.FD.FoodDatabase;

@Entity(tableName = FoodDatabase.FOOD_TABLE)
public class Food {
    @PrimaryKey(autoGenerate = true)
    private int foodLogId;
    private String foodName;
    private int calsPerServing;
    private double servings;
    private int userLogKey;

    public Food(String foodName, int calsPerServing, double servings, int userLogKey) {
        this.foodName = foodName;
        this.calsPerServing = calsPerServing;
        this.servings = servings;
        this.userLogKey = userLogKey;
    }

    @Override
    public String toString() {
        return "Food: " + foodName + "\n" +
                "Cals Per Serving: " + calsPerServing + "\n" +
                "Serving Size: " + servings + "\n" +
                "Total Calories: " + (calsPerServing *  servings)+
                "\n=-=-=-=-=-=-=\n";
    }

    public int getUserLogKey() {
        return userLogKey;
    }

    public void setUserLogKey(int userLogKey) {
        this.userLogKey = userLogKey;
    }

    public int getFoodLogId() {
        return foodLogId;
    }

    public void setFoodLogId(int foodLogId) {
        this.foodLogId = foodLogId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCalsPerServing() {
        return calsPerServing;
    }

    public void setCalsPerServing(int calsPerServing) {
        this.calsPerServing = calsPerServing;
    }

    public double getServings() {
        return servings;
    }

    public void setServings(double servings) {
        this.servings = servings;
    }


}

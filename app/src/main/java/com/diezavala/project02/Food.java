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

    public Food(String foodName, int calsPerServing, double servings) {
        this.foodName = foodName;
        this.calsPerServing = calsPerServing;
        this.servings = servings;
    }

    @Override
    public String toString() {
        return "Log #" + foodLogId + "\n" +
                "Food: " + foodName + "\n" +
                "Cals Per Serving: " + calsPerServing + "\n" +
                "Serving Size: " + servings + "\n" +
                "=-=-=-=-=-=-=\n";
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

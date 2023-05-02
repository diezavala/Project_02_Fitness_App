package com.diezavala.project02.FD;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.diezavala.project02.Food;
import com.diezavala.project02.GL.GymLogDataBase;
import com.diezavala.project02.GymLogItem;

import java.util.List;

@Dao
public interface FoodDAO {
    @Insert
    void insert(Food... food);

    @Update
    void update(Food... food);

    @Delete
    void delete(Food food);

    @Query("SELECT * FROM " + FoodDatabase.FOOD_TABLE)
    List<Food> getAllFoodLogs();

    @Query("SELECT * FROM " + FoodDatabase.FOOD_TABLE + " WHERE foodLogId = :foodLogId ")
    Food getFoodLogById(int foodLogId);

    @Query("SELECT * FROM " + FoodDatabase.FOOD_TABLE + " WHERE foodName = :foodName ")
    Food getFoodLogByFood(String foodName);

}

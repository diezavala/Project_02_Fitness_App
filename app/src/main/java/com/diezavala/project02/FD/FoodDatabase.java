package com.diezavala.project02.FD;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.diezavala.project02.Food;
import com.diezavala.project02.GL.GymLogDataBase;

@Database(entities = {Food.class}, version = 1)
public abstract class FoodDatabase extends RoomDatabase {
    public static final String FOOD_DATABASE_NAME = "Food.db";
    public static final String FOOD_TABLE = "food_table";

    private static volatile FoodDatabase instance;

    private static final Object LOCK = new Object();

    public abstract FoodDAO FoodDAO();

    public static FoodDatabase getInstance(Context context){
        if(instance == null){
            synchronized (LOCK){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            FoodDatabase.class,
                            FOOD_DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}

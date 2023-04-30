package com.diezavala.project02.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.diezavala.project02.users;
//import com.diezavala.project02.DB.UserDAO;

@Database(entities = {users.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {
    public static final String DATABASE_NAME = "userLogin.db";
    public static final String USER_TABLE = "userLogIn_table";

    private static volatile AppDataBase instance;

    private static final Object LOCK = new Object();

    public abstract UserDAO getDAO();

    public static AppDataBase getInstance(Context context){
        if(instance == null){
            synchronized (LOCK){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class,
                            DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}

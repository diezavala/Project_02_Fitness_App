package com.diezavala.project02.GL;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.diezavala.project02.GymLogItem;

@Database(entities = {GymLogItem.class},version = 1)
public abstract class GymLogDataBase extends RoomDatabase {
    public static final String GL_DATABASE_NAME = "GymLog.db";
    public static final String GYMLOG_TABLE = "gymlog_table";

    private static volatile GymLogDataBase instance;

    private static final Object LOCK = new Object();

    public abstract GymLogDAO GymLogDAO();

    public static GymLogDataBase getInstance(Context context){
        if(instance == null){
            synchronized (LOCK){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            GymLogDataBase.class,
                            GL_DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}

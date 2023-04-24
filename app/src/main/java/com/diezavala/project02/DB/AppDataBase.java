package com.diezavala.project02.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.diezavala.project02.GymLog;


@Database(entities= {GymLog.class},version=1)
@TypeConverters({DateTypeConverter.class})
public abstract class AppDataBase extends RoomDatabase {
    public static final String DATABASE_NAME="GymLog.db";
    public static final String GYMLOG_TABLE="gymlog_table";

    private static volatile AppDataBase instance;
    private static final Object LOCK =new Object();
    public abstract GymLogDAO GymLogDAO();


    public static AppDataBase getInstance(Context context){
        if(instance ==null){
            synchronized (LOCK){
                if(instance==null){
                    instance= Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class,
                            DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}

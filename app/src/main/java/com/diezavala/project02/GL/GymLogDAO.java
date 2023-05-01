package com.diezavala.project02.GL;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.diezavala.project02.GymLogItem;

import java.util.List;

@Dao
public interface GymLogDAO {
    @Insert
    void insert(GymLogItem... gymLogs);

    @Update
    void update(GymLogItem... gymLogs);

    @Delete
    void delete(GymLogItem gymLogs);

    @Query("SELECT * FROM " + GymLogDataBase.GYMLOG_TABLE)
    List<GymLogItem> getGymLogs();

    @Query("SELECT * FROM " + GymLogDataBase.GYMLOG_TABLE + " WHERE gymLogId = :gymLogId ")
    List<GymLogItem> getLogById(int gymLogId);

}

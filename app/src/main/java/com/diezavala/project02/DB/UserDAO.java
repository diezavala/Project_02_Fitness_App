package com.diezavala.project02.DB;

//import com.diezavala.project02.DB.AppDataBase;
import com.diezavala.project02.users;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {

//    @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE logId =:logId")
//    List<users> getGymLogsByUserId(int logId);
    @Insert
    void insert(users... user);

    @Update
    void update(users... user);

    @Delete
    void delete(users user);

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE)
    List<users> getALlUsers();

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE username = :username")
    users getUserByUsername(String username);

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE logId = :id")
    users getUserByUSerId(int id);



}

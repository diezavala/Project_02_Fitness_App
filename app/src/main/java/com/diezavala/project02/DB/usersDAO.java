package com.diezavala.project02.DB;

import com.diezavala.project02.users;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface usersDAO {
    @Insert
    void insert(users... user);

    @Update
    void update(users... user);

    @Delete
    void delete(users user);
}

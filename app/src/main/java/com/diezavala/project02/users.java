package com.diezavala.project02;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.diezavala.project02.DB.AppDataBase;

@Entity(tableName = AppDataBase.USER_TABLE)
public class users {
    @PrimaryKey(autoGenerate = true)
    private int logId;

    private String username;
    private String password;

    public users(String username, String password) {
        this.username = username;
        this.password = password;
//        this.logId = logId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }
}

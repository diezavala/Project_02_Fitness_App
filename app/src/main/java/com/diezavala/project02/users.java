package com.diezavala.project02;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.diezavala.project02.DB.AppDataBase;

@Entity(tableName = AppDataBase.USERLOGIN_TABLE)
public class users {
    @PrimaryKey(autoGenerate = true)
    private int logId;
    private String user_name;
    private String user_password;

    public users(String user_name, String user_password) {
        this.user_name = user_name;
        this.user_password = user_password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }
}

package com.diezavala.project02;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
//import com.daclink.gymlog_v_sp22.DB.AppDataBase;
import com.diezavala.project02.DB.AppDataBase;
import java.util.Date;
@Entity(tableName = AppDataBase.GYMLOG_TABLE)

public class GymLog {

    @PrimaryKey(autoGenerate=true)
    private int logId;

    private String exercise;
    private double weight;
    private int reps;
    private Date date;

    public GymLog(String exercise, double weight, int reps) {
        this.exercise = exercise;
        this.weight = weight;
        this.reps = reps;
        this.date= new Date();

    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Log # "+logId+"\n"+
                "Exercise: "+exercise+"\n"+
                "Weight: "+weight+"\n"+
                "Reps: # "+reps+"\n"+
                "Date: "+date+"\n"+
                "=-=-=-=-=-=-\n";
    }
}

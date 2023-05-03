package com.diezavala.project02;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.diezavala.project02.GL.GymLogDataBase;

import java.util.Date;

@Entity(tableName = GymLogDataBase.GYMLOG_TABLE)
public class GymLogItem {
    @PrimaryKey(autoGenerate = true)
    private int gymLogId;
    private String exercise;
    private int reps;
    private double weight;

//    int gymLogKey;
//    private Date date;

    public GymLogItem(String exercise, int reps, double weight) {
        this.exercise = exercise;
        this.reps = reps;
        this.weight = weight;
//        date = new Date();
    }

    @Override
    public String toString() {
        return "Log #" + gymLogId + "\n" +
                "Exercise: " + exercise + "\n" +
                "Weight: " + weight + "\n" +
                "Reps: " + reps + "\n" +
                "=-=-=-=-=-=-=\n";
    }

    public int getGymLogId() {
        return gymLogId;
    }

    public void setGymLogId(int gymLogId) {
        this.gymLogId = gymLogId;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
}

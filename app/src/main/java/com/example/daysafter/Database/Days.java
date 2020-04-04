package com.example.daysafter.Database;

public class Days {
    private int id;
    private String targetDate;

    public int getId(){
        return id;
    }

    public void  setId(int id) {
        this.id = id;
    }

    public String getName() {
        return targetDate;
    }

    public  void setDay (String studentName) {
        this.targetDate = studentName;
    }

    @Override
    public String toString() {
        return targetDate;
    }
}

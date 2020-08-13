package sample;

import java.io.Serializable;

public class Date implements Serializable {
    private int day;
    private int month;
    private int year;
    private boolean isLeapYear;

    public Date(int day, int month, int year) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
       this.day = day;

    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
       this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
       this.year = year;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }
}

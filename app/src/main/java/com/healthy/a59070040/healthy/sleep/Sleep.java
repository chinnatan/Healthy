package com.healthy.a59070040.healthy.sleep;

public class Sleep {

    private int primaryId;
    private String currentDate;
    private String timetosleepHour;
    private String timetosleepMin;
    private String timetowakeupHour;
    private String timetowakeupMin;
    private String counttime;

    private static Sleep sleepInstance;

    private Sleep() {

    }

    public Sleep(int primaryId, String currentDate, String timetosleepHour, String timetosleepMin, String timetowakeupHour, String timetowakeupMin, String counttime) {
        this.setPrimaryId(primaryId);
        this.setCurrentDate(currentDate);
        this.setTimetosleepHour(timetosleepHour);
        this.setTimetosleepMin(timetosleepMin);
        this.setTimetowakeupHour(timetowakeupHour);
        this.setTimetowakeupMin(timetowakeupMin);
        this.setCounttime(counttime);
    }

    public static Sleep getSleepInstance() {
        if(sleepInstance == null) {
            sleepInstance = new Sleep();
        }
        return sleepInstance;
    }

    public static Sleep setSleepInstance() {
        if(sleepInstance != null) {
            sleepInstance = null;
        }
        return sleepInstance;
    }

    public int getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(int primaryId) {
        this.primaryId = primaryId;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getTimetosleepHour() {
        return timetosleepHour;
    }

    public void setTimetosleepHour(String timetosleepHour) {
        this.timetosleepHour = timetosleepHour;
    }

    public String getTimetosleepMin() {
        return timetosleepMin;
    }

    public void setTimetosleepMin(String timetosleepMin) {
        this.timetosleepMin = timetosleepMin;
    }

    public String getTimetowakeupHour() {
        return timetowakeupHour;
    }

    public void setTimetowakeupHour(String timetowakeupHour) {
        this.timetowakeupHour = timetowakeupHour;
    }

    public String getTimetowakeupMin() {
        return timetowakeupMin;
    }

    public void setTimetowakeupMin(String timetowakeupMin) {
        this.timetowakeupMin = timetowakeupMin;
    }

    public String getCounttime() {
        return counttime;
    }

    public void setCounttime(String counttime) {
        this.counttime = counttime;
    }
}

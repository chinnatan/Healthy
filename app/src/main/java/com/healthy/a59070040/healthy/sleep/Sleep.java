package com.healthy.a59070040.healthy.sleep;

public class Sleep {

    private String currentDate;
    private String timetosleep;
    private String timetowakeup;
    private String counttime;

    public Sleep() {

    }

    public Sleep(String currentDate, String timetosleep, String timetowakeup, String counttime) {
        this.setCurrentDate(currentDate);
        this.setTimetosleep(timetosleep);
        this.setTimetowakeup(timetowakeup);
        this.setCounttime(counttime);
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getTimetosleep() {
        return timetosleep;
    }

    public void setTimetosleep(String timetosleep) {
        this.timetosleep = timetosleep;
    }

    public String getTimetowakeup() {
        return timetowakeup;
    }

    public void setTimetowakeup(String timetowakeup) {
        this.timetowakeup = timetowakeup;
    }

    public String getCounttime() {
        return counttime;
    }

    public void setCounttime(String counttime) {
        this.counttime = counttime;
    }
}

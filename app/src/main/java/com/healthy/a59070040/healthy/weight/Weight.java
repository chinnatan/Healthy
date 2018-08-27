package com.healthy.a59070040.healthy.weight;

public class Weight {

    String date;
    int weight;
    String status;

    public Weight() {}

    public Weight(String date, int weight, String status) {
        this.date = date;
        this.weight = weight;
        this.status = status;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

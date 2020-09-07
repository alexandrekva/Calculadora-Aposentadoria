package com.akva.calculadoraaposentadoria.activity.model;

import java.io.Serializable;

public class MonthValueData implements Serializable {
    private double total;
    private int month;

    public MonthValueData(double total, int month) {
        this.total = total;
        this.month = month;
    }

    public MonthValueData() {
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}

package com.akva.calculadoraaposentadoria.activity.model;

import com.akva.calculadoraaposentadoria.activity.HomeActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CalculusDataObject implements Serializable {

    private double totalValue;
    private double totalInInterest;
    private double totalInvested;
    private double monthlyDividends;
    private int firstMillionMonth;
    private int dividendsOverContributionMonth;
    private List<MonthValueData> monthValueData;

    public CalculusDataObject() {
        this.monthValueData = new ArrayList<>();
        this.totalInvested = 0;
        this.totalInInterest = 0;
        this.totalInvested = 0;
        this.firstMillionMonth = 0;
        this.dividendsOverContributionMonth = 0;
        this.monthlyDividends = 0;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.monthlyDividends = totalValue * HomeActivity.MONTHLY_DIVIDENDS;
        this.totalValue = totalValue;
    }

    public int getFirstMillionMonth() {
        return firstMillionMonth;
    }

    public void setFirstMillionMonth(int firstMillionMonth) {
        this.firstMillionMonth = firstMillionMonth;
    }

    public int getDividendsOverContributionMonth() {
        return dividendsOverContributionMonth;
    }

    public void setDividendsOverContributionMonth(int dividendsOverContributionMonth) {
        this.dividendsOverContributionMonth = dividendsOverContributionMonth;
    }

    public double getTotalInInterest() {
        return totalInInterest;
    }

    public void setTotalInInterest(double totalInInterest) {
        this.totalInInterest = totalInInterest;
    }

    public double getTotalInvested() {
        return totalInvested;
    }

    public void setTotalInvested(double totalInvested) {
        this.totalInvested = totalInvested;
    }

    public double getMonthlyDividends() {
        return monthlyDividends;
    }


    public List<MonthValueData> getMonthValueData() {
        return monthValueData;
    }

    public void setMonthValueData(List<MonthValueData> monthValueData) {
        this.monthValueData = monthValueData;
    }

    public void addMonthValueData(MonthValueData monthValueData) {
        this.monthValueData.add(monthValueData);
    }
}

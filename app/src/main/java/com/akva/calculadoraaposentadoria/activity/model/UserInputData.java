package com.akva.calculadoraaposentadoria.activity.model;

public class UserInputData {

    private double initialValue;
    private double monthlyContribution;
    private double monthlyProfitability;
    private int periodInMonths;
    private boolean reinvestDividends;
    private double monthlyDividendsPercent;

    public UserInputData(double initialValue, double monthlyContribution, double monthlyProfitability, int periodInMonths, boolean reinvestDividends, double monthlyDividendsPercent) {
        this.initialValue = initialValue;
        this.monthlyContribution = monthlyContribution;
        this.monthlyProfitability = monthlyProfitability;
        this.periodInMonths = periodInMonths;
        this.reinvestDividends = reinvestDividends;
        this.monthlyDividendsPercent = monthlyDividendsPercent;
    }

    public double getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(double initialValue) {
        this.initialValue = initialValue;
    }

    public double getMonthlyContribution() {
        return monthlyContribution;
    }

    public void setMonthlyContribution(double monthlyContribution) {
        this.monthlyContribution = monthlyContribution;
    }

    public double getMonthlyProfitability() {
        return monthlyProfitability;
    }

    public void setMonthlyProfitability(double monthlyProfitability) {
        this.monthlyProfitability = monthlyProfitability;
    }

    public int getPeriodInMonths() {
        return periodInMonths;
    }

    public void setPeriodInMonths(int periodInMonths) {
        this.periodInMonths = periodInMonths;
    }

    public boolean isReinvestDividends() {
        return reinvestDividends;
    }

    public void setReinvestDividends(boolean reinvestDividends) {
        this.reinvestDividends = reinvestDividends;
    }

    public double getMonthlyDividendsPercent() {
        return monthlyDividendsPercent;
    }

    public void setMonthlyDividendsPercent(double monthlyDividendsPercent) {
        this.monthlyDividendsPercent = monthlyDividendsPercent;
    }
}

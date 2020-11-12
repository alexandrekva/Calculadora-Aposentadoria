package com.akva.calculadoraaposentadoria.activity.util;

import com.akva.calculadoraaposentadoria.activity.HomeActivity;
import com.akva.calculadoraaposentadoria.activity.model.CalculusDataObject;
import com.akva.calculadoraaposentadoria.activity.model.MonthValueData;
import com.akva.calculadoraaposentadoria.activity.model.UserInputData;

public class CalculationUtil {

    private  boolean firstMillion = false;
    private  boolean dividendsOverContribution = false;

    public  CalculusDataObject calculateProfitability(UserInputData userInputData){
        Double initialValue = userInputData.getInitialValue();
        Double monthlyContribution = userInputData.getMonthlyContribution();
        Double monthlyProfitability = userInputData.getMonthlyProfitability();
        int periodInMonths = userInputData.getPeriodInMonths();
        Boolean reinvestDividends = userInputData.isReinvestDividends();
        Double monthlyDividendsPercent = userInputData.getMonthlyDividendsPercent();

        CalculusDataObject dataObject = new CalculusDataObject();
        double currentValue = initialValue;
        double totalInvested = 0;

        for (int i=0; i<periodInMonths; i++){
            currentValue += (currentValue * monthlyProfitability) + monthlyContribution;
            totalInvested += monthlyContribution;


            if (reinvestDividends) {
                double monthlyDividends = (currentValue * monthlyDividendsPercent);
                currentValue += monthlyDividends;
                totalInvested += monthlyDividends;
            }

            if (checkFirstMillion(currentValue)) {
                dataObject.setFirstMillionMonth(i);
            }

            if (checkDividendsOverContribution(currentValue, monthlyContribution, monthlyDividendsPercent)) {
                dataObject.setDividendsOverContributionMonth(i);
            }

            dataObject.addMonthValueData(new MonthValueData(currentValue, i+1));
        }

        double totalInInterest = currentValue - totalInvested;

        dataObject.setTotalInvested(totalInvested);
        dataObject.setTotalValue(currentValue);
        dataObject.setTotalInInterest(totalInInterest);
        dataObject.setMonthlyDividendsPercent(monthlyDividendsPercent);
        dataObject.setMonthlyDividends();

        return dataObject;
    }

    private  boolean checkFirstMillion(double currentValue){
        if (firstMillion == false && currentValue >= 1000000){
            firstMillion = true;
            return true;
        } else{
            return false;
        }
    }

    private  boolean checkDividendsOverContribution(double currentValue, double monthlyContribution, double monthlyDividendsPercent){
        if (dividendsOverContribution == false && (currentValue * monthlyDividendsPercent >= monthlyContribution)){
            dividendsOverContribution = true;
            return true;
        } else {
            return false;
        }
    }

}

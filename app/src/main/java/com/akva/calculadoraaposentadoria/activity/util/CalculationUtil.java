package com.akva.calculadoraaposentadoria.activity.util;

import com.akva.calculadoraaposentadoria.activity.HomeActivity;
import com.akva.calculadoraaposentadoria.activity.model.CalculusDataObject;
import com.akva.calculadoraaposentadoria.activity.model.MonthValueData;

public class CalculationUtil {

    private boolean firstMillion = false;
    private boolean dividendsOverContribution = false;

    public CalculusDataObject calculateProfitability(double initialValue, double monthlyContribution, double monthlyProfitability, int periodInMonths, boolean dividendsSwitch){
        CalculusDataObject dataObject = new CalculusDataObject();
        double currentValue = initialValue;
        double totalInvested = 0;

        for (int i=0; i<periodInMonths; i++){
            currentValue += (currentValue * monthlyProfitability) + monthlyContribution;
            totalInvested += monthlyContribution;


            if (dividendsSwitch) {
                double monthlyDividends = (currentValue * HomeActivity.MONTHLY_DIVIDENDS);
                currentValue += monthlyDividends;
                totalInvested += monthlyDividends;
            }

            if (checkFirstMillion(currentValue))
                dataObject.setFirstMillionMonth(i);

            if (checkDividendsOverContribution(currentValue, monthlyContribution))
                dataObject.setDividendsOverContributionMonth(i);

            dataObject.addMonthValueData(new MonthValueData(currentValue, i+1));
        }

        double totalInInterest = currentValue - totalInvested;

        dataObject.setTotalInvested(totalInvested);
        dataObject.setTotalValue(currentValue);
        dataObject.setTotalInInterest(totalInInterest);

        return dataObject;
    }

    private boolean checkFirstMillion(double currentValue){
        if (firstMillion == false && currentValue >= 1000000){
            firstMillion = true;
            return true;
        } else{
            return false;
        }
    }

    private boolean checkDividendsOverContribution(double currentValue, double monthlyContribution){
        if (dividendsOverContribution == false && (currentValue * HomeActivity.MONTHLY_DIVIDENDS >= monthlyContribution)){
            dividendsOverContribution = true;
            return true;
        } else {
            return false;
        }
    }

}

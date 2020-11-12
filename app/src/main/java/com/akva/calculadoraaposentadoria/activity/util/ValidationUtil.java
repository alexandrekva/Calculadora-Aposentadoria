package com.akva.calculadoraaposentadoria.activity.util;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class ValidationUtil {

    private  EditText editTextInitialValue, editTextMonthlyContribution, editTextMonthlyProfitability;
    private Context context;

    public ValidationUtil(EditText editTextInitialValue, EditText editTextMonthlyContribution, EditText editTextMonthlyProfitability, Context context) {
        this.context = context;
        this.editTextInitialValue = editTextInitialValue;
        this.editTextMonthlyContribution = editTextMonthlyContribution;
        this.editTextMonthlyProfitability = editTextMonthlyProfitability;
    }


    public boolean validation(){
        if (inputFilled())
            return validateMonthlyProfitability();

        return false;
    }

    private boolean inputFilled(){

        if (editTextInitialValue == null || editTextInitialValue.getText().toString().isEmpty()) {
            Toast.makeText(context, "Insira um valor inicial válido", Toast.LENGTH_SHORT).show();
            return false;
        } else if (editTextMonthlyContribution == null || editTextMonthlyContribution.getText().toString().isEmpty()) {
            Toast.makeText(context, "Insira uma contribuição mensal válida", Toast.LENGTH_SHORT).show();
            return false;
        } else if (editTextMonthlyProfitability == null || editTextMonthlyProfitability.getText().toString().isEmpty()) {
            Toast.makeText(context, "Insira uma rentabilidade mensal válida", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateMonthlyProfitability(){
        Double monthlyProfitability = Double.parseDouble(editTextMonthlyProfitability.getText().toString());

        if (monthlyProfitability > 3.0){
            Toast.makeText(context, "Rentabilidade mensal muito elevada!\nInsira um valor menor que 3% ao mês.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


}

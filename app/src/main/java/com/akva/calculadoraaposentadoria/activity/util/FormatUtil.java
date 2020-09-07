package com.akva.calculadoraaposentadoria.activity.util;

import java.text.DecimalFormat;

public class FormatUtil {

    public String formatDecimal (double d){

        DecimalFormat df = new DecimalFormat();
        df.applyPattern(" #,##0.00");

        return df.format(d);
    }

}

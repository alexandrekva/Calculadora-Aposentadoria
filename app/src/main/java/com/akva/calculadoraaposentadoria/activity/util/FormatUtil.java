package com.akva.calculadoraaposentadoria.activity.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class FormatUtil {

    public String formatDecimal (double d){

        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols( new Locale("pt", "BR"));
        unusualSymbols.setDecimalSeparator(',');
        unusualSymbols.setGroupingSeparator('.');

        String pattern = (" #,##0.00");
        DecimalFormat df = new DecimalFormat(pattern, unusualSymbols);

        return df.format(d);
    }

    public String formatWithoutDecimal (double d){

        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols( new Locale("pt", "BR"));
        unusualSymbols.setGroupingSeparator('.');

        String pattern = (" #,##0");
        DecimalFormat df = new DecimalFormat(pattern, unusualSymbols);

        return df.format(d);
    }

}

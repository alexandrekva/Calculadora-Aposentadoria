package com.akva.calculadoraaposentadoria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.akva.calculadoraaposentadoria.R;
import com.akva.calculadoraaposentadoria.activity.model.CalculusDataObject;
import com.akva.calculadoraaposentadoria.activity.model.MonthValueData;
import com.akva.calculadoraaposentadoria.activity.util.FormatUtil;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;
import java.util.List;


public class DetailsActivity extends AppCompatActivity {

    // Variáveis
    private TextView textTotalInvested, textTotalInterest, textTotal, textDividendsOverContribution, textFirstMillion;
    private LineChart lineChart;
    private Intent intent;
    private CalculusDataObject dataObject;

    // Atribui os valores recebidos aos campos de texto.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textTotalInterest = findViewById(R.id.textViewTotalInInterestValue);
        textTotalInvested = findViewById(R.id.textViewTotalInvestedValue);
        textTotal = findViewById(R.id.textViewTotalValue);
        textDividendsOverContribution = findViewById(R.id.textViewDividendsOverContribution);
        textFirstMillion = findViewById(R.id.textViewFirstMillion);
        lineChart = findViewById(R.id.lineChart);
        intent = getIntent();

        setDetails();
        loadChartData();

    }

    private void setDetails(){
        this.dataObject = (CalculusDataObject) intent.getSerializableExtra("dataObject");
        int dividendsOverContributionMonth = dataObject.getDividendsOverContributionMonth();
        int firstMillionMonth = dataObject.getFirstMillionMonth();

        String totalValueString = new FormatUtil().formatDecimal(dataObject.getTotalValue());
        String totalInInterestString = new FormatUtil().formatDecimal(dataObject.getTotalInInterest());
        String totalInvestedString = new FormatUtil().formatDecimal(dataObject.getTotalInvested());

        this.textTotal.setText(totalValueString);
        this.textTotalInterest.setText(totalInInterestString);
        this.textTotalInvested.setText(totalInvestedString);


        if (dividendsOverContributionMonth != 0)
            textDividendsOverContribution.setText("Seus dividendos ultrapassaram o valor do aporte mensal no mês " + dividendsOverContributionMonth + "!");

        if (firstMillionMonth != 0)
            textFirstMillion.setText("Você alcançou o primeiro milhão no mês " + firstMillionMonth + "!");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void loadChartData(){
        List<Entry> entries = new ArrayList<Entry>();
        List<MonthValueData> dataObjects = this.dataObject.getMonthValueData();

        for (MonthValueData data : dataObjects){
            entries.add(new Entry(data.getMonth(), Float.parseFloat(String.valueOf(data.getTotal()))));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Patrimônio acumulado");
        dataSet.setDrawCircles(false);
        dataSet.setDrawValues(false);
        dataSet.setLineWidth(3);
        dataSet.setColor(getResources().getColor(R.color.colorAccent));

        LineData lineData = new LineData(dataSet);

        Description description = new Description();
        description.setText("Evolução patrimonial");
        description.setTextSize(10);
        description.setTextColor(getResources().getColor(R.color.colorText));
        lineChart.getLegend().setEnabled(false);
        lineChart.setDescription(description);
        lineChart.setData(lineData);
        lineChart.setDrawBorders(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawLabels(false);
        lineChart.getAxisLeft().setDrawAxisLine(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getXAxis().setDrawLabels(true);
        lineChart.getXAxis().setDrawAxisLine(false);
        lineChart.getAxisRight().setDrawGridLines(true);
        lineChart.getAxisRight().setDrawLabels(true);
        lineChart.getAxisRight().setGridLineWidth(1);
        lineChart.getAxisRight().setDrawAxisLine(false);
        lineChart.getAxisRight().setTextColor(getResources().getColor(R.color.colorText));
        lineChart.setTouchEnabled(false);
        lineChart.animateX(1500);
        lineChart.invalidate();
    }


}


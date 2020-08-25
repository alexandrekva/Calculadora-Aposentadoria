package com.example.calculadoraaposentadoria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.example.calculadoraaposentadoria.R;
import com.example.calculadoraaposentadoria.activity.models.MonthValueData;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class DetailsActivity extends AppCompatActivity {

    // Variáveis
    private TextView textTotalInvested, textTotalInterest, textTotal, textDividendsOverContribution;
    private LineChart lineChart;

    // Atribui os valores recebidos aos campos de texto.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textTotalInterest = findViewById(R.id.textViewTotalInterest);
        textTotalInvested = findViewById(R.id.textViewTotalInvested);
        textTotal = findViewById(R.id.textViewTotal);
        textDividendsOverContribution = findViewById(R.id.textViewDividendsOverContribution);
        lineChart = findViewById(R.id.lineChart);

        Intent intent = getIntent();
        DecimalFormat decimalFormat = new DecimalFormat("#,#00.00");
        loadChartData();



        Double total, totalInvested, totalInterest;
        total = intent.getDoubleExtra("total", 0);
        totalInterest = intent.getDoubleExtra("totalInterest", 0);
        totalInvested = intent.getDoubleExtra("totalInvested", 0);
        int dividendsOverContributionMonth = intent.getIntExtra("dividendsOverContribution", 0);
        List<MonthValueData> list = ((List<MonthValueData>)getIntent().getExtras().getSerializable("list"));

        textTotalInterest.setText("R$: " + decimalFormat.format(totalInterest));
        textTotal.setText("R$: " + decimalFormat.format(total));
        textTotalInvested.setText("R$: " + decimalFormat.format(totalInvested));

        // Verifica se os dividendos ultrapassaram o valor da contribuição mensal.
        if (dividendsOverContributionMonth != 0) {
            textDividendsOverContribution.setText("Seus dividendos ultrapassaram o valor do aporte mensal no mês " + dividendsOverContributionMonth + "!");
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void loadChartData(){
        List<Entry> entries = new ArrayList<Entry>();
        List<MonthValueData> dataObjects = ((List<MonthValueData>) getIntent().getExtras().get("list"));

        for (MonthValueData data : dataObjects){
            entries.add(new Entry(data.getMonth(), Float.parseFloat(String.valueOf(data.getTotal()))));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Patrimônio acumulado");
        dataSet.setDrawCircles(false);
        dataSet.setDrawValues(false);
        dataSet.setLineWidth(2);
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
        lineChart.getXAxis().setDrawLabels(false);
        lineChart.getXAxis().setDrawAxisLine(false);
        lineChart.getAxisRight().setDrawGridLines(false);
        lineChart.getAxisRight().setDrawLabels(true);
        lineChart.getAxisRight().setDrawAxisLine(false);
        lineChart.getAxisRight().setTextColor(getResources().getColor(R.color.colorText));
        lineChart.setTouchEnabled(false);
        lineChart.animateX(1500);
        lineChart.invalidate();
    }


}


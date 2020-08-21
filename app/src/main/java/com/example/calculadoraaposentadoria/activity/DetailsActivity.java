package com.example.calculadoraaposentadoria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.example.calculadoraaposentadoria.R;

import java.text.DecimalFormat;


public class DetailsActivity extends AppCompatActivity {

    // Variáveis
    private TextView textTotalInvested, textTotalInterest, textTotal, textDividendsOverContribution;

    // Atribui os valores recebidos aos campos de texto.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textTotalInterest = findViewById(R.id.textViewTotalInterest);
        textTotalInvested = findViewById(R.id.textViewTotalInvested);
        textTotal = findViewById(R.id.textViewTotal);
        textDividendsOverContribution = findViewById(R.id.textViewDividendsOverContribution);

        Intent intent = getIntent();
        DecimalFormat decimalFormat = new DecimalFormat("#,#00.00");

        Double total, totalInvested, totalInterest;
        total = intent.getDoubleExtra("total", 0);
        totalInterest = intent.getDoubleExtra("totalInterest", 0);
        totalInvested = intent.getDoubleExtra("totalInvested", 0);
        int dividendsOverContributionMonth = intent.getIntExtra("dividendsOverContribution", 0);

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
}


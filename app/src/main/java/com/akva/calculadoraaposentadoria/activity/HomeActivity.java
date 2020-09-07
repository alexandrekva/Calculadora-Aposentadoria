package com.akva.calculadoraaposentadoria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.akva.calculadoraaposentadoria.R;
import com.akva.calculadoraaposentadoria.activity.model.CalculusDataObject;
import com.akva.calculadoraaposentadoria.activity.model.MonthValueData;
import com.akva.calculadoraaposentadoria.activity.util.CalculationUtil;
import com.akva.calculadoraaposentadoria.activity.util.FormatUtil;
import java.io.Serializable;
import java.util.List;


public class HomeActivity extends AppCompatActivity {

    // Variáveis
    public static final double MONTHLY_DIVIDENDS = 0.3 / 100;
    private TextView textViewTotalValue, textViewYearPeriod, textViewMonthlyDividends;
    private EditText editTextInitialValue, editTextMonthlyContribution, editTextMonthlyProfitability;
    private SeekBar seekBarYearPeriod;
    private ToggleButton toggleButtonDividends;
    private CalculusDataObject dataObject = new CalculusDataObject();
    private boolean status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        textViewTotalValue = findViewById(R.id.textViewTotalValue);
        textViewYearPeriod = findViewById(R.id.textViewYearPeriod);
        textViewMonthlyDividends = findViewById(R.id.textViewMonthlyDividends);
        editTextInitialValue = findViewById(R.id.editTextInitialValue);
        editTextMonthlyProfitability = findViewById(R.id.editTextMonthlyProfitability);
        editTextMonthlyContribution = findViewById(R.id.editTextMonthlyContribution);
        seekBarYearPeriod = findViewById(R.id.seekBarYearPeriod);
        toggleButtonDividends = findViewById(R.id.toggleButtonDividends);




        seekBarYearPeriod.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewYearPeriod.setText(progress + " Anos");
                doCalculus();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                validation();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        toggleButtonDividends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
                doCalculus();
            }
        });

    }

    private void doCalculus(){

        if (status){
            double initialValue = Double.parseDouble(this.editTextInitialValue.getText().toString());
            double monthlyContribution = Double.parseDouble(this.editTextMonthlyContribution.getText().toString());
            double monthlyProfitability = (Double.parseDouble((this.editTextMonthlyProfitability.getText().toString()))) / 100;
            int periodInMonths = seekBarYearPeriod.getProgress() * 12;

            dataObject = new CalculationUtil().calculateProfitability(initialValue, monthlyContribution, monthlyProfitability, periodInMonths, toggleButtonDividends.isChecked());
            String totalValueString = new FormatUtil().formatDecimal(dataObject.getTotalValue());
            String monthlyDividendsString = new FormatUtil().formatDecimal(dataObject.getMonthlyDividends());
            this.textViewTotalValue.setText(totalValueString);
            this.textViewMonthlyDividends.setText("Dividendos mensais: " + monthlyDividendsString);
        }
    }

    // Método de validação dos campos de entrada de dados
    // Define a variável status como true se todos os campos de inserção de dados forem preenchidos, do contrário, define como false.
    private void validation() {
     if (editTextInitialValue == null || editTextInitialValue.getText().toString().isEmpty() ){
         Toast.makeText(this, "Insira um valor inicial válido", Toast.LENGTH_SHORT).show();
         status = false;
     } else if (editTextMonthlyContribution == null || editTextMonthlyContribution.getText().toString().isEmpty()){
         Toast.makeText(this, "Insira uma contribuição mensal válida", Toast.LENGTH_SHORT).show();
         status = false;
     } else if (editTextMonthlyProfitability == null || editTextMonthlyProfitability.getText().toString().isEmpty()){
         Toast.makeText(this, "Insira uma rentabilidade mensal válida", Toast.LENGTH_SHORT).show();
         status = false;
     } else{
         status = true;
     }
    }


        // Método que encaminha para a activity de detalhes
        // Passa para a activity os valores de total investido, total ganho em júros, o total acumulado e se os dividendos mensais ultrapassam a contribuição mensal do usuário.
        public void moreDetails(View view) {
            Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
            double totalInvested = dataObject.getTotalInvested();
            double totalInInterest = dataObject.getTotalInInterest();
            double totalValue = dataObject.getTotalValue();
            int dividendsOverContributionMonth = dataObject.getDividendsOverContributionMonth();
            List<MonthValueData> monthValueDataList = dataObject.getMonthValueData();

            intent.putExtra("totalInvested", totalInvested);
            intent.putExtra("totalInInterest", totalInInterest);
            intent.putExtra("totalValue", totalValue);
            intent.putExtra("dividendsOverContributionMonth", dividendsOverContributionMonth);
            intent.putExtra("monthValueDataList", (Serializable) monthValueDataList);
            startActivity(intent);
        }

        }
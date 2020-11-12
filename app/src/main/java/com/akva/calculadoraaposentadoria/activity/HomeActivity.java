package com.akva.calculadoraaposentadoria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.akva.calculadoraaposentadoria.R;
import com.akva.calculadoraaposentadoria.activity.model.CalculusDataObject;
import com.akva.calculadoraaposentadoria.activity.model.UserInputData;
import com.akva.calculadoraaposentadoria.activity.util.CalculationUtil;
import com.akva.calculadoraaposentadoria.activity.util.FormatUtil;
import com.akva.calculadoraaposentadoria.activity.util.ValidationUtil;


public class HomeActivity extends AppCompatActivity implements InfoDialog.ExampleDialogListener {

    // Variáveis
    private double monthlyDividends = 0.3;
    private double monthlyDividendsPercent = monthlyDividends / 100;
    private double initialValue = 0;
    private TextView textViewTotalValue, textViewYearPeriod, textViewMonthlyDividends;
    private EditText editTextInitialValue, editTextMonthlyContribution, editTextMonthlyProfitability;
    private SeekBar seekBarYearPeriod;
    private ToggleButton toggleButtonDividends;
    private CalculusDataObject dataObject;
    private ImageView imageViewInfo;
    private boolean validationStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textViewTotalValue = findViewById(R.id.textViewTotalValue);
        textViewYearPeriod = findViewById(R.id.textViewYearPeriod);
        textViewMonthlyDividends = findViewById(R.id.textViewMonthlyDividends);
        editTextInitialValue = findViewById(R.id.editTextInitialValue);
        editTextMonthlyProfitability = findViewById(R.id.editTextMonthlyProfitability);
        editTextMonthlyContribution = findViewById(R.id.editTextMonthlyContribution);
        seekBarYearPeriod = findViewById(R.id.seekBarYearPeriod);
        toggleButtonDividends = findViewById(R.id.toggleButtonDividends);
        imageViewInfo = findViewById(R.id.imageViewInfo);




        seekBarYearPeriod.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewYearPeriod.setText(progress + " Anos");

                if (validationStatus) {
                doCalculus();
                setTextViewsValues(dataObject);
                }
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
                if (validationStatus) {
                    doCalculus();
                    setTextViewsValues(dataObject);
                }
            }
        });

        imageViewInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo();
            }
        });

    }

    private void doCalculus(){

            initialValue = Double.parseDouble(this.editTextInitialValue.getText().toString());
            double monthlyContribution = Double.parseDouble(this.editTextMonthlyContribution.getText().toString());
            double monthlyProfitability = (Double.parseDouble((this.editTextMonthlyProfitability.getText().toString()))) / 100;
            int periodInMonths = seekBarYearPeriod.getProgress() * 12;

            UserInputData userInputData = new UserInputData(initialValue, monthlyContribution, monthlyProfitability, periodInMonths, toggleButtonDividends.isChecked(), monthlyDividendsPercent);

            dataObject = new CalculationUtil().calculateProfitability(userInputData);
        }


    private void setTextViewsValues(CalculusDataObject dataObject){
        double totalValue = dataObject.getTotalValue();
        String totalValueString = new FormatUtil().formatWithoutDecimal(totalValue);
        String totalDividendsString = new FormatUtil().formatWithoutDecimal(dataObject.getMonthlyDividends());

        if (totalValue >= 1000000000000.0){
            this.textViewTotalValue.setText("um trilhão +");
        } else {
            this.textViewTotalValue.setText(totalValueString);
        }

        this.textViewMonthlyDividends.setText("Dividendos mensais: R$" + totalDividendsString);
    }

    // Método de validação dos campos de entrada de dados
    // Define a variável status como true se todos os campos de inserção de dados forem preenchidos, do contrário, define como false.

    private void validation() {
        ValidationUtil validationUtil = new ValidationUtil(editTextInitialValue, editTextMonthlyContribution, editTextMonthlyProfitability, this.getApplicationContext());
        validationStatus = validationUtil.validation();
    }

    private void showInfo(){
        InfoDialog infoDialog = new InfoDialog();
        Bundle args = new Bundle();
        args.putDouble("monthlyDividends", monthlyDividends);

        infoDialog.setArguments(args);
        infoDialog.show(getSupportFragmentManager(), "Informações");
    }

        // Método que encaminha para a activity de detalhes
        // Passa para a activity os valores de total investido, total ganho em júros, o total acumulado e se os dividendos mensais ultrapassam a contribuição mensal do usuário.
    public void moreDetails(View view) {
        Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);

        intent.putExtra("dataObject", this.dataObject);
        intent.putExtra("initialValue", initialValue);
        startActivity(intent);
    }

    @Override
    public void applyTexts(Double monthlyDividends) {
        this.monthlyDividends = monthlyDividends;
        this.monthlyDividendsPercent = monthlyDividends / 100;

    }
}
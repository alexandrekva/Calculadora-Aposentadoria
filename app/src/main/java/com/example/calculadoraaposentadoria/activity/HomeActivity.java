package com.example.calculadoraaposentadoria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculadoraaposentadoria.R;
import com.example.calculadoraaposentadoria.activity.models.MonthValueData;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {

    // Variáveis
    private SeekBar seekBarYear;
    private TextView monthlyDividends, finalAmount, years, stinksOrStonks, firstMillion;
    private EditText initialValue, monthlyContribution, monthlyProfitability;
    private Switch dividendsSwitch;
    private ImageView imageStinksOrStonks;
    private Button details;
    private int months = 480, dividendsOverContributionMonth = 0;
    private static final double MONTHLY_DIVIDENDS = 0.0030;
    private double finalValue, totalInvested, totalInterest;
    private boolean status = false;
    private List<MonthValueData> monthValueDataList = new ArrayList<MonthValueData>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        seekBarYear             = findViewById(R.id.seekBarYear);
        monthlyDividends        = findViewById(R.id.textViewMonthlyDividends);
        finalAmount             = findViewById(R.id.textViewFinalAmount);
        initialValue            = findViewById(R.id.inputInitialValue);
        monthlyContribution     = findViewById(R.id.inputMonthlyContribution);
        monthlyProfitability    = findViewById(R.id.inputMonthlyProfitability);
        dividendsSwitch         = findViewById(R.id.dividendsSwitch);
        years                   = findViewById(R.id.textViewYears);
        stinksOrStonks          = findViewById(R.id.textViewStinksOrStonks);
        imageStinksOrStonks     = findViewById(R.id.imageViewStinksOrStonks);
        firstMillion            = findViewById(R.id.textViewFirstMillion);
        details                 = findViewById(R.id.buttonMoreDetails);


        //Listener do switch de reinvestir dividendos
        // Verifica se os campos necessários foram preenchidos, com o método validation e se os dados estiverem preenchidos (status = true), chama o método do cálculo.
        dividendsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validation();
                if (status){
                    calculateFinalAmount();
                }
            }
        });

        // Listener da SeekBar
        // Chama o método validation quando começa o tracking do touch.
        // Se validados os campos, atribui ao TextView years o valor equivalente ao progresso da barra.
        // Atribui à variável months também o valor do progresso da barra, multiplicando o valor pela quantidade de meses em um ano.

        seekBarYear.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                years.setText(progress + " Anos");
                if (status) {
                    months = (progress * 12);
                    calculateFinalAmount();
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

    }

    // Método de validação dos campos de entrada de dados
    // Define a variável status como true se todos os campos de inserção de dados forem preenchidos, do contrário, define como false.
    public void validation() {
     if (initialValue == null || initialValue.getText().toString().isEmpty() ){
         Toast.makeText(this, "Insira um valor inicial válido", Toast.LENGTH_SHORT).show();
         status = false;
     } else if (monthlyContribution == null || monthlyContribution.getText().toString().isEmpty()){
         Toast.makeText(this, "Insira uma contribuição inicial válida", Toast.LENGTH_SHORT).show();
         status = false;
     } else if (monthlyProfitability == null || monthlyProfitability.getText().toString().isEmpty()){
         Toast.makeText(this, "Insira uma rentabilidade mensal válida", Toast.LENGTH_SHORT).show();
         status = false;
     } else{
         status = true;
     }
    }

    // Método que calcula o valor final obtido
    // Declara as variáveis necessárias e atribui seus valores.
    // Realiza o cálculo do valor total utilizando a estrutura condicional for.
    // Chama o método stinksOrStonks ao final para saber se o valor é maior que 1.000.000.

    public void calculateFinalAmount() {
        double initialValue = Double.parseDouble(this.initialValue.getText().toString());
        double monthlyContribution = Double.parseDouble(this.monthlyContribution.getText().toString());
        double monthlyProfitability = (Double.parseDouble(this.monthlyProfitability.getText().toString())) / 100;
        finalValue = initialValue;
        totalInvested = (monthlyContribution * months);
        totalInterest = 0;
        boolean firstMillion = false;
        boolean dividendsOverContribution = false;
        monthValueDataList = new ArrayList<MonthValueData>();

        // O loop do cálculo acontecerá até que a quantidade de meses desejados pelo usuário seja alcançada.
        // Durante cada repetição, será acrescido ao montante final o valor da contribuição mensal somado ao valor da rentabilidade mensal da carteira.
        for (int i = 1; i <= months; i++) {
            finalValue += monthlyContribution + (finalValue * monthlyProfitability);
            // Se o switch de dividendos estiver ativo, será acrescido ao valor final também os dividendos recebidos.
            if (dividendsSwitch.isChecked()){
                double monthlyInvested = (finalValue * MONTHLY_DIVIDENDS);
                finalValue += monthlyInvested;
                totalInvested += monthlyInvested;
            }
            // Se o valor na repetição for maior que um milhão pela primeira vez, atribui o texto de parabéns, e identifica em que mês ele foi alcançado.
            if (finalValue > 1000000 && firstMillion == false) {
                this.firstMillion.setText("Parabéns! Primeiro milhão alcançado no mês " + i + "!");
                this.firstMillion.setVisibility(View.VISIBLE);
                firstMillion = true;
            } else if (firstMillion == false) {
                this.firstMillion.setVisibility(View.INVISIBLE);
            }

            // Verifica se o valor dos dividendos recebidos é superior ao valor investido mensalmente, bem como em que mês isso foi alcançado, se alcançado.
            if ((finalValue * MONTHLY_DIVIDENDS) >= monthlyContribution && dividendsOverContribution == false){
                dividendsOverContributionMonth = i;
                dividendsOverContribution = true;
            }
            if (i%12 == 0)
            monthValueDataList.add(new MonthValueData(finalValue, i/12));

        }

        totalInterest = finalValue - totalInvested;


        DecimalFormat decimalFormat = new DecimalFormat("#,#00.00");
        double monthlyDividendsFinal = (finalValue * MONTHLY_DIVIDENDS);

        finalAmount.setText("R$ " + decimalFormat.format(finalValue));
        monthlyDividends.setText("R$ " + decimalFormat.format(monthlyDividendsFinal));

        //Reseta a variável do mês em que os dividendos foram ultrapassados, se o feito não for alcançado.
        if ((finalValue * MONTHLY_DIVIDENDS) <= monthlyContribution){
            dividendsOverContributionMonth = 0;
        }

        stinksOrStonks();
    }

        // Método que verifica se o valor total obtido é maior que um milhão
        // Se o valor for superior a um milhão, define o texto para STONKS, sua cor como verde e a imagem ao lado com uma seta de gráfico positiva.
        // Do contrário, define o texto para STINKS, sua cor como vermelho, e a imagem como uma seta de gráfico negativa.
        public void stinksOrStonks(){
        if (finalValue > 1000000){
            stinksOrStonks.setText("STONKS!");
            stinksOrStonks.setTextColor(getResources().getColor(R.color.stonksColor));

            imageStinksOrStonks.setImageDrawable(getResources().getDrawable(R.drawable.ic_stonks));
        } else {
            stinksOrStonks.setText("STINKS!");
            stinksOrStonks.setTextColor(getResources().getColor(R.color.stinksColor));

            imageStinksOrStonks.setImageDrawable(getResources().getDrawable(R.drawable.ic_stinks));
        }

        stinksOrStonks.setVisibility(View.VISIBLE);
        imageStinksOrStonks.setVisibility(View.VISIBLE);
        }

        // Método que encaminha para a activity de detalhes
        // Passa para a activity os valores de total investido, total ganho em júros, o total acumulado e se os dividendos mensais ultrapassam a contribuição mensal do usuário.
        public void moreDetails(View view){
            Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
            intent.putExtra("totalInvested", totalInvested);
            intent.putExtra("totalInterest", totalInterest);
            intent.putExtra("total", finalValue);
            intent.putExtra("dividendsOverContribution", dividendsOverContributionMonth);
            intent.putExtra("list", (Serializable) monthValueDataList);
            startActivity(intent);
        }


    }
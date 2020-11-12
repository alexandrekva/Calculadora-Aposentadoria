package com.akva.calculadoraaposentadoria.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.akva.calculadoraaposentadoria.R;

public class InfoDialog extends AppCompatDialogFragment {

    private EditText editTextMonthlyDividends;
    private ExampleDialogListener listener;
    private Double monthlyDividends;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        monthlyDividends = getArguments().getDouble("monthlyDividends");

        editTextMonthlyDividends = view.findViewById(R.id.editTextMonthlyDividends);
        editTextMonthlyDividends.setText(String.valueOf(monthlyDividends));


        builder.setView(view)
                .setTitle("Informações")
                .setCancelable(true)
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Double monthlyDividends = Double.parseDouble(editTextMonthlyDividends.getText().toString());
                        listener.applyTexts(monthlyDividends);
                    }
                });



        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Deve implementar ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener {
        void applyTexts(Double monthlyDividends);
    }

}

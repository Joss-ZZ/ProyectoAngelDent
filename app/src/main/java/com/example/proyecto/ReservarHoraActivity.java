package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.Calendar;

public class ReservarHoraActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner jSpinner;
    EditText jtxtEdit;
    private int smes, sdia, saño, nmes, ndia, naño;
    static final int DATE_ID = 0;
    Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar_hora);
        jSpinner = findViewById(R.id.txtSpinner);
        jtxtEdit = findViewById(R.id.txtEdit);
        smes = c.get(Calendar.MONTH);
        saño = c.get(Calendar.YEAR);
        sdia = c.get(Calendar.DAY_OF_MONTH);
        jtxtEdit.setOnClickListener(this);
        String[] opciones = {"", "Mañana", "Tarde"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        jSpinner.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtEdit:
                fecha();
        }

    }

    private void fecha() {
       showDialog(DATE_ID);

    }

    private void colocar_fecha()

    {
        jtxtEdit.setText((nmes + 1) + "-" + ndia + "-" + naño + " ");
    }


    private DatePickerDialog.OnDateSetListener nDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month , int day) {
naño=year;
ndia=day;
nmes=month;
colocar_fecha();
        }
    };

protected Dialog onCreateDialog (int id){
    switch (id){
        case DATE_ID:
            return new DatePickerDialog(this,nDateSetListener,saño,smes,sdia);
    }

    return null;
}

}
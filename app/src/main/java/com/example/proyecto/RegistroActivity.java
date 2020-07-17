package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener{

    EditText jtxtUsuario, jtxtClave;

    Button jbtnContinuar , jbtnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        jbtnContinuar = findViewById(R.id.btnContinuar);
        jbtnAtras = findViewById(R.id.btnAtras);

        jbtnContinuar.setOnClickListener(this);
        jbtnAtras.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){


            case R.id.btnContinuar:
                Continuar();
                break;


            case R.id.btnAtras:
                Atras();
                break;
        }

    }



    private void Continuar() {
        Intent iRegistro = new Intent(this, Registro2Activity.class);
        startActivity(iRegistro);

    }

    private void Atras() {
        Intent iLogin = new Intent(this, LoginActivity.class);
        startActivity(iLogin);
    }
}
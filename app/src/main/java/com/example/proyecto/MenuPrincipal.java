package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuPrincipal extends AppCompatActivity implements View.OnClickListener{
    ImageButton jibubicacion, jibnosotros;
    Button jbtnCerrarSesion;

    private SharedPreferences preferences2;
    private SharedPreferences.Editor editor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        jibubicacion = findViewById(R.id.Ubicacion);
        jibnosotros = findViewById(R.id.Nosotros);
        jbtnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        jibnosotros.setOnClickListener(this);
        jibubicacion.setOnClickListener(this);
        jbtnCerrarSesion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Nosotros :
                nosotros();
                break;
            case R.id.Ubicacion :
                ubicacion();
                break;
            case R.id.btnCerrarSesion :
                CerrarSesion();
                break;
        }

    }

    private void CerrarSesion() {
        preferences2 = getSharedPreferences("Usuario", MODE_PRIVATE);
        editor2 = preferences2.edit();
        editor2.clear();
        editor2.commit();
        Intent iLogin = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(iLogin);
        finish();
    }

    private void nosotros() {
        Intent intent = new Intent(this,NosotrosActivity.class);
        startActivity(intent);
    }

    private void ubicacion() {
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }
}
package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuPrincipal extends AppCompatActivity implements View.OnClickListener{
    ImageButton jibubicacion, jibnosotros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        jibubicacion = findViewById(R.id.Ubicacion);
        jibnosotros = findViewById(R.id.Nosotros);

        jibnosotros.setOnClickListener(this);
        jibubicacion.setOnClickListener(this);

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
        }

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
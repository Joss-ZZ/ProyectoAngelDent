package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

public class RegistrarCita extends AppCompatActivity {
    private HorarioDentista dentista;

    TextView jlblItemDatoPersonal, jlblItemCorreoPersonal;
    ImageView jivItemFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cita);

        dentista = (HorarioDentista) getIntent().getSerializableExtra("datosDentista");

        jlblItemDatoPersonal = findViewById(R.id.lblItemDatoPersonal);
        jlblItemCorreoPersonal = findViewById(R.id.lblItemCorreoPersonal);
        jivItemFoto = findViewById(R.id.ivItemFoto);

        jlblItemDatoPersonal.setText(dentista.getNombres()+", "+dentista.getApellidos());
        jlblItemCorreoPersonal.setText(dentista.getCorreo());
        String sFoto = dentista.getFoto();
        byte[] bFoto = Base64.decode(sFoto, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(bFoto,0, bFoto.length);
        jivItemFoto.setImageBitmap(decodedImage);
    }
}
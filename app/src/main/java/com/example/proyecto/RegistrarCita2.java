package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import cz.msebera.android.httpclient.Header;

public class RegistrarCita2 extends AppCompatActivity {
    private HorarioDentista dentista;
    private String turno, fecha;

    TextView jlblDoctor, jlblFechaSeleccionada, jlblHora, jlblEstado;
    Button jbtnRegistrarCita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cita2);

        jlblDoctor = findViewById(R.id.lblDoctor);
        jlblFechaSeleccionada = findViewById(R.id.lblFechaSeleccionada);
        jlblHora = findViewById(R.id.lblHora);
        jlblEstado = findViewById(R.id.lblEstado);
        jbtnRegistrarCita = findViewById(R.id.btnRegistrarCita);

        dentista = (HorarioDentista) getIntent().getSerializableExtra("datosDentista");
        turno = this.getIntent().getStringExtra("turno");
        fecha = this.getIntent().getStringExtra("fecha");
        
        MostrarEstado(dentista.getId_dentista(), dentista.getId_dia(), dentista.getId_hora(), fecha);
        jlblDoctor.setText(dentista.getNombres()+", "+dentista.getApellidos());
        jlblFechaSeleccionada.setText(fecha);
        jlblHora.setText(dentista.getHora_inicio()+" a "+dentista.getHora_fin());
    }

    private void MostrarEstado(int id_dentista, int id_dia, int id_hora, String fecha) {
        AsyncHttpClient consultarHora = new AsyncHttpClient();

        String sURL ="http://camilodc.site/ConsultarDispinibilidadHora.php";

        RequestParams params = new RequestParams();
        params.add("id_dentista", ""+id_dentista);
        params.add("id_dia", ""+id_dia);
        params.add("id_hora", ""+id_hora);
        params.add("fecha", fecha);

        consultarHora.post(sURL, params, new BaseJsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response) {
                if(statusCode == 200){
                    try {
                        JSONArray jsonArray = new JSONArray(rawJsonResponse);
                        if(jsonArray.length() > 0){
                            String estado = jsonArray.getJSONObject(0).getString("estado");
                            if(estado.equals("0")){
                                jlblEstado.setText(Html.fromHtml("<font color='#33691E'>Disponible</font>"));
                            }else{
                                jlblEstado.setText(Html.fromHtml("<font color='#EE0000'>Reservado</font>"));
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, Object errorResponse) {

            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return null;
            }
        });
    }
}
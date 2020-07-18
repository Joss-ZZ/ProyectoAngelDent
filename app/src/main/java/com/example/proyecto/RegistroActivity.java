package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener{

    EditText jtxtCorreo, jtxtUsuario, jtxtClave, jtxtNombre, jtxtApellido, jtxtTelefono, jtxtDireccion;

    Button jbtnContinuar , jbtnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        jtxtCorreo = findViewById(R.id.txtCorreo);
        jtxtUsuario = findViewById(R.id.txtUsuario);
        jtxtClave = findViewById(R.id.txtClave);
        jtxtNombre = findViewById(R.id.txtNombre);
        jtxtApellido = findViewById(R.id.txtApellido);
        jtxtTelefono = findViewById(R.id.txtTelefono);
        jtxtDireccion = findViewById(R.id.txtDireccion);
        jbtnContinuar = findViewById(R.id.btnContinuar);
        jbtnAtras = findViewById(R.id.btnAtras);

        jbtnContinuar.setOnClickListener(this);
        jbtnAtras.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnContinuar:
                Registrar();
                break;
            case R.id.btnAtras:
                Atras();
                break;
        }

    }

    private void Registrar() {
        //Validaciones antes de registrar
        boolean validacion = Campos();
        boolean validarCorreo = false;
        boolean LongitudTelf = false;
        if(validacion){
            validarCorreo = validarEmail(jtxtCorreo.getText().toString().trim());
            if(validarCorreo){
                LongitudTelf = TelefonoLongitud();
            }else{
                jtxtCorreo.setError("Ingrese un email válido.");
            }
        }
        if(validarCorreo==true && validacion==true && LongitudTelf==true){
            AsyncHttpClient ahcRegistrar = new AsyncHttpClient();
            String sUrl = "http://camilodc.site/pacientes.php";
            //llenar parametros
            RequestParams params = new RequestParams();
            params.add("usuario", jtxtUsuario.getText().toString().trim());
            params.add("contrasena", jtxtClave.getText().toString().trim());
            params.add("nombre", jtxtNombre.getText().toString().trim());
            params.add("apellido", jtxtApellido.getText().toString().trim());
            params.add("telefono", jtxtTelefono.getText().toString().trim());
            params.add("email",jtxtCorreo.getText().toString().trim());
            params.add("direccion", jtxtDireccion.getText().toString().trim());

            ahcRegistrar.post(sUrl, params, new BaseJsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response) {
                    if(statusCode == 200){
                        int iRetVal = (rawJsonResponse.length() == 0 ? 0 : Integer.parseInt(rawJsonResponse));
                        if(iRetVal == 1){
                            Toast.makeText(getApplicationContext(), "Registro Agregado con éxito!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, Object errorResponse) {
                    Toast.makeText(getApplicationContext(), "Error al registrar.", Toast.LENGTH_SHORT).show();
                }

                @Override
                protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                    return null;
                }
            });
        }
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    private boolean TelefonoLongitud() {
        String telefono = jtxtTelefono.getText().toString().trim();
        boolean validacion=true;
        if(telefono.length()<7 || telefono.length()>9){
            validacion=false;
            jtxtTelefono.setError("El teléfono debe tener entre 7 a 9 caracteres.");
        }
        return validacion;
    }

    private boolean Campos() {
        boolean validacion=true;
        if(jtxtCorreo.getText().toString().trim().isEmpty()){validacion=false;jtxtCorreo.setError("El campo no puede estar vacío");}
        if(jtxtUsuario.getText().toString().trim().isEmpty()){validacion=false;jtxtUsuario.setError("El campo no puede estar vacío");}
        if(jtxtClave.getText().toString().trim().isEmpty()){validacion=false;jtxtClave.setError("El campo no puede estar vacío");}
        if(jtxtNombre.getText().toString().trim().isEmpty()){validacion=false;jtxtNombre.setError("El campo no puede estar vacío");}
        if(jtxtApellido.getText().toString().trim().isEmpty()){validacion=false;jtxtApellido.setError("El campo no puede estar vacío");}
        if(jtxtTelefono.getText().toString().trim().isEmpty()){validacion=false;jtxtTelefono.setError("El campo no puede estar vacío");}
        if(jtxtDireccion.getText().toString().trim().isEmpty()){validacion=false;jtxtDireccion.setError("El campo no puede estar vacío");}
        return  validacion;
    }
    private void Atras() {
        Intent iLogin = new Intent(this, LoginActivity.class);
        startActivity(iLogin);
    }
}
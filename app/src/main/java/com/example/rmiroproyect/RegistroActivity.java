package com.example.rmiroproyect;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistroActivity extends AppCompatActivity {

    EditText Usuario;
    EditText password;
    EditText Correo;
    Button btnRegistrar;
    private RequestQueue cartero;
    private VolleyS mVolleyS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mVolleyS = VolleyS.getInstance(this.getApplicationContext());
        cartero = mVolleyS.getRequestQueue();

        Usuario = (EditText) findViewById(R.id.txtRegUsuario);
        password = (EditText) findViewById(R.id.txtRegContra);
        Correo = (EditText) findViewById(R.id.txtRegCorreo);
        btnRegistrar = (Button) findViewById(R.id.btnRegRegistro);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://192.168.1.68:8000/api/registro";

                JSONObject datos = new JSONObject();
                try {
                    datos.put("name", Usuario.getText());
                    datos.put("email", Correo.getText());
                    datos.put("password", password.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, datos, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Request", response.toString());

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                cartero.add(request);

                Toast.makeText(getApplicationContext(), "Usuario registrado.", Toast.LENGTH_LONG).show();

                Usuario.setText("");
                password.setText("");
                Correo.setText("");
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

    }

}

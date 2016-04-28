package com.example.usuario.redsports;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

public class Login extends AppCompatActivity {
    private EditText etUser, etPass;
    private Button btnOk;
    private String IP = "http://webservicesports.esy.es";
    private String LOGIN = IP + "/auth.php";
    private String usuario, contraseña;
    private TextView tvTitulo, tvRegistrarse;
    private SmoothProgressBar barra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser = (EditText)findViewById(R.id.etUser);
        etPass = (EditText)findViewById(R.id.etContrasena);
        tvTitulo = (TextView)findViewById(R.id.textView);
        btnOk = (Button) findViewById(R.id.btnOk);
        tvRegistrarse = (TextView)findViewById(R.id.tvRegistrarse);
        barra  =(SmoothProgressBar)findViewById(R.id.progressbar);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/RockSalt.ttf");
        tvTitulo.setTypeface(tf);

        tvRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this,"pulsado",Toast.LENGTH_SHORT).show();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Para comprobar si ha rellenado los 2 campos
                boolean rellenado = false;

                if(etUser.getText().length() > 0) { //Comprobar que haya escrito un nombre de usuario
                    Log.v("etuser: ",etUser.getText().length()+"");
                    Log.v("etuser",etUser.getText().toString());
                    usuario = etUser.getText().toString().trim();

                    if(etPass.getText().length() > 0) { //Comprobar que haya escrito una contraseña
                        contraseña = etPass.getText().toString().trim();
                        rellenado = true;
                    }else{
                        etPass.setError("Escriba su contraseña");
                        etUser.setError(null);
                    }
                }else{
                    etUser.setError("Inserte un nombre de usuario");
                    etPass.setError(null);
                }

                if(rellenado) {
                    LoginTask login = new LoginTask();
                    login.execute(LOGIN + "?usuario=" + usuario, contraseña);
                    barra.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public class LoginTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];
            String contraseña = params[1];
            URL url = null; // Url de donde queremos obtener información
            String devuelve = "";

            try {
                url = new URL(cadena);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                        " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
                //connection.setHeader("content-type", "application/json");

                int respuesta = connection.getResponseCode();
                StringBuilder result = new StringBuilder();

                if (respuesta == HttpURLConnection.HTTP_OK){


                    InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada

                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader

                    // El siguiente proceso lo hago porque el JSONOBject necesita un String y tengo
                    // que tranformar el BufferedReader a String. Esto lo hago a traves de un
                    // StringBuilder.

                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);        // Paso toda la entrada al StringBuilder
                    }
                    //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                    JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                    //Accedemos al vector de resultados

                    String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON

                    if (resultJSON.equals("1")){      // hay un alumno que mostrar
                        devuelve = respuestaJSON.getJSONObject("mensaje").getString("contrasena");
                        if(devuelve.equals(contraseña)){
                            return "Logueado";
                        }else{
                            return "Contraseña equivocada";
                        }

                    }
                    else if (resultJSON.equals("2")){
                        return "El usuario no existe";
                    }

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return devuelve;
        }

        @Override
        protected void onPostExecute(String s) {
            barra.setVisibility(View.INVISIBLE);
            if(s.equals("Logueado")){
                Toast.makeText(Login.this,"jeje",Toast.LENGTH_SHORT).show();
            }else {
                Snackbar.make(btnOk, s, Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}
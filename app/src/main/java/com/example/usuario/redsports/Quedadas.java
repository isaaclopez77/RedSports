package com.example.usuario.redsports;

import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.json.JSONArray;
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
import java.util.ArrayList;

public class Quedadas extends AppCompatActivity {

    private String IP = "http://webservicesports.esy.es/obtener_deportes.php";
    private ArrayList<String> array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quedadas);

        array.add("uno");
        array.add("dos");
        array.add("tres");
        array.add("ucuet");
        array.add("cinco");

        new getDeportesTask().execute(IP);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarQuedadas);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        assert viewPager != null;
        //hay que recoger los datos antes de hacer esto
        viewPager.setAdapter(new AdaptadorPestañas(getSupportFragmentManager(),array ));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.appbartabs);
        assert tabLayout != null;
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
    }

    public class getDeportesTask extends AsyncTask<String,Void,ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... params) {

            String cadena = params[0];
            URL url = null; // Url de donde queremos obtener información
            String devuelve = "";
            JSONArray jArray = null;

            try {
                url = new URL(cadena);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                        " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
                //connection.setHeader("content-type", "application/json");

                int respuesta = connection.getResponseCode();
                StringBuilder result = new StringBuilder();

                if (respuesta == HttpURLConnection.HTTP_OK) {


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

                    if (resultJSON.equals("1")) {      // hay un alumno que mostrar
                        jArray = respuestaJSON.getJSONArray("deportes");
                        ArrayList<String> arrayDeportes = new ArrayList<>();
                        for(int i = 0; i<jArray.length();i++){
                            JSONObject json_respuesta = jArray.getJSONObject(i);
                            arrayDeportes.add(json_respuesta.getString("nombre"));
                        }

                        return arrayDeportes;
                    } else if (resultJSON.equals("2")) {
                        return null;
                    }

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            super.onPostExecute(strings);
            if(strings!=null){
                Log.v("deportes",strings.toString());
            }else{

            }
        }
    }

}

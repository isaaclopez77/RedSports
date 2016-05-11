package com.example.usuario.redsports;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class AltaEncuentro extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_encuentro);
        textView=  (TextView)findViewById(R.id.tvId);
        Bundle e = getIntent().getExtras();
        if(e != null){
            int id = e.getInt("deporte_id");
            Log.v("deporte_id despues",":" + id);
            textView.setText("Id deporte: " + id);
        }else{

        }

    }
}

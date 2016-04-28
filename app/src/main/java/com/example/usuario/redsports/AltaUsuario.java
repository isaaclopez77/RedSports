package com.example.usuario.redsports;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by USUARIO on 28/04/2016.
 */
public class AltaUsuario extends AppCompatActivity{
    private TextView tvTitulo;
    private EditText etUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altausuario);

        etUser = (EditText)findViewById(R.id.etUserN);
        etUser.setCompoundDrawables(ContextCompat.getDrawable(getApplicationContext(),R.drawable.redsport_icon),null,null,null);
        tvTitulo = (TextView)findViewById(R.id.tvTitulo);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/RockSalt.ttf");
        tvTitulo.setTypeface(tf);
    }
}

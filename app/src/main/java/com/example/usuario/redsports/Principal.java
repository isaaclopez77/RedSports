package com.example.usuario.redsports;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private String usuario, contraseña;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private RelativeLayout rl;
    private TextView tvTitulo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        tvTitulo = (TextView) findViewById(R.id.tvRedSports);
        //Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/RockSalt.ttf");
        //tvTitulo.setTypeface(tf);

        //comprobar si esta logueado y obtener usuario si lo está
        prefs = getSharedPreferences("login_preferences", Context.MODE_PRIVATE);
        editor = prefs.edit();
        usuario = prefs.getString("username","");
        contraseña = prefs.getString("contrasena","");
        Log.v("estado","username " + usuario);
        Log.v("estado","contraseña " + contraseña);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);


    }

    public void cerrar(View v){
        editor.clear();
        editor.commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        /*int id = item.getItemId();
        Intent intent = new Intent(getApplicationContext(), Grafico.class);
        if (id == R.id.llamadas) {
            intent.putExtra("opcion", "llamadas");
            startActivity(intent);
        } else if (id == R.id.salientes) {
            intent.putExtra("opcion", "salientes");
            startActivity(intent);
        } else if (id == R.id.entrantes) {
            intent.putExtra("opcion", "entrantes");
            startActivity(intent);
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

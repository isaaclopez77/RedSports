package com.example.usuario.redsports;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.design.widget.NavigationView;
import android.support.v4.content.IntentCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.usuario.redsports.fragments.Fragment1;
import com.example.usuario.redsports.splash.SplashScreen;

public class Principal extends AppCompatActivity {
    private String usuario, contraseña;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private TextView tvNombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //comprobar si esta logueado y obtener usuario si lo está
        prefs = getSharedPreferences("login_preferences", Context.MODE_PRIVATE);
        editor = prefs.edit();
        usuario = prefs.getString("username", "");
        contraseña = prefs.getString("contrasena", "");
        if(usuario.equals("") || contraseña.equals("")){
            Intent i = new Intent(Principal.this,Login.class);
            startActivity(i);
            finish();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        tvNombre = (TextView) header.findViewById(R.id.tvNombre);
        tvNombre.setText(usuario);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                boolean fragmentTransaction = false;
                android.support.v4.app.Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.ndpartidos:
                        fragment = new Fragment1();
                        fragmentTransaction = true;
                        break;
                    case R.id.ndmapa:
                        //Mapa
                        break;
                    case R.id.ndLogout:
                        editor.clear();
                        editor.commit();
                        Intent i = new Intent(Principal.this,Login.class);
                        startActivity(i);
                        finish();
                        break;
                }

                if (fragmentTransaction) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                    item.setChecked(true);
                    getSupportActionBar().setTitle(item.getTitle());
                }
                drawer.closeDrawers();
                return true;
            }
        });
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

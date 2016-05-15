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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.usuario.redsports.POJO.Encuentro;
import com.example.usuario.redsports.fragments.AdaptadorEncuentros;
import com.example.usuario.redsports.splash.SplashScreenSports;
import com.github.siyamed.shapeimageview.CircularImageView;

import java.util.ArrayList;


public class Principal extends AppCompatActivity {
    private String usuario, contraseña;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private TextView tvNombre, tvprueba;
    private CircularImageView imgUser;
    private RecyclerView rv;
    private ArrayList<Encuentro> encuentros;

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
        //Ahora obtengo los encuentros de ese usuario
        Bundle extras = getIntent().getExtras();
        if(extras != null){ //Si está apuntado algún encuentro muestro la lista
            encuentros = extras.getParcelableArrayList("encuentros");

            rv = (RecyclerView)findViewById(R.id.RecyclerView);
            final AdaptadorEncuentros adaptador = new AdaptadorEncuentros(encuentros);
            adaptador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = rv.getChildAdapterPosition(v);
                    Encuentro item = encuentros.get(itemPosition);
                    Log.v("seleccionado",item.toString());

                    //Intent para ver ese encuentro
                }
            });
            rv.setAdapter(adaptador);

            rv.setLayoutManager(new LinearLayoutManager(Principal.this, LinearLayoutManager.VERTICAL,false));
        }else{
            TextView tv = (TextView)findViewById(R.id.tvMisEncuentros);
            tv.setVisibility(View.GONE);

            LinearLayout ly = (LinearLayout)findViewById(R.id.layout_linear);
            ly.setVisibility(View.VISIBLE);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView tvToolbar = (TextView)toolbar.findViewById(R.id.tvTituloToolbar);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/RockSalt.ttf");
        tvToolbar.setTypeface(tf);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        imgUser = (CircularImageView)header.findViewById(R.id.imgUser);
        //imgUser.setImageBitmap(); CAMBIAR IMAGEN USUARIO
        tvNombre = (TextView) header.findViewById(R.id.tvNombre);
        tvNombre.setText(usuario);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ndquedadas:
                        Intent e = new Intent(Principal.this,SplashScreenSports.class);
                        startActivity(e);
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

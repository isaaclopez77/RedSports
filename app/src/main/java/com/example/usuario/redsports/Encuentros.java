package com.example.usuario.redsports;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.usuario.redsports.POJO.Deporte;
import com.example.usuario.redsports.POJO.Encuentro;
import java.util.ArrayList;

public class Encuentros extends AppCompatActivity {

    private ArrayList<Deporte> deportes = new ArrayList<>();
    private ArrayList<Encuentro> encuentros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quedadas);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
             deportes = extras.getParcelableArrayList("deportes");
             encuentros = extras.getParcelableArrayList("encuentros");
        }else{
            Intent i = new Intent(this,Principal.class);
            startActivity(i);
            finish();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarQuedadas);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        assert viewPager != null;
        viewPager.setAdapter(new AdaptadorPestañas(getSupportFragmentManager(),deportes, encuentros));


        TabLayout tabLayout = (TabLayout) findViewById(R.id.appbartabs);
        assert tabLayout != null;
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
    }
}

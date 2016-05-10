package com.example.usuario.redsports.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.redsports.POJO.Encuentro;
import com.example.usuario.redsports.R;

import java.util.ArrayList;


public class FragmentEncuentros3 extends Fragment {

    private static ArrayList<Encuentro> encuentros;
    private RecyclerView recView;
    private FloatingActionButton fab;
    View viewFragment;

    public static FragmentEncuentros3 newInstance(ArrayList<Encuentro> e) {
        FragmentEncuentros3 fragment = new FragmentEncuentros3();
        encuentros = e;
        return fragment;
    }

    public FragmentEncuentros3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewFragment = inflater.inflate(R.layout.fragment_encuentros,container,false);
        fab = (FloatingActionButton)viewFragment.findViewById(R.id.floatingButton);
        recView = (RecyclerView)viewFragment.findViewById(R.id.RecView);
        Log.v("fragemtnooooo",encuentros.toString());

        final AdaptadorEncuentros adaptador = new AdaptadorEncuentros(encuentros);
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = recView.getChildAdapterPosition(v);
                Encuentro item = encuentros.get(itemPosition);
                Log.v("seleccionado",item.toString());

                //Intent para ver ese encuentro
            }
        });
        recView.setAdapter(adaptador);

        recView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

        return viewFragment;
    }


}

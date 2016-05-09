package com.example.usuario.redsports.fragments;


import android.os.Bundle;
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


public class FragmentEncuentros2 extends Fragment {

    private static ArrayList<Encuentro> encuentros;
    private RecyclerView recView;
    View viewFragment;

    public static FragmentEncuentros2 newInstance(ArrayList<Encuentro> e) {
        FragmentEncuentros2 fragment = new FragmentEncuentros2();
        encuentros = e;
        return fragment;
    }

    public FragmentEncuentros2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.fragment_encuentros,container,false);

        recView = (RecyclerView)viewFragment.findViewById(R.id.RecView);

        final AdaptadorEncuentros adaptador = new AdaptadorEncuentros(encuentros);
        Log.v("fragemtnooooo2",encuentros.toString());
        recView.setAdapter(adaptador);
        recView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        return viewFragment;
    }


}

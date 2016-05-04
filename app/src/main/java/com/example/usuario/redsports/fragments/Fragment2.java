package com.example.usuario.redsports.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.redsports.R;

/**
 * Created by USUARIO on 04/05/2016.
 */
public class Fragment2 extends Fragment{

    public Fragment2(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment2, container, false);
    }
}

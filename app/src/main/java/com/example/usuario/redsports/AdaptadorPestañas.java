package com.example.usuario.redsports;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.usuario.redsports.fragments.Fragment1;
import com.example.usuario.redsports.fragments.Fragment2;

import java.util.ArrayList;

public class AdaptadorPestañas extends FragmentPagerAdapter {
    //private String tabTitles[] = new String[] { "Tab Uno", "Tab Dos", "Tab Tres", "Tab Cuatro", "Tab Cinco", "Tab Seis"};
    private ArrayList<String> deportes;

    public AdaptadorPestañas(FragmentManager fm, ArrayList<String> deportes) {
        super(fm);
        this.deportes = deportes;
    }

    @Override
    public int getCount() {
        return deportes.size();
    }

    @Override
    public Fragment getItem(int position) {
        //Hacer en un solo fragmento todo

        Fragment f = null;

        switch(position) {
            case 0:
            case 2:
            case 4:
                f = Fragment1.newInstance();
                break;
            case 1:
            case 3:
            case 5:
                f = Fragment2.newInstance();
                break;
        }

        return f;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return deportes.get(position);
    }
}

package com.example.usuario.redsports;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.usuario.redsports.POJO.Deporte;
import com.example.usuario.redsports.POJO.Encuentro;
import com.example.usuario.redsports.fragments.Fragment1;

import java.util.ArrayList;

public class AdaptadorPestañas extends FragmentPagerAdapter {

    private ArrayList<Deporte> deportes;
    private ArrayList<Encuentro> encuentros;
    private Deporte deporte;
    private  ArrayList<Encuentro> encuentrosDeDeporte = new ArrayList<>();

    public AdaptadorPestañas(FragmentManager fm, ArrayList<Deporte> deportes, ArrayList<Encuentro> encuentros) {
        super(fm);
        this.deportes = deportes;
        this.encuentros = encuentros;
    }

    @Override
    public int getCount() {
        return deportes.size();
    }

    @Override
    public Fragment getItem(int position) {
        //Hacer en un solo fragmento todo

        Fragment f = null;

        //hay que pasarle los encuentros cuyo deporte_id sea el adecuado
        switch(position) {
            case 0:
                deporte = deportes.get(0);

                int id = deporte.getId();

                for(Encuentro e : encuentros){
                    if(e.esEsteEncuentroDeEsteDeporte(id)){
                        encuentrosDeDeporte.add(e);
                        Log.v("estado",e.toString());
                    }
                }

                f = Fragment1.newInstance();
                break;
            case 1:
                deporte = deportes.get(1);

                id = deporte.getId();

                for(Encuentro e : encuentros){
                    if(e.esEsteEncuentroDeEsteDeporte(id)){
                        encuentrosDeDeporte.add(e);
                        Log.v("estado",e.toString());
                    }
                }

                f = Fragment1.newInstance();
                break;
            case 2:
                deporte = deportes.get(2);
                f = Fragment1.newInstance();
                break;
            case 3:
                deporte = deportes.get(3);
                f = Fragment1.newInstance();
                break;
            case 4:
                deporte = deportes.get(4);
                f = Fragment1.newInstance();
                break;
            case 5:
                deporte = deportes.get(5);
                f = Fragment1.newInstance();
                break;
            case 6:
                deporte = deportes.get(6);
                f = Fragment1.newInstance();
                break;
            case 7:
                deporte = deportes.get(7);
                f = Fragment1.newInstance();
                break;
            case 8:
                deporte = deportes.get(8);
                f = Fragment1.newInstance();
                break;
            case 9:
                deporte = deportes.get(9);
                f = Fragment1.newInstance();
                break;

        }

        return f;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return deportes.get(position).getNombre();
    }
}

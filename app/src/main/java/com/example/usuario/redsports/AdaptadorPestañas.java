package com.example.usuario.redsports;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.usuario.redsports.POJO.Deporte;
import com.example.usuario.redsports.POJO.Encuentro;
import com.example.usuario.redsports.fragments.FragmentEncuentros1;
import com.example.usuario.redsports.fragments.FragmentEncuentros2;

import java.util.ArrayList;

public class AdaptadorPestañas extends FragmentPagerAdapter {

    private ArrayList<Deporte> deportes;
    private ArrayList<Encuentro> encuentros;
    private ArrayList<Encuentro> encuentrosPorId;

    public AdaptadorPestañas(FragmentManager fm, ArrayList<Deporte> deportes, ArrayList<Encuentro> encuentros) {
        super(fm);
        this.deportes = deportes;
        this.encuentros = encuentros;
        Log.v("def",encuentros.toString());
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
                ArrayList<Encuentro> e1 = getEncuentrosByDeporteId(deportes.get(0).getId());
                Log.v("deporte1",e1.toString());

                f = FragmentEncuentros1.newInstance(e1);
                break;
            case 1:
                ArrayList<Encuentro> e2 = getEncuentrosByDeporteId(deportes.get(1).getId());
                Log.v("deporte2",e2.toString());
                f = FragmentEncuentros2.newInstance(e2);
                break;
            case 2:
                encuentrosPorId = getEncuentrosByDeporteId(deportes.get(2).getId());
                Log.v("deporte3",encuentrosPorId.toString());
                f = FragmentEncuentros1.newInstance(encuentrosPorId);
                break;
            case 3:
                encuentrosPorId = getEncuentrosByDeporteId(deportes.get(3).getId());
                Log.v("deporte4",encuentrosPorId.toString());
                f = FragmentEncuentros1.newInstance(encuentrosPorId);
                break;
            case 4:
                encuentrosPorId = getEncuentrosByDeporteId(deportes.get(4).getId());
                Log.v("deporte5",encuentrosPorId.toString());
                f = FragmentEncuentros1.newInstance(encuentrosPorId);
                break;
            case 5:
                encuentrosPorId = getEncuentrosByDeporteId(deportes.get(5).getId());
                Log.v("deporte6",encuentrosPorId.toString());
                f = FragmentEncuentros1.newInstance(encuentrosPorId);
                break;
            case 6:
                encuentrosPorId = getEncuentrosByDeporteId(deportes.get(6).getId());
                Log.v("deporte7",encuentrosPorId.toString());
                f = FragmentEncuentros1.newInstance(encuentrosPorId);
                break;
            case 7:
                encuentrosPorId = getEncuentrosByDeporteId(deportes.get(7).getId());
                Log.v("deporte8",encuentrosPorId.toString());
                f = FragmentEncuentros1.newInstance(encuentrosPorId);
                break;
            case 8:
                encuentrosPorId = getEncuentrosByDeporteId(deportes.get(8).getId());
                Log.v("deporte9",encuentrosPorId.toString());
                f = FragmentEncuentros1.newInstance(encuentrosPorId);
                break;
            case 9:
                encuentrosPorId = getEncuentrosByDeporteId(deportes.get(9).getId());
                Log.v("deporte10",encuentrosPorId.toString());
                f = FragmentEncuentros1.newInstance(encuentrosPorId);
                break;
        }
        return f;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return deportes.get(position).getNombre();
    }

    private ArrayList<Encuentro> getEncuentrosByDeporteId(int deporte_id){ // le pasamos el id de un deporte y nos devulve un ArrayList con los encuentros asociados a ese deporte

        ArrayList<Encuentro> result = new ArrayList<>();

        for(Encuentro e : encuentros){
            if(e.esEsteEncuentroDeEsteDeporte(deporte_id))
                result.add(e);
        }
        return result;
    }
}

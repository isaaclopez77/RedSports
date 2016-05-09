package com.example.usuario.redsports.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.redsports.POJO.Encuentro;
import com.example.usuario.redsports.R;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by USUARIO on 09/05/2016.
 */
public class AdaptadorEncuentros extends RecyclerView.Adapter<AdaptadorEncuentros.EncuentrosViewHolder> implements View.OnClickListener {

    private View.OnClickListener listener;

    private ArrayList<Encuentro> datos;

    public AdaptadorEncuentros(ArrayList<Encuentro> datos) {
        this.datos = datos;
    }

    @Override
    public EncuentrosViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_encuentro, viewGroup, false);
        itemView.setOnClickListener(this);

        EncuentrosViewHolder tvh = new EncuentrosViewHolder(itemView);

        return tvh;
    }

    @Override
    public void onBindViewHolder(EncuentrosViewHolder viewHolder, int pos) {
        Encuentro item = datos.get(pos);

        viewHolder.bindTitular(item);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }


    /*************** VIEWHOLDER *********************/
    public static class EncuentrosViewHolder  extends RecyclerView.ViewHolder {

    private TextView tvDescripcion, tvFecha, tvCapacidad;

    public EncuentrosViewHolder(View itemView) {
        super(itemView);

        tvDescripcion = (TextView)itemView.findViewById(R.id.tvDesc);
        tvFecha = (TextView)itemView.findViewById(R.id.tvFecha);
        tvCapacidad = (TextView)itemView.findViewById(R.id.tvCapacidad);
    }

    public void bindTitular(Encuentro e) {
        String[] fechaHora = obtenerFechayHora(e);

        tvDescripcion.setText(e.getDescripcion());
        tvCapacidad.setText(e.getApuntados() + "/" + e.getCapacidad());
        tvFecha.setText(fechaHora[0] + " " + fechaHora[1]);
    }

        private String[] obtenerFechayHora(Encuentro e){ // Formatea la fecha y la hora en el formato adecuado para mostrarla
            String fechaa = e.getFecha();
            String horaa = e.getHora();

            Date fecha = Date.valueOf(fechaa);
            SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM");

            String[] result = new String[2];

            result[0] = dateFormat.format(fecha);
            result[1] = horaa.substring(0,horaa.length()-3);

            return result;
        }
}

}
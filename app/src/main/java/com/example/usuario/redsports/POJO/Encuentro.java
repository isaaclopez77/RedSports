package com.example.usuario.redsports.POJO;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by USUARIO on 08/05/2016.
 */
public class Encuentro implements Parcelable{
    private int id;
    private String descripcion;
    private int deporte_id;
    private int apuntados;
    private int capacidad;
    private String fecha;
    private String hora;
    private String lat;
    private String lon;

    public Encuentro(int id,String descripcion, int deporte_id,int apuntados, int capacidad, String fecha, String hora, String lat, String lon) {
        this.id = id;
        this.descripcion = descripcion;
        this.deporte_id = deporte_id;
        this.apuntados = apuntados;
        this.capacidad = capacidad;
        this.fecha = fecha;
        this.hora = hora;
        this.lat = lat;
        this.lon = lon;
    }

    public Encuentro() {
    }


    protected Encuentro(Parcel in) {
        id = in.readInt();
        descripcion = in.readString();
        deporte_id = in.readInt();
        apuntados = in.readInt();
        capacidad = in.readInt();
        fecha = in.readString();
        hora = in.readString();
        lat = in.readString();
        lon = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(descripcion);
        dest.writeInt(deporte_id);
        dest.writeInt(apuntados);
        dest.writeInt(capacidad);
        dest.writeString(fecha);
        dest.writeString(hora);
        dest.writeString(lat);
        dest.writeString(lon);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Encuentro> CREATOR = new Creator<Encuentro>() {
        @Override
        public Encuentro createFromParcel(Parcel in) {
            return new Encuentro(in);
        }

        @Override
        public Encuentro[] newArray(int size) {
            return new Encuentro[size];
        }
    };

    public boolean esEsteEncuentroDeEsteDeporte(int deporte_id){
        if(deporte_id == this.deporte_id){
            return true;
        }else{
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDeporte_id() {
        return deporte_id;
    }

    public void setDeporte_id(int deporte_id) {
        this.deporte_id = deporte_id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public int getApuntados() {
        return apuntados;
    }

    public void setApuntados(int apuntados) {
        this.apuntados = apuntados;
    }

    @Override
    public String toString() {
        return "Encuentro{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", deporte_id=" + deporte_id +
                ", apuntados=" + apuntados +
                ", capacidad=" + capacidad +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }
}

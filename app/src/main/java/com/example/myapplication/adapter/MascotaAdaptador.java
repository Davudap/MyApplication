package com.example.myapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.clases.Mascota;

import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Activity activity;
    private ArrayList<Mascota> mascotas;
    private int layoutID;

    public MascotaAdaptador(Activity activity, ArrayList<Mascota> mascotas, int layoutID) {
        this.activity = activity;
        this.mascotas = mascotas;
        this.layoutID = layoutID;
    }

    // Inflar el layout y los pasara al viewholder para que obtenga los Views


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(layoutID, parent,false);

        switch (layoutID){
            case R.layout.cardview_mascota:
                return new MascotaViewHolder(view);

            case R.layout.cardview_perfil_fotos:
                return new PerfilFotosViewHolder(view);
        }

        return null;
    }

    /*
    *                           |OnBindViewHolder|
    * -------------------------------------------------------------------------*/

    // Asocia cada elemento de la lista con cada View
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (layoutID){
            case R.layout.cardview_mascota:
                onBindViewHolder((MascotaViewHolder) holder, position);
                break;

            case R.layout.cardview_perfil_fotos:
                onBindViewHolder((PerfilFotosViewHolder) holder, position);
                break;
        }
    }

    /* Mascotas */
    public void onBindViewHolder(MascotaViewHolder holder, int position) {
        Mascota mascota = mascotas.get(position);

        holder.imgFoto.setImageResource(mascota.getFoto());
        holder.tvNombreCV.setText(mascota.getNombre());
        holder.tvRatingCV.setText(Integer.toString(mascota.getRating()));
    }


    /* Perfil */
    public void onBindViewHolder(PerfilFotosViewHolder holder, int position) {
        Mascota mascota = mascotas.get(position);

        holder.imgFoto.setImageResource(mascota.getFoto());
        holder.tvRatingCV.setText(Integer.toString(mascota.getRating()));

    }
    /*--------------------------------------------------------------------------*/
    // Elementos que contiene la lista de contactos
    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    /*
     *                           |VIEWS HOLDERS|
     * -------------------------------------------------------------------------*/


    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private TextView tvNombreCV;
        private TextView tvRatingCV;

        public MascotaViewHolder(View itemView) {
            super(itemView);

            imgFoto         = (ImageView)   itemView.findViewById(R.id.imgFoto);
            tvNombreCV      = (TextView)    itemView.findViewById(R.id.tvNombreCV);
            tvRatingCV      = (TextView)    itemView.findViewById(R.id.tvRatingCV);

        }
    }

    public static class PerfilFotosViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private TextView tvRatingCV;

        public PerfilFotosViewHolder(View itemView) {
            super(itemView);

            imgFoto         = (ImageView)   itemView.findViewById(R.id.imgFoto);
            tvRatingCV      = (TextView)    itemView.findViewById(R.id.tvRatingCV);

        }
    }
}

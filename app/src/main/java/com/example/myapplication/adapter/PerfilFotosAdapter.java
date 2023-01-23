package com.example.myapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.clases.Mascota;

import java.util.ArrayList;

public class PerfilFotosAdapter extends RecyclerView.Adapter<PerfilFotosAdapter.PerfilFotosViewHolder>{

    private Activity activity;
    private ArrayList<Mascota> mascotas;

    public PerfilFotosAdapter(Activity activity, ArrayList<Mascota> mascotas) {
        this.activity = activity;
        this.mascotas = mascotas;
    }

    // Inflar el layout y los pasara al viewholder para que obtenga los Views
    @Override
    public PerfilFotosAdapter.PerfilFotosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.cardview_perfil_fotos, parent,false);


        return new PerfilFotosAdapter.PerfilFotosViewHolder(view);
    }

    // Asocia cada elemento de la lista con cada View
    @Override
    public void onBindViewHolder(PerfilFotosAdapter.PerfilFotosViewHolder holder, int position) {
        Mascota mascota = mascotas.get(position);

        holder.imgFoto.setImageResource(mascota.getFoto());
        holder.tvRatingCV.setText(Integer.toString(mascota.getRating()));

    }

    // Elementos que contiene la lista de contactos
    @Override
    public int getItemCount() {
        return mascotas.size();
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

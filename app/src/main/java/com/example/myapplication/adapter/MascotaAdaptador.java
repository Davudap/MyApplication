package com.example.myapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.clases.Mascota;

import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder>{

    private Activity activity;
    private ArrayList<Mascota> mascotas;

    public MascotaAdaptador(Activity activity, ArrayList<Mascota> mascotas) {
        this.activity = activity;
        this.mascotas = mascotas;
    }

    // Inflar el layout y los pasara al viewholder para que obtenga los Views
    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.cardview_mascota, parent,false);


        return new MascotaViewHolder(view);
    }

    // Asocia cada elemento de la lista con cada View
    @Override
    public void onBindViewHolder(MascotaViewHolder holder, int position) {
        Mascota mascota = mascotas.get(position);

        holder.imgFoto.setImageResource(mascota.getFoto());
        holder.tvNombreCV.setText(mascota.getNombre());
        holder.tvRatingCV.setText(Integer.toString(mascota.getRating()));
        /*
        holder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, DetalleContacto.class);

                intent.putExtra("nombre", contacto.getNombre());
                intent.putExtra("telefono", contacto.getTelefono());
                intent.putExtra("mail", contacto.getMail());

                activity.startActivity(intent);
            }
        });*/
    }

    // Elementos que contiene la lista de contactos
    @Override
    public int getItemCount() {
        return mascotas.size();
    }

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
}

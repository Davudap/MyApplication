package com.example.myapplication.fragments;

import com.example.myapplication.adapter.MascotaAdaptador;
import com.example.myapplication.clases.Mascota;

import java.util.ArrayList;

public interface IRecyclerViewFragment {

    public void generarLinearLayoutVertical();
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> contactos);
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);

}

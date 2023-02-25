package com.example.myapplication.presentador;

import android.content.Context;

import com.example.myapplication.clases.Mascota;
import com.example.myapplication.database.ConstructorMascotas;
import com.example.myapplication.fragments.IRecyclerViewFragment;

import java.util.ArrayList;

public class RecyclerViewPresenter implements IRecyclerViewPresenter{

    private IRecyclerViewFragment iRecyclerViewFragment;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewPresenter(IRecyclerViewFragment iRecyclerViewFragment, Context context) {
        this.iRecyclerViewFragment = iRecyclerViewFragment;
        this.context = context;
    }

    @Override
    public void obtenerContactosBaseDeDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarContactosRV();
    }

    @Override
    public void mostrarContactosRV() {
        iRecyclerViewFragment.inicializarAdaptadorRV(iRecyclerViewFragment.crearAdaptador(mascotas));
        iRecyclerViewFragment.generarLinearLayoutVertical();
    }
}

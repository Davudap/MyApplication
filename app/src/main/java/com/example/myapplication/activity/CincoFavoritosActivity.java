package com.example.myapplication.activity;

import static java.lang.Integer.compare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.example.myapplication.clases.Mascota;
import com.example.myapplication.adapter.MascotaAdaptador;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Collections;


public class CincoFavoritosActivity extends AppCompatActivity {

    private ArrayList<Mascota> mascotas;
    private ArrayList<Mascota> favMascotas;

    private RecyclerView listaMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinco_favoritos);

        Toolbar toolbar = (Toolbar) findViewById(R.id.miActionBar);

        toolbar.removeAllViews();

        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mascotasFavoritos();


        listaMascotas = findViewById(R.id.rvMascotas);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(linearLayoutManager);

        inicializarAdaptador();

    }

    public void mascotasFavoritos(){


        Bundle parametros = getIntent().getBundleExtra(getResources().getString(R.string.blista));

        mascotas = (ArrayList<Mascota>) parametros.getSerializable(getResources().getString(R.string.plista));

        ordernarMascotas();

        favMascotas = new ArrayList<Mascota>();


        for(int i = 0; i < 5; i++)
            favMascotas.add(mascotas.get(i));

    }

    public void ordernarMascotas(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mascotas.sort(Collections.reverseOrder((mascota1, mascota2) -> compare(mascota1.getRating(), mascota2.getRating())));
        }

    }

    public void inicializarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(this, favMascotas);
        listaMascotas.setAdapter(adaptador);
    }

}
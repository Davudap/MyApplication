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
import com.example.myapplication.database.ConstructorMascotas;

import java.util.ArrayList;
import java.util.Collections;


public class CincoFavoritosActivity extends AppCompatActivity {

    private ArrayList<Mascota> favMascotas;

    private RecyclerView rvMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinco_favoritos);

       Toolbar toolbar = (Toolbar) findViewById(R.id.miActionBar);

        toolbar.removeAllViews();

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ultimasCincoMascotasFavoritas();

        rvMascotas = findViewById(R.id.rvMascotas);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rvMascotas.setLayoutManager(linearLayoutManager);

        inicializarAdaptador();

    }


    public void ultimasCincoMascotasFavoritas(){

  /*      favMascotas = new ArrayList<Mascota>();


        favMascotas.add(new Mascota(R.drawable.ic_dog, "Firulais", 1));
        favMascotas.add(new Mascota(R.drawable.ic_cat, "Michi", 5));
        favMascotas.add(new Mascota(R.drawable.ic_dog, "Firulais", 0));
        favMascotas.add(new Mascota(R.drawable.ic_cat, "Michi", 2));
        favMascotas.add(new Mascota(R.drawable.ic_pinguin, "Fabri", 1));*/

        ConstructorMascotas constructorMascotas = new ConstructorMascotas(this);

        favMascotas = constructorMascotas.topRating();

    }

    public void inicializarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(this, favMascotas, R.layout.cardview_mascota);
        rvMascotas.setAdapter(adaptador);
    }

}
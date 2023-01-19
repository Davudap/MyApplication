package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ArrayList<Mascota> mascotas;

    private RecyclerView listaMascotas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();;

        listaMascotas = findViewById(R.id.rvMascotas);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(linearLayoutManager);

        inicializarListaContactos();
        inicializarAdaptador();

        clickFiveFavorite();

    }


    public void clickFiveFavorite(){

        ImageButton ibFav = findViewById(R.id.ibFav);



        ibFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> strMascotas = new ArrayList<>();
                Bundle args = new Bundle();
                args.putSerializable(getResources().getString(R.string.plista),(Serializable) mascotas);

                Intent intent = new Intent(MainActivity.this, CincoFavoritos.class);

                intent.putExtra(getResources().getString(R.string.blista), args);

                startActivity(intent);
            }
        });
    }

    public void inicializarListaContactos(){

        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.ic_dog, "Firulais", 3));
        mascotas.add(new Mascota(R.drawable.ic_pinguin, "Fabri", 4));
        mascotas.add(new Mascota(R.drawable.ic_rabbit, "Bugs Bunny", 2));
        mascotas.add(new Mascota( R.drawable.ic_cat, "Michi", 4));
        mascotas.add(new Mascota(R.drawable.ic_rabbit, "Javier", 5));
        mascotas.add(new Mascota(R.drawable.ic_dog, "Funes", 2));

        ArrayList<String> nombresMascotas =new ArrayList<String>();
        for (Mascota mascota:mascotas) {
            nombresMascotas.add(mascota.getNombre());
        }
    }

    public void inicializarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(this, mascotas);
        listaMascotas.setAdapter(adaptador);
    }



}
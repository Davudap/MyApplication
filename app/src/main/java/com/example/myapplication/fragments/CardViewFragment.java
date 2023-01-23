package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.myapplication.R;
import com.example.myapplication.activity.CincoFavoritosActivity;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.adapter.MascotaAdaptador;
import com.example.myapplication.clases.Mascota;

import java.io.Serializable;
import java.util.ArrayList;


public class CardViewFragment extends Fragment {


    private ArrayList<Mascota> mascotas;

    private RecyclerView listaMascotas;

    public CardViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_card_view, container, false);


        listaMascotas = view.findViewById(R.id.rvMascotas);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(linearLayoutManager);

        inicializarListaContactos();
        inicializarAdaptador();

        clickFiveFavorite();

        return view;
    }

    public void clickFiveFavorite(){

        ImageButton ibFav = getActivity().findViewById(R.id.ibFav);

        ibFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> strMascotas = new ArrayList<>();
                Bundle args = new Bundle();
                args.putSerializable(getResources().getString(R.string.plista),(Serializable) mascotas);

                Intent intent = new Intent(getActivity(), CincoFavoritosActivity.class);

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

    }

    public void inicializarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(getActivity(), mascotas);
        listaMascotas.setAdapter(adaptador);
    }
}
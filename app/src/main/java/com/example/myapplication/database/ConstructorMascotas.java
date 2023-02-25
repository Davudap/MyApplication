package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;

import com.example.myapplication.R;
import com.example.myapplication.clases.Mascota;

import java.util.ArrayList;

public class ConstructorMascotas {

    private Context context;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos(){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerTodasLasFotos();
    }

    public void incializarMascotas(){
        BaseDatos db = new BaseDatos(context);
        crearMascotas(db);
    }

    public void crearMascotas(BaseDatos db){
        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.ic_dog, "Firulais", 3));
        mascotas.add(new Mascota(R.drawable.ic_pinguin, "Fabri", 4));
        mascotas.add(new Mascota(R.drawable.ic_rabbit, "Bugs Bunny", 2));
        mascotas.add(new Mascota(R.drawable.ic_cat, "Michi", 4));
        mascotas.add(new Mascota(R.drawable.ic_rabbit, "Javier", 5));
        mascotas.add(new Mascota(R.drawable.ic_dog, "Funes", 2));

        ContentValues contentValuesMascota = new ContentValues();
        ContentValues contentValuesFotos = new ContentValues();

        for (Mascota mascotaActual:mascotas) {
            contentValuesMascota.put(ConstantesDB.MASCOTA_NOMBRE, mascotaActual.getNombre());
            db.insertarMascotas(contentValuesMascota);
            contentValuesFotos.put(ConstantesDB.FOTO_IMAGEN, mascotaActual.getFoto());
            contentValuesFotos.put(ConstantesDB.FOTO_RATING, mascotaActual.getRating());
            contentValuesFotos.put(ConstantesDB.FOTO_MASCOTA_ID, db.obtenerUltimoID());
            db.insertarFotos(contentValuesFotos);
        }

        crearRating();

    }

    public ArrayList<Mascota> topRating(){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerUltimosRating();
    }

    public void crearRating(){

        BaseDatos db = new BaseDatos(context);



        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantesDB.RATING_FOTO_ID, 0);
        db.insertarRating(contentValues);

    }

}

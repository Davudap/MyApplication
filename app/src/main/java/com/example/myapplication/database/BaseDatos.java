package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.clases.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesDB.DATABASE_NAME, null, ConstantesDB.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesDB.TABLE_MASCOTA + " ( "
                + ConstantesDB.MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ConstantesDB.MASCOTA_NOMBRE + " TEXT "
                + " )";

        String queryCrearTablaFoto = "CREATE TABLE " + ConstantesDB.TABLE_FOTO + " ( "
                + ConstantesDB.FOTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ConstantesDB.FOTO_IMAGEN + " INTEGER, "
                + ConstantesDB.FOTO_RATING + " INTEGER, "
                + ConstantesDB.FOTO_MASCOTA_ID + " INTEGER, "


                + "FOREIGN KEY ( " + ConstantesDB.FOTO_MASCOTA_ID + " )"
                + "REFERENCES " + ConstantesDB.TABLE_MASCOTA + "( " + ConstantesDB.MASCOTA_ID + " )"
                + " )";

        String queryCrearRating = "CREATE TABLE " + ConstantesDB.TABLE_RATING + " ( "
                + ConstantesDB.RATING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ConstantesDB.RATING_FOTO_ID + " INTEGER, "

                + "FOREIGN KEY ( " + ConstantesDB.RATING_FOTO_ID + " )"
                + "REFERENCES " + ConstantesDB.TABLE_FOTO + "( " + ConstantesDB.FOTO_ID + " )"
                + " )";

        sqLiteDatabase.execSQL(queryCrearTablaMascota);
        sqLiteDatabase.execSQL(queryCrearTablaFoto);
        sqLiteDatabase.execSQL(queryCrearRating);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesDB.TABLE_MASCOTA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesDB.TABLE_FOTO);
        onCreate(sqLiteDatabase);
    }

    public void insertarMascotas(ContentValues contentValues){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.insert(ConstantesDB.TABLE_MASCOTA, null, contentValues);
        sqLiteDatabase.close();
    }

    public void insertarFotos(ContentValues contentValues){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.insert(ConstantesDB.TABLE_FOTO, null, contentValues);
        sqLiteDatabase.close();
    }

    public ArrayList<Mascota> obtenerTodasLasFotos(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesDB.TABLE_FOTO;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor registros = sqLiteDatabase.rawQuery(query, null);

        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();

            mascotaActual.setId(registros.getInt(3));

            Cursor registroMascota = obtenerCursorMascota(mascotaActual.getId());

            if(registroMascota.moveToNext())
                mascotaActual.setNombre(registroMascota.getString(1));;


            mascotaActual.setFoto(registros.getInt(1));
            mascotaActual.setRating(registros.getInt(2));

            mascotas.add(mascotaActual);
        }

        sqLiteDatabase.close();

        return mascotas;
    }

    public Cursor obtenerCursorMascota(int id){
        String query ="SELECT * " +
                " FROM " + ConstantesDB.TABLE_MASCOTA +
                " WHERE " + ConstantesDB.MASCOTA_ID + " = " + id;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        return sqLiteDatabase.rawQuery(query, null);

    }

    public int obtenerUltimoID(){
        int ultimoID;

        String query = "SELECT MAX ( " + ConstantesDB.MASCOTA_ID + " ) "
                + "FROM " + ConstantesDB.TABLE_MASCOTA;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor registro = sqLiteDatabase.rawQuery(query, null);

        if (registro.moveToNext())
            ultimoID = registro.getInt(0);
        else
            ultimoID = 0;

        sqLiteDatabase.close();

        return ultimoID;
    }

    public ArrayList<Mascota> obtenerUltimosRating(){

        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesDB.TABLE_RATING
                + " ORDER BY " + ConstantesDB.RATING_ID + " DESC"
                + " LIMIT 5; ";


        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor registro = sqLiteDatabase.rawQuery(query, null);

        while (registro.moveToNext()){
            Mascota mascotaActual = new Mascota();

            Cursor registroFoto = obtenerCursorFoto(registro.getInt(1));



            if(registroFoto.moveToNext()){
                mascotaActual.setFoto(registroFoto.getInt(1));
                mascotaActual.setRating(registroFoto.getInt(2));

                Cursor registroMascota = obtenerCursorMascota(registroFoto.getInt(3));


                if(registroMascota.moveToNext()){
                    mascotaActual.setId(registroMascota.getInt(0));
                    mascotaActual.setNombre(registroMascota.getString(1));
                }

            }
            mascotas.add(mascotaActual);
        }

        sqLiteDatabase.close();

        return mascotas;

    }

    public Cursor obtenerCursorFoto(int id){
        String query ="SELECT * " +
                " FROM " + ConstantesDB.TABLE_MASCOTA +
                " WHERE " + ConstantesDB.MASCOTA_ID + " = " + id;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        return sqLiteDatabase.rawQuery(query, null);
    }

    public void insertarRating(ContentValues contentValues){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.insert(ConstantesDB.TABLE_RATING, null, contentValues);
        sqLiteDatabase.close();
    }

}

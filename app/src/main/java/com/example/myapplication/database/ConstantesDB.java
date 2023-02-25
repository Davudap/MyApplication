package com.example.myapplication.database;

public final class ConstantesDB {

    public static final String DATABASE_NAME = "mascotas";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_MASCOTA = "mascota";
    public static final String MASCOTA_ID = "id";
    public static final String MASCOTA_NOMBRE = "nombre";

    public static final String TABLE_FOTO = "foto";
    public static final String FOTO_ID = "id";
    public static final String FOTO_IMAGEN = "imagen";
    public static final String FOTO_MASCOTA_ID = "mascota_id";
    public static final String FOTO_RATING = "rating";

    public static final String TABLE_RATING = "rating";
    public static final String RATING_ID = "id";
    public static final String RATING_FOTO_ID = "foto_id";


}

package com.example.jcapax.sqliteandroid;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by jcapax on 22/2/18.
 */

public class PersonaLogic {
    BaseHelper bh;

    public PersonaLogic(BaseHelper bh) {
        this.bh = bh;
    }

    public ArrayList<String> listaPersonas(){
        ArrayList<String> lPer = new ArrayList<>();

//        BaseHelper bh = new BaseHelper(this, "Base", null, 2);
        SQLiteDatabase db = bh.getReadableDatabase();
        String sql = "select id, nombre, edad from personas";
        try{
            Cursor c = db.rawQuery(sql, null);
            while(c.moveToNext()){
                Persona p = new Persona();
                p.setId(c.getInt(0));
                p.setNombre(c.getString(1));
                p.setEdad(c.getInt(2));

                lPer.add(p.getId()+" "+p.getNombre()+" "+p.getEdad());
            }
        }catch(Exception e){

        }
        return lPer;
    }

    public boolean UpdatePersona(int id){
        boolean aux = false;

        SQLiteDatabase db = bh.getWritableDatabase();
        try {
            String sql = "update personas set edad = 79 where id = " + String.valueOf(id);
            db.execSQL(sql);
            db.close();
            aux = true;
        }catch (Exception e){

        }

        return aux;
    }

    public boolean guardar(String nombre, int edad){
        boolean aux = false;

        SQLiteDatabase sqlData = bh.getWritableDatabase();
        try {
            ContentValues c = new ContentValues();
            c.put("nombre", nombre);
            c.put("edad", edad);
            sqlData.insert("personas", null, c);
            sqlData.close();

            aux = true;
        }catch (Exception e){

        }

        return aux;
    }

}

package com.example.jcapax.sqliteandroid;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by jcapax on 22/2/18.
 */

public class DireccionLogic {
    BaseHelper bh;

    public DireccionLogic(BaseHelper bh) {
        this.bh = bh;
    }

    public ArrayList<String> listaDireccion(String id){
        ArrayList<String> lDir = new ArrayList<>();

//        BaseHelper bh = new BaseHelper(this, "Base", null, 2);
        SQLiteDatabase db = bh.getReadableDatabase();
        String sql = "select id, nombre, calle from v_personadireccion where id = "+id;
        try{
            Cursor c = db.rawQuery(sql, null);
            while(c.moveToNext()){
                Direccion d = new Direccion();
                d.setId(c.getInt(0));
                d.setNombre(c.getString(1));
                d.setCalle(c.getString(2));

                lDir.add(d.getId()+" "+d.getNombre()+" "+d.getCalle());
            }
        }catch(Exception e){

        }
        return lDir;
    }
}

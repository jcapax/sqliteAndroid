package com.example.jcapax.sqliteandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by jcapax on 15/10/17.
 */

public class BaseHelper extends SQLiteOpenHelper {


    String sql_create_table = "CREATE TABLE personas(id integer primary key autoincrement, nombre text, edad int)";
    String sql_drop_table = "DROP TABLE personas";
    public BaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sql_create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(sql_drop_table);
        sqLiteDatabase.execSQL(sql_create_table);
    }

    public ArrayList<String> listaPersonas(){
        ArrayList<String> lPer = new ArrayList<>();

//        BaseHelper bh = new BaseHelper(this, "Base", null, 2);
        SQLiteDatabase db = this.getReadableDatabase();
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

        SQLiteDatabase db = this.getWritableDatabase();
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

        SQLiteDatabase sqlData = this.getWritableDatabase();
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

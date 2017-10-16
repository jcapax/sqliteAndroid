package com.example.jcapax.sqliteandroid;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Mostrar extends AppCompatActivity {

    ListView lvPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        lvPersonas = (ListView) findViewById(R.id.lvPersonas);
        cargarPersonas();
    }

//    private ArrayList<String> listaPersonas(){
//        ArrayList<String> lPer = new ArrayList<>();
//
//        BaseHelper bh = new BaseHelper(this, "Base", null, 2);
//        SQLiteDatabase db = bh.getReadableDatabase();
//        String sql = "select id, nombre, edad from personas";
//        try{
//            Cursor c = db.rawQuery(sql, null);
//            while(c.moveToNext()){
//                Persona p = new Persona();
//                p.setId(c.getInt(0));
//                p.setNombre(c.getString(1));
//                p.setEdad(c.getInt(2));
//
//                lPer.add(p.toString());
//            }
//        }catch(Exception e){
//
//        }
//        return lPer;
//    }

    public void cargarPersonas(){

        BaseHelper bh = new BaseHelper(this, "Base", null, 2);
        ArrayList<String> datos = bh.listaPersonas();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        lvPersonas.setAdapter(adapter);

    }
}

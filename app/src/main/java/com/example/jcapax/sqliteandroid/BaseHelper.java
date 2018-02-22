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


    String sql_create_table_persona = "CREATE TABLE personas(" +
            "id integer primary key autoincrement, " +
            "nombre text, " +
            "edad int)";

    String sql_drop_table_persona = "DROP TABLE personas";

    String sql_create_table_direccion = "CREATE TABLE direccion(" +
            "id integer primary key autoincrement, " +
            "idPersona integer, " +
            "calle text)";

    String sql_drop_table_direccion = "DROP TABLE direccion";

    String sql_create_view_persona_direccion = "" +
            "create view v_personadireccion as " +
            "select p.id, p.nombre, d.calle" +
            "from persona p join direccion d on " +
            "p.id = d.idPersona";
    String sql_drop_view_persona_direccion = "DROP VIEW v_personadireccion";

    public BaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sql_create_table_persona);
        sqLiteDatabase.execSQL(sql_create_table_direccion);

        sqLiteDatabase.execSQL(sql_create_view_persona_direccion);

        //sqLiteDatabase.execSQL("insert into direccion(idPersona, calle) values(1, "+"cabrera"+")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(sql_drop_table_persona);
        sqLiteDatabase.execSQL(sql_create_table_persona);

        sqLiteDatabase.execSQL(sql_drop_table_direccion);
        sqLiteDatabase.execSQL(sql_create_table_direccion);

        sqLiteDatabase.execSQL(sql_drop_view_persona_direccion);
        sqLiteDatabase.execSQL(sql_create_view_persona_direccion);
    }

}

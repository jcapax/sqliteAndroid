package com.example.jcapax.sqliteandroid;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Mostrar extends AppCompatActivity {

    ListView lvPersonas;
    BaseHelper bh;
    PersonaModel pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        bh = new BaseHelper(this, "Base", null, 2);
        pm = new PersonaModel(bh);

        lvPersonas = (ListView) findViewById(R.id.lvPersonas);
        ArrayList<String> datos = pm.listaPersonas();
        int nroRegistros = datos.size();
        if(nroRegistros > 0) {
            CustomAdapter customAdapter = new CustomAdapter(datos, nroRegistros);
            lvPersonas.setAdapter(customAdapter);
        }
    }


    class CustomAdapter extends BaseAdapter{

        ArrayList<String> datos;
        int nroRegistros;

        public CustomAdapter(ArrayList<String> datos, int nroRegistros){
            this.datos = datos;
            this.nroRegistros = nroRegistros;
        }

        @Override
        public int getCount() {
            return nroRegistros;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.custom_layout, null);

            TextView labelId = (TextView) view.findViewById(R.id.txtView_id);
            TextView labelEdad = (TextView) view.findViewById(R.id.txtView_edad);
            TextView labelDetalle = (TextView) view.findViewById(R.id.txtView_detalle);

            String aux = datos.get(i);

            String frag[] = datos.get(i).split(" ");

            labelId.setText(frag[0]);
            labelDetalle.setText(frag[1]);
            labelEdad.setText(frag[2]);

            return view;

        }
    }
}

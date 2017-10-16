package com.example.jcapax.sqliteandroid;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtNombre;
    EditText txtEdad;
    Button btnGuardar;
    Button btnMostrar;
    Button btnActualizar;
    BaseHelper bh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtEdad = (EditText) findViewById(R.id.txtEdad);

        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnMostrar = (Button) findViewById(R.id.btnMostrar);
        btnActualizar = (Button) findViewById(R.id.btnUpdate);

        bh = new BaseHelper(this, "Base", null, 2);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombre.getText().toString();
                Integer edad = Integer.valueOf(txtEdad.getText().toString());
                guardar(nombre, edad);
            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Mostrar.class));
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizar();
            }
        });

    }


    public void guardar(String nombre, int edad) {

        boolean aux = bh.guardar(nombre, edad);
        if(aux){
            txtNombre.setText("");
            txtEdad.setText("");
            Toast.makeText(this, "registro insertado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "erro en el registro", Toast.LENGTH_SHORT).show();
        }
    }

    public void actualizar(){
        boolean aux = bh.UpdatePersona(1);
        if(aux){
            Toast.makeText(this, "registro actualizado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "erro en la actualizacion", Toast.LENGTH_SHORT).show();
        }
    }
}

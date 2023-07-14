package com.example.basedatossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnInsertar;
    private Button btnEliminar;
    private Button btnModificar;
    private Button btnSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsertar = findViewById(R.id.btnInsertar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnModificar = findViewById(R.id.btnModificar);
        btnSelect = findViewById(R.id.btnSelect);

        eventos();

    }

    private void eventos() {
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarUsuario();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();

            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar();

            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultar();
            }
        });
    }

    private void consultar(){
        Log.d("TAG_", "Modificar");
        AdministradorBaseDatos adbd = new AdministradorBaseDatos(this, "BDPrueba", null, 1);
        SQLiteDatabase miBD = adbd.getWritableDatabase();

        Cursor cursor = miBD.rawQuery("Select * from usuarios order by apellido desc", null);

        if(cursor.moveToFirst()){
            do{
                Log.d("TAG_", "----------------------------");
                Log.d("TAG_", "Codigo " + cursor.getInt(0));
                Log.d("TAG_", "Nombre " + cursor.getString(1));
                Log.d("TAG_", "Apellido " + cursor.getString(2));
            }while(cursor.moveToNext());

        }

        cursor.close();
        miBD.close();
    }

    private void modificar(){
        Log.d("TAG_", "Modificar");
        AdministradorBaseDatos adbd = new AdministradorBaseDatos(this, "BDPrueba", null, 1);
        SQLiteDatabase miBD = adbd.getWritableDatabase();

        try{
            if (adbd != null) {
                ContentValues reg = new ContentValues();
                reg.put("nombre", "AAAAAA");
                reg.put("Apellido", "BBBBBB");



                //Insert con objeto
                String[] parametros = {"333"};
                Log.d("TAG_", "Modificar " + miBD.update("usuarios",reg,"codigo = ?", parametros));

                //Sql directo pero malo, malo, malo, no tiene parametros un 1
                //miBD.execSQL("insert into usuarios (codigo, nombre, apellido) values('888', 'Valentina', 'Zambra')");

                //Sql directo con parámetros
                //String[] parametros = {"Catalina", "Zambra", "222"};
                //miBD.execSQL("update usuarios set nombre = ?, apellido = ? where codigo = ?", parametros);
            } else {
                Toast.makeText(this, "Ocurrió un error al abrir base de datos", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            Toast.makeText(this, "Error " + ex.toString(), Toast.LENGTH_SHORT).show();
        }finally {
            miBD.close();
        }
    }

    private void eliminar(){
        Log.d("TAG_", "Eliminar");
        AdministradorBaseDatos adbd = new AdministradorBaseDatos(this, "BDPrueba", null, 1);
        SQLiteDatabase miBD = adbd.getWritableDatabase();

        try{
            if (adbd != null) {
                String[] parametros = {"222"};
                Log.d("TAG_", "Eliminar" + miBD.delete("usuarios","codigo=?",parametros));

                //Sql directo con parámetros
                //miBD.execSQL("delete from usuarios where codigo = ?", parametros);
            } else {
                Toast.makeText(this, "Ocurrió un error al abrir base de datos", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            Toast.makeText(this, "Error " + ex.toString(), Toast.LENGTH_SHORT).show();
        }finally {
            miBD.close();
        }

    }

    private void insertarUsuario(){
        Log.d("TAG_", "Insertar");
        AdministradorBaseDatos adbd = new AdministradorBaseDatos(this, "BDPrueba", null, 1);
        SQLiteDatabase miBD = adbd.getWritableDatabase();

        try{
            if (adbd != null) {
                ContentValues reg = new ContentValues();

                reg.put("codigo", 333);
                reg.put("nombre", "Emilia");
                reg.put("Apellido", "Ulloa");

                //Insert con objeto
                Log.d("TAG_", "Insertar " + miBD.insert("usuarios", null, reg));

                //Sql directo pero malo, malo, malo, no tiene parametros un 1
                //miBD.execSQL("insert into usuarios (codigo, nombre, apellido) values('888', 'Valentina', 'Zambra')");

                //Sql directo con parámetros
                String[] parametros = {"777","Catalina", "Zambra"};
                miBD.execSQL("insert into usuarios (codigo, nombre, apellido) values(?,?,?)", parametros);


            } else {
                Toast.makeText(this, "Ocurrió un error al abrir base de datos", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            Toast.makeText(this, "Error " + ex.toString(), Toast.LENGTH_SHORT).show();
        }finally {
            miBD.close();
        }
    }
}
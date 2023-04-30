package com.jjv.fragmentacionesmoviles;

import androidx.appcompat.app.AppCompatActivity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.text.ParsePosition;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    //"jdbc:mysql://localhost:3306/nombre_de_tu_base_de_datos";
    private EditText Host;
    private EditText User;
    private EditText Password;
    private EditText Database;
    private EditText IP;
    private Button Conectar;
    private EditText Port;
    private Connection conexion;
    private TableLayout tabla;

    //Variables de los datos
    private EditText ID;
    private EditText Categoria;
    private RadioButton Insertar;
    private RadioButton Modificar;
    private RadioButton Eliminar;
    private Button Aceptar;
    private RadioGroup Opciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Selectores de conexión
        Host = (EditText) findViewById(R.id.txtHost);
        User = (EditText) findViewById(R.id.txtUsuario);
        Password = (EditText) findViewById(R.id.txtContraseña);
        Database = (EditText) findViewById(R.id.txtBD);
        IP = (EditText) findViewById(R.id.txtIP);
        Conectar = (Button) findViewById(R.id.btnConectar);
        //tabla = (TableLayout) findViewById(R.id.tableData);

        //Selectores de la administracion
        ID = (EditText) findViewById(R.id.txtID);
        Categoria = (EditText) findViewById(R.id.txtCategoría);
        Insertar = (RadioButton) findViewById(R.id.rbInsertar);
        Modificar = (RadioButton) findViewById(R.id.rbModificar);
        Eliminar = (RadioButton) findViewById(R.id.rbEliminar);
        Aceptar = (Button) findViewById(R.id.btnRealizarOpe);
        Opciones = (RadioGroup) findViewById(R.id.rbGroup);


        Conectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conectToMYSQL();
            }
        });

        Aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, String.valueOf(Opciones.getCheckedRadioButtonId()), Toast.LENGTH_SHORT).show();
                switch (Opciones.getCheckedRadioButtonId()){
                    case 2131231236:
                        Toast.makeText(MainActivity.this, "Insertando", Toast.LENGTH_SHORT).show();
                        break;
                    case 2131231234:
                        Toast.makeText(MainActivity.this, "Elimiando", Toast.LENGTH_SHORT).show();
                        break;

                    case 2131231237:
                        Toast.makeText(MainActivity.this, "Modificando", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        Toast.makeText(MainActivity.this, "Por favor, selecciona una opción", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

    public void visualizar(View v){
        Intent i = new Intent(this, VisualizarDatos.class);
        i.putExtra("transaccion", "SELECT * FROM Producto");
        startActivity(i);
    }

    public void conectToMYSQL(){
        try {
            String url;
            url = "jdbc:mysql://"+IP.getText()+"/"+Database.getText();
            String user = User.getText().toString();
            String pass = Password.getText().toString();
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection(url, user, pass);
            if (!conexion.isClosed()){
                Toast.makeText(null, "Conexión exitosa", Toast.LENGTH_SHORT).show();
                conexion.close();
            }else {
                Toast.makeText(null, "La conexión está cerrada", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            //Toast.makeText(this, "Excepcion", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
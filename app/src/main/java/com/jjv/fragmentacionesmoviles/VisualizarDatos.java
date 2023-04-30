package com.jjv.fragmentacionesmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

public class VisualizarDatos extends AppCompatActivity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_datos);

        gridView = (GridView) findViewById(R.id.dgvTabla);
    }

    public void volver(View v){
        finish();
    }
}
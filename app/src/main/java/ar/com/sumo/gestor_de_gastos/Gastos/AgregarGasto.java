package ar.com.sumo.gestor_de_gastos.Gastos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import ar.com.sumo.gestor_de_gastos.Adapters.AdapterRecyclerViewGastos;
import ar.com.sumo.gestor_de_gastos.Adapters.DbAdapter;
import ar.com.sumo.gestor_de_gastos.MainActivity;
import ar.com.sumo.gestor_de_gastos.R;
import ar.com.sumo.gestor_de_gastos.Services.AgregarCategorias;
import ar.com.sumo.gestor_de_gastos.Transacciones.CategoriaTransaccion;

public class AgregarGasto extends AppCompatActivity {
    ArrayList<CategoriaTransaccion> categoriasGastos;


    private RecyclerView recyclerAgregarGastos;
    private DbAdapter db;
    private double monto;
    private boolean fijo;
    private Date fecha;
    private String detalle;
    private String categoriaNombre;
    private String colorCategoria;
    private TextView guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_gasto);

        //findView
        Toolbar toolbar = findViewById(R.id.toolbar_gastos);
        TextView salirGasto = findViewById(R.id.salirGastos);
        ImageButton detallesGastos = findViewById(R.id.detalles);
        recyclerAgregarGastos = findViewById(R.id.recyclerAgregarGastos);
        guardar = findViewById(R.id.guardar_gasto);
        //toolbar

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.izquierda);

        //salir
        salirGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                volver();

            }
        });

        //detalles
        detallesGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DetallesGastos.class);
                startActivity(i);
            }
        });


        //categorias de gastos
        AgregarCategorias agregar = new AgregarCategorias();
        categoriasGastos = agregar.agregarCategoriasGastos(this);
        recyclerAgregarGastos.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        AdapterRecyclerViewGastos adaptador = new AdapterRecyclerViewGastos(categoriasGastos);
        recyclerAgregarGastos.setAdapter(adaptador);
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "n" + categoriasGastos.get(recyclerAgregarGastos.getChildAdapterPosition(v)).getNombre(), Toast.LENGTH_SHORT).show();
                categoriaNombre = "n" + categoriasGastos.get(recyclerAgregarGastos.getChildAdapterPosition(v)).getNombre();
            }
        });

        //base de datos
        db = new DbAdapter(this);
        db.abrir();

        //guardar
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), categoriaNombre, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        volver();
        return true;
    }


    private void volver() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}

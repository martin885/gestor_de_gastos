package ar.com.sumo.gestor_de_gastos.Gastos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private boolean fijo = false;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String fecha = sdf.format(Calendar.getInstance().getTime());
    private String detalles = "";
    private String categoriaNombre = "";
    private String colorCategoria = "";
    private int indiceColorCategoria;
    private TextView guardar;
    private ImageButton detallesGastos;
    private TextView salirGasto;
    private EditText mMonto;
    private CheckBox mFijo;
    private Calendar mFecha;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_gasto);

        cargarItems();

        //----------------SALIR---------------------------
        salirGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver();
            }
        });

        //----------------DETALLES------------------------
        detallesGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DetallesGastos.class);
                startActivityForResult(i, RESULT_OK);
            }
        });


        //---------------SACAR FOCO CUANDO APRIETA DONE------------

        /** mMonto.setOnEditorActionListener(new TextView.OnEditorActionListener() {
        @Override public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_GO) {
        //mMonto.clearFocus();
        //getCurrentFocus().clearFocus();

        }
        return false;
        }
        });**/

        //---------------CATEGORIAS DE GASTOS----------------------
        AgregarCategorias agregar = new AgregarCategorias();
        categoriasGastos = agregar.agregarCategoriasGastos(this);
        recyclerAgregarGastos.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        AdapterRecyclerViewGastos adaptador = new AdapterRecyclerViewGastos(categoriasGastos);
        recyclerAgregarGastos.setAdapter(adaptador);
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "n" + categoriasGastos.get(recyclerAgregarGastos.getChildAdapterPosition(v)).getNombre(), Toast.LENGTH_SHORT).show();
                categoriaNombre = categoriasGastos.get(recyclerAgregarGastos.getChildAdapterPosition(v)).getNombre();
                colorCategoria = categoriasGastos.get(recyclerAgregarGastos.getChildAdapterPosition(v)).getColor();
            }
        });

        //------------------GUARDAR--------------------------------
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), categoriaNombre, Toast.LENGTH_LONG).show();

                monto = Double.valueOf(mMonto.getText().toString());
                if (categoriaNombre.isEmpty() || monto == 0 || fecha.isEmpty()) {
                    volver();
                    Toast.makeText(getApplicationContext(), "No se ha guardado nada...", Toast.LENGTH_SHORT).show();
                    return;
                }

                indiceColorCategoria = db.obtenerColor(colorCategoria);
                db.insertarGasto(categoriaNombre, indiceColorCategoria, monto, fijo, fecha, detalles);
                volver();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        volver();
        return true;
    }


    private void volver() {
        db.close();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    private void cargarItems() {
        //---------------FINDVIEW--------------------------
        toolbar = findViewById(R.id.toolbar_gastos);
        salirGasto = findViewById(R.id.salirGastos);
        detallesGastos = findViewById(R.id.detalles);
        recyclerAgregarGastos = findViewById(R.id.recyclerAgregarGastos);
        guardar = findViewById(R.id.guardar_gasto);
        mMonto = findViewById(R.id.monto_gasto);
        mFijo = findViewById(R.id.gasto_fijo);
        //mFecha=;

        //----------------TOOLBAR-------------------------
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.izquierda);

        //----------------DATABASE------------------------
        db = new DbAdapter(this);
        db.abrir();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_OK) {
            detalles = data.getStringExtra("detalles");
        }
    }
}

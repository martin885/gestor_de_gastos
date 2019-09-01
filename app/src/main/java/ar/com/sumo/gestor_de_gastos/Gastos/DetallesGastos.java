package ar.com.sumo.gestor_de_gastos.Gastos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ar.com.sumo.gestor_de_gastos.R;

public class DetallesGastos extends AppCompatActivity {

    private EditText mDetalles;
    private String detalles;
    private TextView salirDetalles;
    private TextView mGuardar;
    private Toolbar toolbar;
    private final int MAXIMO_CARACTERES = 140;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_gastos);

        toolbar = findViewById(R.id.toolbar_gastos);
        salirDetalles = findViewById(R.id.salirDetallesGastos);
        mDetalles = findViewById(R.id.detalle_gasto);
        mGuardar = findViewById(R.id.guardar_detalles);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.izquierda);

        salirDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDetalles.getText().toString().length() <= MAXIMO_CARACTERES) {
                    detalles =mDetalles.getText().toString();
                }else{
                    Toast.makeText(getApplicationContext(),
                            "El detalle debe contener menos de 50 caractéres...",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                onBackPressed();
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent i= new Intent();
        i.putExtra("detalles",detalles);
        setResult(DetallesGastos.RESULT_OK,i);
        Toast.makeText(getApplicationContext(),
                "El detalle se guardó con éxito...",
                Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }
}

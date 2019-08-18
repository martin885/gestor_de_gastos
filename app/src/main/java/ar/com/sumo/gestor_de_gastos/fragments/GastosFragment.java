package ar.com.sumo.gestor_de_gastos.fragments;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import ar.com.sumo.gestor_de_gastos.Adapters.AdapterRecyclerViewLegend;
import ar.com.sumo.gestor_de_gastos.Gastos.AgregarGasto;
import ar.com.sumo.gestor_de_gastos.Gastos.Gasto;
import ar.com.sumo.gestor_de_gastos.R;
import ar.com.sumo.gestor_de_gastos.Services.Calendarios;
import ar.com.sumo.gestor_de_gastos.Services.Graficos;
import ar.com.sumo.gestor_de_gastos.Services.Leyenda;
import ar.com.sumo.gestor_de_gastos.Services.PercentFormatter;
import ar.com.sumo.gestor_de_gastos.Services.TestGastos;
import ar.com.sumo.gestor_de_gastos.Transacciones.Transaccion;

/**
 * A simple {@link Fragment} subclass.
 */
public class GastosFragment extends Fragment {


    public GastosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gastos, container, false);
        Calendarios calendario = new Calendarios();
        calendario.cargarCalendario(view);

        //cargarGraficos(view);

        Graficos graficos = new Graficos();

        graficos.cargarGraficos(view);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUpMenu(view);
            }
        });
        return view;
    }

    private void popUpMenu(View view) {
//        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
//        View mView = getLayoutInflater().inflate(R.layout.agregar_gastos_ingresos, null);
//        mBuilder.setView(mView);
//        AlertDialog dialog = mBuilder.create();
//        dialog.show();
        Dialog alertDialog = new Dialog(getContext());
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(R.layout.agregar_gastos_ingresos);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.findViewById(R.id.agregarIngreso).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        alertDialog.findViewById(R.id.agregarGasto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Hola", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), AgregarGasto.class);
                startActivity(i);
            }
        });
        alertDialog.show();
    }


}

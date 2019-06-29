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
import ar.com.sumo.gestor_de_gastos.Services.Leyenda;
import ar.com.sumo.gestor_de_gastos.Services.PercentFormatter;
import ar.com.sumo.gestor_de_gastos.Services.TestGastos;
import ar.com.sumo.gestor_de_gastos.Transacciones.Transaccion;

/**
 * A simple {@link Fragment} subclass.
 */
public class GastosFragment extends Fragment {

    PieChart pieChart;
    PieChart pieChartTotal;
    PieChart pieChartMes;
    PieChart pieChartDia;
    RecyclerView recyclerPersonajes;
    ArrayList<Transaccion> listaGastosCompleta;
    ArrayList<Leyenda> listaGastosLeyenda;

    public GastosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gastos, container, false);
        Calendar calendar = Calendar.getInstance();
        CalendarView calendarView = view.findViewById(R.id.calendarViewGastos);
        List<EventDay> events = new ArrayList<>();
        events.add(new EventDay(calendar, R.drawable.ic_launcher_background));
        try {
            calendarView.setDate(calendar);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
        calendarView.setEvents(events);

        calendarView.setOnDayClickListener(eventDay ->
                Toast.makeText(getContext(),
                        eventDay.getCalendar().getTime().toString() + " "
                                + eventDay.isEnabled(),
                        Toast.LENGTH_SHORT).show());
        listaGastosCompleta = new ArrayList<Transaccion>();
        listaGastosLeyenda = new ArrayList<Leyenda>();
        cargarGraficos(view);

        recyclerPersonajes = view.findViewById(R.id.recyclerLegend);
        recyclerPersonajes.setLayoutManager(new LinearLayoutManager(getContext()));


        AdapterRecyclerViewLegend adaptador = new AdapterRecyclerViewLegend(listaGastosLeyenda);
        recyclerPersonajes.setAdapter(adaptador);

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

    private void cargarGraficos(View view) {
        pieChart = view.findViewById(R.id.pieChart);
        pieChartTotal = view.findViewById(R.id.pieChartTotal);
        pieChartMes = view.findViewById(R.id.pieChartMes);
        pieChartDia = view.findViewById(R.id.pieChartDia);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        //pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.getLegend().setEnabled(false);
        pieChartTotal.setUsePercentValues(true);
        pieChartTotal.getDescription().setEnabled(false);
        pieChartTotal.setExtraOffsets(0, 0, 0, 0);
        pieChartTotal.setDragDecelerationFrictionCoef(0.95f);
        pieChartTotal.setHoleColor(Color.WHITE);
        pieChartTotal.getLegend().setEnabled(false);
        pieChartMes.setUsePercentValues(true);
        pieChartMes.getDescription().setEnabled(false);
        pieChartMes.setExtraOffsets(5, 10, 5, 5);
        pieChartMes.setDragDecelerationFrictionCoef(0.95f);
        pieChartMes.setHoleColor(Color.WHITE);
        pieChartMes.getLegend().setEnabled(false);
        pieChartDia.setUsePercentValues(true);
        pieChartDia.getDescription().setEnabled(false);
        pieChartDia.setExtraOffsets(5, 10, 5, 5);
        pieChartDia.setDragDecelerationFrictionCoef(0.95f);
        pieChartDia.setHoleColor(Color.WHITE);
        pieChartDia.getLegend().setEnabled(false);
        listaGastosCompleta = new TestGastos().crearGastos();
        //Toast.makeText(getContext(), "Largo lista: " + listaGastosCompleta.size(), Toast.LENGTH_LONG).show();
        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();
        PieEntry general = new PieEntry(0);
        PieDataSet dataSet = new PieDataSet(yValues, "Gastos");
        dataSet.setSliceSpace(1f);
        dataSet.setSelectionShift(5f);
        ArrayList<Integer> listaColores = new ArrayList<Integer>();
        double valor = 0;
        boolean comparador = false;
        for (int i = 0; i < listaGastosCompleta.size(); i++) {
            if (i != listaGastosCompleta.size() - 1) {
                comparador = listaGastosCompleta.get(i).getCategoria().getNombre().equals(listaGastosCompleta.get(i + 1).getCategoria().getNombre());
            } else {
                comparador = false;
            }

            if (comparador) {
                valor += listaGastosCompleta.get(i).getMonto();
            } else {
                valor += listaGastosCompleta.get(i).getMonto();
                //Log.d("COMPARADOR","="+comparador);
                PieEntry pieEntry = new PieEntry((float) valor);
                yValues.add(pieEntry);
                //dataSet.addEntry(pieEntry );
                listaColores.add(Color.parseColor(listaGastosCompleta.get(i).getCategoria().getColor()));
                listaGastosLeyenda.add(new Leyenda(listaGastosCompleta.get(i).getCategoria(), valor));
                valor = 0;
            }

        }
        //dataSet.setColors(new int[]{R.color.colorAccent, R.color.colorAhorros, R.color.colorAlimentos}, getContext());
        dataSet.setColors(listaColores);
//        yValues.add(general);
//
//        yValues.add(new PieEntry(new Random().nextFloat() * 100f));
//        yValues.add(new PieEntry(new Random().nextFloat() * 0f));
//        yValues.add(new PieEntry(new Random().nextFloat() * 0f));
//        yValues.add(new PieEntry(new Random().nextFloat() * 0f));
//        yValues.add(new PieEntry(new Random().nextFloat() * 0f));
//        yValues.add(new PieEntry(new Random().nextFloat() * 0f));
//        yValues.add(new PieEntry(new Random().nextFloat() * 0f));
//        yValues.add(new PieEntry(new Random().nextFloat() * 0f));


        //Description description = new Description();
        //description.setText("Esta es una descripción");
        //pieChart.setDescription(description);
        pieChart.animateY(1000, Easing.EaseInOutCubic);
        //pieChartTotal.setDescription(description);
        pieChartTotal.animateY(1000, Easing.EaseInOutCubic);
        //pieChartMes.setDescription(description);
        pieChartMes.animateY(1000, Easing.EaseInOutCubic);
        //pieChartDia.setDescription(description);
        pieChartDia.animateY(1000, Easing.EaseInOutCubic);

        //yValues.get(0).setY(new Random().nextFloat() * 100f);
        //yValues.get(0).setLabel("1");


        PieData pieData = new PieData(dataSet);
        //pieData.setValueTextSize(20f);
        PieData pieDataTotal = new PieData(dataSet);
        // pieData.setValueTextSize(1f);
        pieData.setValueFormatter(new PercentFormatter(pieChart, false));
        pieData.setValueTextColor(Color.WHITE);
        pieChart.setData(pieData);
        pieChartTotal.setData(pieDataTotal);
        pieChartMes.setData(pieDataTotal);

        pieChartDia.setData(pieDataTotal);
    }

}

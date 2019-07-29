package ar.com.sumo.gestor_de_gastos.Services;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

import ar.com.sumo.gestor_de_gastos.Adapters.AdapterRecyclerViewLegend;
import ar.com.sumo.gestor_de_gastos.R;
import ar.com.sumo.gestor_de_gastos.Transacciones.Transaccion;

public class Graficos {


    private PieChart pieChart;
    private PieChart pieChartTotal;
    private PieChart pieChartMes;
    private PieChart pieChartDia;
    private RecyclerView recyclerPersonajes;
    private ArrayList<Transaccion> listaGastosCompleta;
    private ArrayList<Leyenda> listaGastosLeyenda;
    private PieDataSet dataSet;
    private ArrayList<PieEntry> yValues;

    public void cargarGraficos(View view) {

        pieChartTotal = view.findViewById(R.id.pieChartTotal);
        pieChartMes = view.findViewById(R.id.pieChartMes);
        pieChartDia = view.findViewById(R.id.pieChartDia);
        listaGastosCompleta = new ArrayList<Transaccion>();
        listaGastosLeyenda = new ArrayList<Leyenda>();

        cargarPieChartPrincipal(view);

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

        cargarGastosYColores();

        //Description description = new Description();
        //description.setText("Esta es una descripción");
        //pieChart.setDescription(description);

        //pieChartTotal.setDescription(description);
        pieChartTotal.animateY(1000, Easing.EaseInOutCubic);
        //pieChartMes.setDescription(description);
        pieChartMes.animateY(1000, Easing.EaseInOutCubic);
        //pieChartDia.setDescription(description);
        pieChartDia.animateY(1000, Easing.EaseInOutCubic);

        //yValues.get(0).setY(new Random().nextFloat() * 100f);
        //yValues.get(0).setLabel("1");


        cargarDataSetPieChartPrincipal();
        //pieData.setValueTextSize(20f);
        PieData pieDataTotal = new PieData(dataSet);
        // pieData.setValueTextSize(1f);


        pieChartTotal.setData(pieDataTotal);
        pieChartMes.setData(pieDataTotal);
        pieChartDia.setData(pieDataTotal);

        recyclerPersonajes = view.findViewById(R.id.recyclerLegend);
        recyclerPersonajes.setLayoutManager(new LinearLayoutManager(view.getContext()));


        AdapterRecyclerViewLegend adaptador = new AdapterRecyclerViewLegend(listaGastosLeyenda);
        recyclerPersonajes.setAdapter(adaptador);
    }

    private void cargarPieChartPrincipal(View view) {
        pieChart = view.findViewById(R.id.pieChart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        //pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.getLegend().setEnabled(false);
    }

    private void cargarDataSetPieChartPrincipal() {
        pieChart.animateY(1000, Easing.EaseInOutCubic);
        PieData pieData = new PieData(dataSet);
        pieData.setValueFormatter(new PercentFormatter(pieChart, false));
        pieData.setValueTextColor(Color.WHITE);
        pieChart.setData(pieData);
    }

    private void cargarGastosYColores() {
        listaGastosCompleta = new TestGastos().crearGastos();
        //Toast.makeText(getContext(), "Largo lista: " + listaGastosCompleta.size(), Toast.LENGTH_LONG).show();


        yValues = new ArrayList<PieEntry>();
        PieEntry general = new PieEntry(0);
        dataSet = new PieDataSet(yValues, "Gastos");
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
    }
}

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
import java.util.Calendar;

import ar.com.sumo.gestor_de_gastos.Adapters.AdapterRecyclerViewLegend;
import ar.com.sumo.gestor_de_gastos.Adapters.DbAdapter;
import ar.com.sumo.gestor_de_gastos.R;
import ar.com.sumo.gestor_de_gastos.Transacciones.Transaccion;

public class Graficos {

    private PieChart pieChart;
    private PieChart pieChartTotal;
    private PieChart pieChartMes;
    private PieChart pieChartDia;
    private RecyclerView recyclerPersonajes;
    private ArrayList<Transaccion> listaGastos;
    private ArrayList<Leyenda> listaGastosLeyenda;
    private PieDataSet dataSetPrincipal;
    private PieDataSet dataSetTotal;
    private PieDataSet dataSetMes;
    private PieDataSet dataSetDia;
    private ArrayList<PieEntry> valoresPrincipales;
    private ArrayList<PieEntry> valoresTotales;
    private ArrayList<PieEntry> valoresMensuales;
    private ArrayList<PieEntry> valoresDiarios;
    private DbAdapter db;
    Calendar calendar = Calendar.getInstance();

    public void cargarGraficos(View view) {
        db = new DbAdapter(view.getContext());
        db.abrir();

        listaGastos = new ArrayList<Transaccion>();
        listaGastosLeyenda = new ArrayList<Leyenda>();

        cargarGraficoPrincipal(view);
        cargarGraficoTotal(view);
        cargarGraficoMes(view);
        cargarGraficoDia(view);


        recyclerPersonajes = view.findViewById(R.id.recyclerLegend);
        recyclerPersonajes.setLayoutManager(new LinearLayoutManager(view.getContext()));

        AdapterRecyclerViewLegend adaptador = new AdapterRecyclerViewLegend(listaGastosLeyenda);
        recyclerPersonajes.setAdapter(adaptador);

        db.close();
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

    private void cargarPieChartTotal(View view) {

        pieChartTotal = view.findViewById(R.id.pieChartTotal);
        pieChartTotal.setUsePercentValues(true);
        pieChartTotal.getDescription().setEnabled(false);
        //pieChartTotal.setExtraOffsets(0, 0, 0, 0);
        pieChartTotal.setDragDecelerationFrictionCoef(0.95f);
        pieChartTotal.setHoleColor(Color.WHITE);
        pieChartTotal.getLegend().setEnabled(false);

    }

    private void cargarPieChartMes(View view) {
        pieChartMes = view.findViewById(R.id.pieChartMes);
        pieChartMes.setUsePercentValues(true);
        pieChartMes.getDescription().setEnabled(false);
        pieChartMes.setExtraOffsets(5, 10, 5, 5);
        pieChartMes.setDragDecelerationFrictionCoef(0.95f);
        pieChartMes.setHoleColor(Color.WHITE);
        pieChartMes.getLegend().setEnabled(false);
    }

    private void cargarPieChartDia(View view) {
        pieChartDia = view.findViewById(R.id.pieChartDia);
        pieChartDia.setUsePercentValues(true);
        pieChartDia.getDescription().setEnabled(false);
        pieChartDia.setExtraOffsets(5, 10, 5, 5);
        pieChartDia.setDragDecelerationFrictionCoef(0.95f);
        pieChartDia.setHoleColor(Color.WHITE);
        pieChartDia.getLegend().setEnabled(false);
    }

    private void cargarDataSetPieChartPrincipal() {

        pieChart.animateY(1000, Easing.EaseInOutCubic);
        PieData pieData = new PieData(dataSetPrincipal);
        pieData.setValueFormatter(new PercentFormatter(pieChart, false));
        pieData.setValueTextColor(Color.WHITE);
        pieChart.setData(pieData);

    }

    private void cargarDataSetPieChartTotal() {

        pieChartTotal.animateY(1000, Easing.EaseInOutCubic);
        PieData pieDataTotal = new PieData(dataSetTotal);
        pieDataTotal.setValueFormatter(new PercentFormatter(pieChart, false));
        pieDataTotal.setValueTextColor(Color.WHITE);
        pieChartTotal.setData(pieDataTotal);

    }

    private void cargarDataSetPieChartMes() {

        pieChartMes.animateY(1000, Easing.EaseInOutCubic);
        PieData pieDataMes = new PieData(dataSetMes);
        pieDataMes.setValueFormatter(new PercentFormatter(pieChart, false));
        pieDataMes.setValueTextColor(Color.WHITE);
        pieChartMes.setData(pieDataMes);

    }

    private void cargarDataSetPieChartDia() {
        pieChartDia.animateY(1000, Easing.EaseInOutCubic);
        PieData pieDataDia = new PieData(dataSetDia);
        pieDataDia.setValueFormatter(new PercentFormatter(pieChart, false));
        pieDataDia.setValueTextColor(Color.WHITE);
        pieChartDia.setData(pieDataDia);
    }

    private void cargarGastosYColoresPrincipal() {


        //Esto lo tengo que tomar de la base de datos
        listaGastos = new TestGastos().crearGastos();

        //Toast.makeText(getContext(), "Largo lista: " + listaGastosCompleta.size(), Toast.LENGTH_LONG).show();

        valoresPrincipales = new ArrayList<PieEntry>();
        //PieEntry general = new PieEntry(0);
        dataSetPrincipal = new PieDataSet(valoresPrincipales, "Gastos");
        dataSetPrincipal.setSliceSpace(1f);
        dataSetPrincipal.setSelectionShift(5f);
        ArrayList<Integer> listaColores = new ArrayList<Integer>();
        double valor = 0;
        boolean comparador = false;
        for (int i = 0; i < listaGastos.size(); i++) {
            //comparo el valor de nombre de los objetos
            if (i != listaGastos.size() - 1) {
                comparador = listaGastos.get(i).
                        getCategoria().
                        getNombre().
                        equals(listaGastos.
                                get(i + 1).
                                getCategoria().
                                getNombre());
            } else {
                comparador = false;
            }

            if (comparador) {
                valor += listaGastos.get(i).getMonto();
            } else {
                valor += listaGastos.get(i).getMonto();
                //Log.d("COMPARADOR","="+comparador);
                PieEntry pieEntry = new PieEntry((float) valor);
                valoresPrincipales.add(pieEntry);
                //dataSet.addEntry(pieEntry );
                listaColores.add(Color.parseColor(listaGastos.get(i).getCategoria().getColor()));
                listaGastosLeyenda.add(new Leyenda(listaGastos.get(i).getCategoria(), valor));
                valor = 0;
            }

        }
        //dataSet.setColors(new int[]{R.color.colorAccent, R.color.colorAhorros, R.color.colorAlimentos}, getContext());
        dataSetPrincipal.setColors(listaColores);
    }

    private void cargarGastosYColoresTotal() {
        //Esto lo tengo que tomar de la base de datos
        listaGastos = new TestGastos().crearGastos();

        //Toast.makeText(getContext(), "Largo lista: " + listaGastosCompleta.size(), Toast.LENGTH_LONG).show();

        valoresTotales = new ArrayList<PieEntry>();
        //PieEntry general = new PieEntry(0);
        dataSetTotal = new PieDataSet(valoresTotales, "Gastos");
        dataSetTotal.setSliceSpace(1f);
        dataSetTotal.setSelectionShift(5f);
        ArrayList<Integer> listaColores = new ArrayList<Integer>();
        double valor = 0;
        boolean comparador = false;
        for (int i = 0; i < listaGastos.size(); i++) {
            //comparo el valor de nombre de los objetos
            if (i != listaGastos.size() - 1) {
                comparador = listaGastos.get(i).
                        getCategoria().
                        getNombre().
                        equals(listaGastos.
                                get(i + 1).
                                getCategoria().
                                getNombre());
            } else {
                comparador = false;
            }

            if (comparador) {
                valor += listaGastos.get(i).getMonto();
            } else {
                valor += listaGastos.get(i).getMonto();
                //Log.d("COMPARADOR","="+comparador);
                PieEntry pieEntry = new PieEntry((float) valor);
                valoresTotales.add(pieEntry);
                //dataSet.addEntry(pieEntry );
                listaColores.add(Color.parseColor(listaGastos.get(i).getCategoria().getColor()));
                //listaGastosLeyenda.add(new Leyenda(listaGastosCompleta.get(i).getCategoria(), valor));
                valor = 0;
            }

        }
        //dataSet.setColors(new int[]{R.color.colorAccent, R.color.colorAhorros, R.color.colorAlimentos}, getContext());
        dataSetTotal.setColors(listaColores);
    }

    private void cargarGastosYColoresMes() {
        //Esto lo tengo que tomar de la base de datos
        String mes = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String año = String.valueOf(calendar.get(Calendar.YEAR));
        listaGastos = db.listaGastosMes(mes, año);

        //Toast.makeText(getContext(), "Largo lista: " + listaGastosCompleta.size(), Toast.LENGTH_LONG).show();

        valoresMensuales = new ArrayList<PieEntry>();
        //PieEntry general = new PieEntry(0);
        dataSetMes = new PieDataSet(valoresMensuales, "Gastos");
        dataSetMes.setSliceSpace(1f);
        dataSetMes.setSelectionShift(5f);
        ArrayList<Integer> listaColores = new ArrayList<Integer>();
        double valor = 0;
        boolean comparador = false;
        for (int i = 0; i < listaGastos.size(); i++) {
            //comparo el valor de nombre de los objetos
            if (i != listaGastos.size() - 1) {
                comparador = listaGastos.get(i).
                        getCategoria().
                        getNombre().
                        equals(listaGastos.
                                get(i + 1).
                                getCategoria().
                                getNombre());
            } else {
                comparador = false;
            }

            if (comparador) {
                valor += listaGastos.get(i).getMonto();
            } else {
                valor += listaGastos.get(i).getMonto();
                //Log.d("COMPARADOR","="+comparador);
                PieEntry pieEntry = new PieEntry((float) valor);
                valoresMensuales.add(pieEntry);
                //dataSet.addEntry(pieEntry );
                listaColores.add(Color.parseColor(listaGastos.get(i).getCategoria().getColor()));
                //listaGastosLeyenda.add(new Leyenda(listaGastosCompleta.get(i).getCategoria(), valor));
                valor = 0;
            }

        }
        //dataSet.setColors(new int[]{R.color.colorAccent, R.color.colorAhorros, R.color.colorAlimentos}, getContext());
        dataSetMes.setColors(listaColores);
    }

    private void cargarGastosYColoresDia() {
        //Esto lo tengo que tomar de la base de datos
        listaGastos = db.listaGastosDia();

        //Toast.makeText(getContext(), "Largo lista: " + listaGastosCompleta.size(), Toast.LENGTH_LONG).show();

        valoresDiarios = new ArrayList<PieEntry>();
        //PieEntry general = new PieEntry(0);
        dataSetDia = new PieDataSet(valoresDiarios, "Gastos");
        dataSetDia.setSliceSpace(1f);
        dataSetDia.setSelectionShift(5f);
        ArrayList<Integer> listaColores = new ArrayList<Integer>();
        double valor = 0;
        boolean comparador = false;
        for (int i = 0; i < listaGastos.size(); i++) {
            //comparo el valor de nombre de los objetos
            if (i != listaGastos.size() - 1) {
                comparador = listaGastos.get(i).
                        getCategoria().
                        getNombre().
                        equals(listaGastos.
                                get(i + 1).
                                getCategoria().
                                getNombre());
            } else {
                comparador = false;
            }

            if (comparador) {
                valor += listaGastos.get(i).getMonto();
            } else {
                valor += listaGastos.get(i).getMonto();
                //Log.d("COMPARADOR","="+comparador);
                PieEntry pieEntry = new PieEntry((float) valor);
                valoresDiarios.add(pieEntry);
                //dataSet.addEntry(pieEntry );
                listaColores.add(Color.parseColor(listaGastos.get(i).getCategoria().getColor()));
                //listaGastosLeyenda.add(new Leyenda(listaGastosCompleta.get(i).getCategoria(), valor));
                valor = 0;
            }

        }
        //dataSet.setColors(new int[]{R.color.colorAccent, R.color.colorAhorros, R.color.colorAlimentos}, getContext());
        dataSetDia.setColors(listaColores);
    }

    private void cargarGraficoPrincipal(View view) {
        cargarPieChartPrincipal(view);
        cargarGastosYColoresPrincipal();
        cargarDataSetPieChartPrincipal();
    }

    private void cargarGraficoTotal(View view) {
        cargarPieChartTotal(view);
        cargarGastosYColoresTotal();
        cargarDataSetPieChartTotal();
    }

    private void cargarGraficoMes(View view) {
        cargarPieChartMes(view);
        cargarGastosYColoresMes();
        cargarDataSetPieChartMes();
    }

    private void cargarGraficoDia(View view) {
        cargarPieChartDia(view);
        cargarGastosYColoresDia();
        cargarDataSetPieChartDia();
    }
}

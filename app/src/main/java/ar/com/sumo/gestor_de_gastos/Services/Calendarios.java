package ar.com.sumo.gestor_de_gastos.Services;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ar.com.sumo.gestor_de_gastos.R;

public class Calendarios {

    public void cargarCalendario(View view) {

        Calendar calendar = Calendar.getInstance();

        CalendarView calendarView = view.findViewById(R.id.calendarViewGastos);

        List<EventDay> events = new ArrayList<>();

        Graficos graficos = new Graficos();

        events.add(new EventDay(calendar, R.drawable.ic_launcher_background));

        try {
            calendarView.setDate(calendar);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
        calendarView.setEvents(events);
        calendarView.setOnDayClickListener(eventDay ->
        {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            String dateString = format.format(eventDay.getCalendar().getTime());

            graficos.cargarGraficos(view, dateString);
            TextView mes_gasto = view.findViewById(R.id.mes_gasto);
            mes_gasto.setText(Mes(dateString, view));

        });
    }


    public String Mes(String mes_numero, View view) {
        String[] fechas = mes_numero.split("/");
        String mes = fechas[1];
        String mes_palabra = null;
        switch (mes) {
            case "01":
                mes_palabra = view.getResources().getString(R.string.enero);
                break;
            case "02":
                mes_palabra = view.getResources().getString(R.string.febrero);
                break;
            case "03":
                mes_palabra = view.getResources().getString(R.string.marzo);
                break;
            case "04":
                mes_palabra = view.getResources().getString(R.string.abril);
                break;
            case "05":
                mes_palabra = view.getResources().getString(R.string.mayo);
                break;
            case "06":
                mes_palabra = view.getResources().getString(R.string.junio);
                break;
            case "07":
                mes_palabra = view.getResources().getString(R.string.julio);
                break;
            case "08":
                mes_palabra = view.getResources().getString(R.string.agosto);
                break;
            case "09":
                mes_palabra = view.getResources().getString(R.string.septiembre);
                break;
            case "10":
                mes_palabra = view.getResources().getString(R.string.octubre);
                break;
            case "11":
                mes_palabra = view.getResources().getString(R.string.noviembre);
                break;
            case "12":
                mes_palabra = view.getResources().getString(R.string.diciembre);
                break;
        }
        return mes_palabra;
    }

}

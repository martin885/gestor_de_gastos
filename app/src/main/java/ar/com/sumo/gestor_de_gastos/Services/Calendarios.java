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

    TextView mesGasto1;
    TextView mesGasto2;
    SimpleDateFormat format;
    String dateString;
    String[] fecha;

    public void cargarCalendario(View view) {

        Calendar calendar = Calendar.getInstance();

        CalendarView calendarView = view.findViewById(R.id.calendarViewGastos);

        List<EventDay> events = new ArrayList<>();

        Graficos graficos = new Graficos();

        format = new SimpleDateFormat("dd/MM/yyyy");

        dateString = format.format(Calendar.getInstance().getTime());

        fecha = fechaSeparada(dateString);

        graficos.cargarGraficos(view, dateString);

        mesGasto1 = view.findViewById(R.id.mes_gasto_1);
        mesGasto2 = view.findViewById(R.id.mes_gasto_2);

        mesGasto1.setText(Mes(fecha[1], view) + " " + fecha[2]);

        mesGasto2.setText(Mes(fecha[1], view) + " " + fecha[2]);
        events.add(new EventDay(calendar, R.drawable.ic_launcher_background));


        calendarView.setEvents(events);


        calendarView.setOnDayClickListener(eventDay ->
        {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            dateString = format.format(eventDay.getCalendar().getTime());

            fecha = fechaSeparada(dateString);

            graficos.cargarGraficos(view, dateString);

            mesGasto1.setText(Mes(fecha[1], view) + " " + fecha[2]);
            mesGasto2.setText(Mes(fecha[1], view) + " " + fecha[2]);

            events.add(new EventDay(eventDay.getCalendar(), R.drawable.ic_launcher_background));
            calendarView.setEvents(events);
            

        });
    }


    public String Mes(String mes_numero, View view) {

        String mes_palabra = null;

        switch (mes_numero) {
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

    public String[] fechaSeparada(String fecha) {
        String[] fechas = fecha.split("/");

        return fechas;
    }
}

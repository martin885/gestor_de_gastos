package ar.com.sumo.gestor_de_gastos.Services;

import android.view.View;
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

        });
    }

}

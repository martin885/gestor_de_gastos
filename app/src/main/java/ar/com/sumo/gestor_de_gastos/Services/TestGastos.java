package ar.com.sumo.gestor_de_gastos.Services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import ar.com.sumo.gestor_de_gastos.Gastos.Gasto;
import ar.com.sumo.gestor_de_gastos.Transacciones.Transaccion;

public class TestGastos {


    public ArrayList<Transaccion> crearGastos() {
        ArrayList<Transaccion> listaGastos = new ArrayList<Transaccion>();
        for (int i = 0; i < 10; i++) {

            listaGastos.add(new Gasto("Entretenimiento", "#577BFF", new Random().nextDouble() * 100, false, Calendar.getInstance().getTime(),"hola"));
        }
        for (int i = 0; i < 10; i++) {

            listaGastos.add(new Gasto("General", "#1574FF", new Random().nextDouble() * 100, false, Calendar.getInstance().getTime(),"hola"));
        }
        for (int i = 0; i < 10; i++) {

            listaGastos.add(new Gasto("Gasolina", "#2071FF", new Random().nextDouble() * 100, false, Calendar.getInstance().getTime(),"hola"));
        }
        return listaGastos;
    }

}

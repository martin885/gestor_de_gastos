package ar.com.sumo.gestor_de_gastos.Services;

import android.content.Context;

import java.util.ArrayList;

import ar.com.sumo.gestor_de_gastos.R;
import ar.com.sumo.gestor_de_gastos.Transacciones.CategoriaTransaccion;

public class AgregarCategorias {


    public ArrayList<CategoriaTransaccion> agregarCategoriasGastos(Context context) {
        ArrayList<CategoriaTransaccion> list = new ArrayList<CategoriaTransaccion>();
        list.add(new CategoriaTransaccion(context.getResources().getString(R.string.gastos_hogar),
                context.getResources().getString(R.string.colorHogar)));
        list.add(new CategoriaTransaccion(context.getResources().getString(R.string.gastos_facturas),
                context.getResources().getString(R.string.colorFacturas)));
        list.add(new CategoriaTransaccion(context.getResources().getString(R.string.gastos_alimentos),
                context.getResources().getString(R.string.colorAlimentos)));
        list.add(new CategoriaTransaccion(context.getResources().getString(R.string.gastos_provisiones),
                context.getResources().getString(R.string.colorProvisiones)));
        list.add(new CategoriaTransaccion(context.getResources().getString(R.string.gastos_salud),
                context.getResources().getString(R.string.colorSalud)));
        list.add(new CategoriaTransaccion(context.getResources().getString(R.string.gastos_indumentaria),
                context.getResources().getString(R.string.colorIndumentaria)));
        list.add(new CategoriaTransaccion(context.getResources().getString(R.string.gastos_entretenimiento),
                context.getResources().getString(R.string.colorEntretenimiento)));
        list.add(new CategoriaTransaccion(context.getResources().getString(R.string.gastos_niños),
                context.getResources().getString(R.string.colorNiños)));
        list.add(new CategoriaTransaccion(context.getResources().getString(R.string.gastos_gasolina),
                context.getResources().getString(R.string.colorGasolina)));
        list.add(new CategoriaTransaccion(context.getResources().getString(R.string.gastos_vehiculo),
                context.getResources().getString(R.string.colorVehiculo)));
        list.add(new CategoriaTransaccion(context.getResources().getString(R.string.gastos_vacaciones),
                context.getResources().getString(R.string.colorVacaciones)));
        list.add(new CategoriaTransaccion(context.getResources().getString(R.string.gastos_regalos),
                context.getResources().getString(R.string.colorRegalos)));
        list.add(new CategoriaTransaccion(context.getResources().getString(R.string.gastos_general),
                context.getResources().getString(R.string.colorGeneral)));
        list.add(new CategoriaTransaccion(context.getResources().getString(R.string.gastos_deporte),
                context.getResources().getString(R.string.colorDeporte)));
        return list;
    }
}

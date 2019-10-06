package ar.com.sumo.gestor_de_gastos.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ar.com.sumo.gestor_de_gastos.Gastos.Gasto;
import ar.com.sumo.gestor_de_gastos.Services.DBHelper;
import ar.com.sumo.gestor_de_gastos.Transacciones.Transaccion;

public class DbAdapter {

    private Context context;
    private DBHelper dbHelper;
    private static final String TABLA_TRANSACCION = "transaccion";
    private static final String TABLA_CATEGORIA = "categoria";
    private SQLiteDatabase db;


    public DbAdapter(Context context) {
        this.context = context;
    }

    public void abrir() {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    public ArrayList<Transaccion> listaGastosMes(String mes, String anio) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //Date date = sdf.parse(dateString);
        if (Integer.valueOf(mes) < 10 && !mes.contains("0")) {
            mes = "0" + mes;
        }
        Cursor c = db.rawQuery("SELECT * FROM " + TABLA_TRANSACCION +
                " JOIN CATEGORIA ON " + TABLA_TRANSACCION +
                ".CATEGORIA_ID=CATEGORIA.ID  WHERE FECHA LIKE '%/" + mes + "/" + anio + "' ORDER BY CATEGORIA", null);

        ArrayList<Transaccion> listaGastos = new ArrayList<Transaccion>();

        if (c.moveToFirst()) {

            while (!c.isAfterLast()) {
                try {

                    String categoria = c.getString(c.getColumnIndex("CATEGORIA"));
                    String colorCategoria = c.getString(c.getColumnIndex("COLOR_CATEGORIA"));
                    double monto = c.getDouble(c.getColumnIndex("MONTO"));
                    boolean fijo = c.getInt(c.getColumnIndex("FIJO")) != 0;
                    String fecha = c.getString(c.getColumnIndex("FECHA"));
                    String detalle = c.getString(c.getColumnIndex("DETALLE"));
                    Gasto gasto = new Gasto(categoria, colorCategoria, monto, fijo, fecha, detalle);
                    listaGastos.add(gasto);

                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
                c.moveToNext();
            }

            return listaGastos;
        } else {
            return listaGastos;
        }

    }

    public ArrayList<Transaccion> listaGastosDia(String dia, String mes, String anio) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //Date date = sdf.parse(dateString);
        if (Integer.valueOf(dia) < 10 && !dia.contains("0")) {
            dia = "0" + dia;
        }
        if (Integer.valueOf(mes) < 10 && !mes.contains("0")) {
            mes = "0" + mes;
        }
        Log.i("FECHAS BASE DE DATOS:", dia + "/" + mes + "/" + anio);
        Cursor c = db.rawQuery("SELECT * FROM " + TABLA_TRANSACCION +
                " JOIN CATEGORIA ON " + TABLA_TRANSACCION +
                ".CATEGORIA_ID=CATEGORIA.ID  WHERE FECHA = '" + dia + "/" + mes + "/" + anio + "' ORDER BY CATEGORIA", null);

        ArrayList<Transaccion> listaGastos = new ArrayList<Transaccion>();

        if (c.moveToFirst()) {

            while (!c.isAfterLast()) {
                try {

                    String categoria = c.getString(c.getColumnIndex("CATEGORIA"));
                    String colorCategoria = c.getString(c.getColumnIndex("COLOR_CATEGORIA"));
                    double monto = c.getDouble(c.getColumnIndex("MONTO"));
                    boolean fijo = c.getInt(c.getColumnIndex("FIJO")) != 0;
                    String fecha = c.getString(c.getColumnIndex("FECHA"));
                    String detalle = c.getString(c.getColumnIndex("DETALLE"));
                    Gasto gasto = new Gasto(categoria, colorCategoria, monto, fijo, fecha, detalle);
                    listaGastos.add(gasto);

                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
                c.moveToNext();
            }

            return listaGastos;
        } else {
            return listaGastos;
        }

    }

    public ArrayList<Transaccion> listaGastosTotal() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //Date date = sdf.parse(dateString);

        Cursor c = db.rawQuery("SELECT * FROM " + TABLA_TRANSACCION +
                " JOIN CATEGORIA ON " + TABLA_TRANSACCION +
                ".CATEGORIA_ID=CATEGORIA.ID ORDER BY CATEGORIA", null);

        ArrayList<Transaccion> listaGastos = new ArrayList<Transaccion>();

        if (c.moveToFirst()) {

            while (!c.isAfterLast()) {
                try {

                    String categoria = c.getString(c.getColumnIndex("CATEGORIA"));
                    String colorCategoria = c.getString(c.getColumnIndex("COLOR_CATEGORIA"));
                    double monto = c.getDouble(c.getColumnIndex("MONTO"));
                    boolean fijo = c.getInt(c.getColumnIndex("FIJO")) != 0;
                    String fecha = c.getString(c.getColumnIndex("FECHA"));
                    String detalle = c.getString(c.getColumnIndex("DETALLE"));
                    Gasto gasto = new Gasto(categoria, colorCategoria, monto, fijo, fecha, detalle);
                    listaGastos.add(gasto);

                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
                c.moveToNext();
            }

            return listaGastos;
        } else {
            return listaGastos;
        }

    }

    public void insertarGasto(String nombreCategoria,
                              int indiceColorCategoria,
                              double monto,
                              boolean fijo,
                              String fecha,
                              String detalles) {
        db.execSQL("INSERT INTO " + TABLA_TRANSACCION +
                        " VALUES (null,?,?,1,?,?,?)",
                new String[]{
                        String.valueOf(monto),
                        String.valueOf(indiceColorCategoria),
                        String.valueOf(fijo),
                        String.valueOf(fecha),
                        String.valueOf(detalles)});
    }

    public int obtenerColor(String color) {
        Cursor cursor = db.rawQuery("SELECT id FROM " + TABLA_CATEGORIA + " WHERE color_categoria=?", new String[]{color});
        int indiceColor = 0;
        if (cursor.moveToFirst()) {
            indiceColor = cursor.getInt(cursor.getColumnIndex("ID"));
        }
        return indiceColor;
    }


    public void close() {
        db.close();
    }
}

package ar.com.sumo.gestor_de_gastos.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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


    public ArrayList<Transaccion> listaGastosMes(int mes, int año) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //Date date = sdf.parse(dateString);
        Cursor c = db.rawQuery("SELECT * FROM " + TABLA_TRANSACCION +
                " JOIN CATEGORIA ON " + TABLA_TRANSACCION +
                ".CATEGORIA_ID=CATEGORIA.ID " , null);

        ArrayList<Transaccion> listaGastos = new ArrayList<Transaccion>();

        if (c.moveToFirst()) {

            while (!c.isAfterLast()) {
                try {

                    String categoria = c.getString(c.getColumnIndex("CATEGORIA"));
                    String colorCategoria = c.getString(c.getColumnIndex("COLOR_CATEGORIA"));
                    double monto = c.getDouble(c.getColumnIndex("MONTO"));
                    boolean fijo = c.getInt(c.getColumnIndex("FIJO")) != 0;
                    //Date fecha = sdf.parse(c.getString(c.getColumnIndex("FECHA")));
                    String detalle = c.getString(c.getColumnIndex("DETALLE"));
                    Gasto gasto = new Gasto(categoria, colorCategoria, monto, fijo, new Date(), detalle);
                    listaGastos.add(gasto);
//                    sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_CATEGORIA +
//                            " (ID INTEGER PRIMARY KEY AUTOINCREMENT, MONTO REAL, " +
//                            "CATEGORIA_ID INTEGER,TIPO_ID INTEGER, " +
//                            "FIJO NUMERIC," +
//                            "FECHA NUMERIC, " +
//                            "FOREIGN KEY (CATEGORIA_ID) REFERENCES CATEGORIA(ID)," +
//                            "FOREIGN KEY (TIPO_ID) REFERENCES TIPO(ID))");
                    //           Transaccion(String nombreCategoria, String colorCategoria, double monto, boolean fijo, Date fecha)
//                contactos.add(mCursor.getString(mCursor.getColumnIndex("DISPLAY_NAME")));
//                contactosCel.add(mCursor.getString(mCursor.getColumnIndex("NUMBER")));


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
                        fecha,
                        detalles});
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

package ar.com.sumo.gestor_de_gastos.Services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "gestor_de_gastos";
    private static final int DB_VERSION = 2;
    private static final String TABLA_TRANSACCION = "transaccion";
    private static final String TABLA_CATEGORIA = "categoria";//entretenimiento ... etc
    private static final String TABLA_TIPO = "tipo";//gasto o ingreso

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLA_CATEGORIA +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT,CATEGORIA TEXT,COLOR_CATEGORIA TEXT )");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES (null,?,?)", new String[]{"Hogar", "#F14400"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES (null,?,?)", new String[]{"Factura", "#E4FF15"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Alimentos", "#00BEDD"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Provisiones", "#E60B0B"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Salud", "#32E900"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Indumentaria", "#D800D9"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Entretenimiento", "#1574FF"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Niños", "#C18F14"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Gasolina", "#577BFF"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Vehiculo", "#A59CFF"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Vacaciones", "#A1DB00"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Regalos", "#A63B11"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"General", "#AAB23F"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Deporte", "#F14400"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Sueldo", "#00FF2A"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Depositos", "#FFDD17"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Ahorros", "#0990CC"});

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLA_TIPO +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TIPO TEXT)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_TIPO + " VALUES(null,?)", new String[]{"Gasto"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_TIPO + " VALUES(null,?)", new String[]{"Ingreso"});



        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLA_TRANSACCION +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "MONTO REAL, " +
                "CATEGORIA_ID INTEGER,TIPO_ID INTEGER, " +
                "FIJO NUMERIC," +
                "FECHA NUMERIC, " +
                "DETALLE TEXT," +
                "FOREIGN KEY (CATEGORIA_ID) REFERENCES CATEGORIA(ID)," +
                "FOREIGN KEY (TIPO_ID) REFERENCES TIPO(ID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLA_CATEGORIA +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT,CATEGORIA TEXT,COLOR_CATEGORIA TEXT )");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES (null,?,?)", new String[]{"Hogar", "#F14400"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES (null,?,?)", new String[]{"Factura", "#E4FF15"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Alimentos", "#00BEDD"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Provisiones", "#E60B0B"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Salud", "#32E900"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Indumentaria", "#D800D9"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Entretenimiento", "#1574FF"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Niños", "#C18F14"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Gasolina", "#577BFF"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Vehiculo", "#A59CFF"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Vacaciones", "#A1DB00"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Regalos", "#A63B11"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"General", "#AAB23F"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Deporte", "#F14400"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Sueldo", "#00FF2A"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Depositos", "#FFDD17"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_CATEGORIA + " VALUES(null,?,?)", new String[]{"Ahorros", "#0990CC"});

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLA_TIPO +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TIPO TEXT)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_TIPO + " VALUES(null,?)", new String[]{"Gasto"});
        sqLiteDatabase.execSQL("INSERT INTO " + TABLA_TIPO + " VALUES(null,?)", new String[]{"Ingreso"});


        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLA_TRANSACCION +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "MONTO REAL, " +
                "CATEGORIA_ID INTEGER," +
                "TIPO_ID INTEGER, " +
                "FIJO NUMERIC," +
                "FECHA NUMERIC, " +
                "DETALLE TEXT," +
                "FOREIGN KEY (CATEGORIA_ID) REFERENCES CATEGORIA(ID)," +
                "FOREIGN KEY (TIPO_ID) REFERENCES TIPO(ID))");
    }

}
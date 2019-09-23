package ar.com.sumo.gestor_de_gastos.Transacciones;

import java.util.Date;

public class Transaccion {
    private CategoriaTransaccion categoria;
    private double monto;
    private boolean fijo;
    private String fecha;
    private String detalle;

    public Transaccion(String nombreCategoria, String colorCategoria, double monto, boolean fijo, String fecha, String detalle) {
        this.categoria = new CategoriaTransaccion(nombreCategoria, colorCategoria);
        this.monto = monto;
        this.fijo = fijo;
        this.fecha = fecha;
        this.detalle = detalle;
    }


    public CategoriaTransaccion getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaTransaccion categoria) {
        this.categoria = categoria;
    }
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }public boolean isFijo() {
        return fijo;
    }
    public void setFijo(boolean fijo) {
        this.fijo = fijo;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}





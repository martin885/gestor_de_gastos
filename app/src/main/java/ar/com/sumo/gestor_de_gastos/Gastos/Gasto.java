package ar.com.sumo.gestor_de_gastos.Gastos;

import java.util.Date;

import ar.com.sumo.gestor_de_gastos.Transacciones.CategoriaTransaccion;
import ar.com.sumo.gestor_de_gastos.Transacciones.Transaccion;

public class Gasto extends Transaccion {
    public Gasto(String nombreCategoria, String colorCategoria, double monto, boolean fijo, Date fecha, String detalle) {
        super(nombreCategoria, colorCategoria, monto, fijo, fecha, detalle);
    }

    @Override
    public CategoriaTransaccion getCategoria() {
        return super.getCategoria();
    }

    @Override
    public void setCategoria(CategoriaTransaccion categoria) {
        super.setCategoria(categoria);
    }

    @Override
    public double getMonto() {
        return super.getMonto();
    }

    @Override
    public void setMonto(double monto) {
        if (monto > 0) {
            monto = monto * (-1);
        }
        super.setMonto(monto);
    }

    @Override
    public boolean isFijo() {
        return super.isFijo();
    }

    @Override
    public void setFijo(boolean fijo) {
        super.setFijo(fijo);
    }

    @Override
    public Date getFecha() {
        return super.getFecha();
    }

    @Override
    public String getDetalle() {
        return super.getDetalle();
    }

    @Override
    public void setFecha(Date fecha) {
        super.setFecha(fecha);
    }

    @Override
    public void setDetalle(String detalle) {
        super.setDetalle(detalle);
    }

}

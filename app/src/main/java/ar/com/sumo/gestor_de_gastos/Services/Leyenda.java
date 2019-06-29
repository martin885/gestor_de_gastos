package ar.com.sumo.gestor_de_gastos.Services;

import ar.com.sumo.gestor_de_gastos.Transacciones.CategoriaTransaccion;

public class Leyenda {

    CategoriaTransaccion categoria;

    double valorTotal;

    public Leyenda(CategoriaTransaccion categoria, double valorTotal) {
        this.categoria = categoria;
        this.valorTotal = valorTotal;
    }


    public CategoriaTransaccion getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaTransaccion categoria) {
        this.categoria = categoria;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}

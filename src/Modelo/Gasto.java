package Modelo;

public class Gasto {

    private String descripcion;
    private String categoria;
    private double monto;

    public Gasto(String descripcion, String categoria, double monto) {
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getMonto() {
        return monto;
    }
}

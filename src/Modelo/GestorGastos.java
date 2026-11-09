package Modelo;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class GestorGastos {

    private ArrayList<Gasto> gastos;
    private double presupuesto;

    public GestorGastos() {
        gastos = new ArrayList<Gasto>();
        presupuesto = 0;
        cargarTXT();
    }

    public boolean guardarPresupuesto(double cantidad) {

        if (!Double.isFinite(cantidad) || cantidad <= 0
                || !gastos.isEmpty()) {
            return false;
        }

        presupuesto = cantidad;
        guardarTXT();
        return true;
    }

    public boolean registrarGasto(String descripcion,
                                  String categoria,
                                  double monto) {

        if (descripcion == null || descripcion.trim().isEmpty()
                || categoria == null || categoria.trim().isEmpty()
                || !Double.isFinite(monto) || monto <= 0
                || monto > calcularSaldo() || presupuesto <= 0
                || descripcion.contains(";") || categoria.contains(";")
                || descripcion.contains("\n") || categoria.contains("\n")) {
            return false;
        }

        Gasto nuevo = new Gasto(descripcion, categoria, monto);
        gastos.add(nuevo);
        guardarTXT();

        return true;
    }

    public ArrayList<Gasto> consultarGastos() {
        return new ArrayList<Gasto>(gastos);
    }

    public double calcularTotal() {

        double total = 0;

        for (Gasto gasto : gastos) {
            total = total + gasto.getMonto();
        }

        return total;
    }

    public double calcularSaldo() {
        return presupuesto - calcularTotal();
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public int contarGastos() {
        return UtilidadesGenericas.contarElementos(gastos);
    }

    public void guardarTXT() {

        try {
            PrintWriter archivo = new PrintWriter(
                    new FileWriter("gastos.txt"));

            archivo.println(presupuesto);

            for (Gasto gasto : gastos) {
                archivo.println(
                        gasto.getDescripcion() + ";" +
                        gasto.getCategoria() + ";" +
                        gasto.getMonto());
            }

            archivo.close();

        } catch (Exception e) {
            System.out.println("Error al guardar el archivo.");
        }
    }

    public void cargarTXT() {

        try {
            File archivo = new File("gastos.txt");

            if (!archivo.exists()) {
                return;
            }

            Scanner lector = new Scanner(archivo);

            if (lector.hasNextLine()) {
                presupuesto = Double.parseDouble(lector.nextLine());
            }

            while (lector.hasNextLine()) {

                String linea = lector.nextLine();
                String[] datos = linea.split(";");

                if (datos.length == 3) {

                    String descripcion = datos[0];
                    String categoria = datos[1];
                    double monto = Double.parseDouble(datos[2]);

                    gastos.add(new Gasto(descripcion, categoria, monto));
                }
            }

            lector.close();

        } catch (Exception e) {
            System.out.println("Error al cargar el archivo.");
        }
    }
}

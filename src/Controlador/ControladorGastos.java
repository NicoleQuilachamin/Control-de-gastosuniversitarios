package Controlador;

import Modelo.Gasto;
import Modelo.GestorGastos;
import Vista.VentanaGastos;

import javax.swing.JOptionPane;

public class ControladorGastos {

    private GestorGastos gestor;
    private VentanaGastos vista;

    public ControladorGastos(GestorGastos gestor, VentanaGastos vista) {
        this.gestor = gestor;
        this.vista = vista;

        vista.btnPresupuesto.addActionListener(e -> guardarPresupuesto());
        vista.btnRegistrar.addActionListener(e -> registrarGasto());
        vista.btnConsultar.addActionListener(e -> consultarGastos());
        vista.btnCalcular.addActionListener(e -> calcularSaldo());
    }

    private void guardarPresupuesto() {
        String texto = vista.txtPresupuesto.getText().trim();

        if (texto.isEmpty()) {
            mostrarError("Ingrese el presupuesto inicial.");
            return;
        }

        try {
            double cantidad = Double.parseDouble(texto);

            if (!Double.isFinite(cantidad) || cantidad <= 0) {
                mostrarError("El presupuesto debe ser mayor a cero.");
                return;
            }

            if (gestor.guardarPresupuesto(cantidad)) {
                JOptionPane.showMessageDialog(vista,
                        "Presupuesto guardado correctamente.");
                calcularSaldo();
            } else {
                mostrarError("No se puede cambiar el presupuesto después de registrar gastos.");
            }

        } catch (NumberFormatException e) {
            mostrarError("Ingrese un número válido para el presupuesto.");
        }
    }

    private void registrarGasto() {
        String descripcion = vista.txtDescripcion.getText().trim();
        String textoMonto = vista.txtMonto.getText().trim();

        if (gestor.getPresupuesto() <= 0) {
            mostrarError("Primero debe ingresar un presupuesto.");
            return;
        }

        if (descripcion.isEmpty()) {
            mostrarError("Ingrese la descripción del gasto.");
            return;
        }

        if (textoMonto.isEmpty()) {
            mostrarError("Ingrese el monto del gasto.");
            return;
        }

        try {
            double monto = Double.parseDouble(textoMonto);

            if (!Double.isFinite(monto) || monto <= 0) {
                mostrarError("El monto debe ser mayor a cero.");
                return;
            }

            if (monto > gestor.calcularSaldo()) {
                mostrarError("Saldo insuficiente.");
                return;
            }

            String categoria = vista.cbCategoria.getSelectedItem().toString();

            if (gestor.registrarGasto(descripcion, categoria, monto)) {
                JOptionPane.showMessageDialog(vista,
                        "Gasto registrado correctamente.");

                vista.txtDescripcion.setText("");
                vista.txtMonto.setText("");

                consultarGastos();

            } else {
                mostrarError("No se pudo registrar el gasto.");
            }

        } catch (NumberFormatException e) {
            mostrarError("Ingrese un monto numérico válido.");
        }
    }

    private void consultarGastos() {
        vista.modeloTabla.setRowCount(0);

        for (Gasto gasto : gestor.consultarGastos()) {
            vista.modeloTabla.addRow(new Object[]{
                gasto.getDescripcion(),
                gasto.getCategoria(),
                String.format("%.2f", gasto.getMonto())
            });
        }

        System.out.println("Gastos registrados: " + gestor.contarGastos());

        calcularSaldo();
    }

    private void calcularSaldo() {
        vista.lblTotal.setText(
                String.format("Total gastado: $%.2f", gestor.calcularTotal()));

        vista.lblSaldo.setText(
                String.format("Saldo disponible: $%.2f", gestor.calcularSaldo()));
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                vista,
                mensaje,
                "Mensaje",
                JOptionPane.PLAIN_MESSAGE
        );
    }
}

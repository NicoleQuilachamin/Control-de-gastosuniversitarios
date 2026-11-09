package app;

import Modelo.GestorGastos;
import Vista.VentanaGastos;
import Controlador.ControladorGastos;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        if (args.length > 0) {
            System.out.println("Modo: " + args[0]);
        }

        SwingUtilities.invokeLater(() -> {

            GestorGastos gestor = new GestorGastos();

            VentanaGastos vista = new VentanaGastos();

            new ControladorGastos(gestor, vista);

            vista.setVisible(true);
        });
    }
}

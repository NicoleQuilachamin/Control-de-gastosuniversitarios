package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaGastos extends JFrame {

    public JTextField txtPresupuesto;
    public JTextField txtDescripcion;
    public JTextField txtMonto;

    public JComboBox<String> cbCategoria;

    public JButton btnPresupuesto;
    public JButton btnRegistrar;
    public JButton btnConsultar;
    public JButton btnCalcular;

    public JTable tablaGastos;
    public DefaultTableModel modeloTabla;

    public JLabel lblTotal;
    public JLabel lblSaldo;

    public VentanaGastos() {

        setTitle("Control de Gastos Universitarios");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        Color rosa = new Color(255, 225, 235);
        getContentPane().setBackground(rosa);

        JPanel panelDatos = new JPanel(new GridLayout(4, 2, 10, 10));
        panelDatos.setBackground(rosa);

        txtPresupuesto = new JTextField();
        txtDescripcion = new JTextField();
        txtMonto = new JTextField();

        cbCategoria = new JComboBox<>();
        cbCategoria.addItem("Alimentación");
        cbCategoria.addItem("Transporte");
        cbCategoria.addItem("Materiales");
        cbCategoria.addItem("Otros");

        btnPresupuesto = new JButton("Guardar presupuesto");

        panelDatos.add(new JLabel("Presupuesto inicial ($):"));
        panelDatos.add(txtPresupuesto);

        panelDatos.add(new JLabel("Descripción del gasto:"));
        panelDatos.add(txtDescripcion);

        panelDatos.add(new JLabel("Categoría:"));
        panelDatos.add(cbCategoria);

        panelDatos.add(new JLabel("Monto ($):"));
        panelDatos.add(txtMonto);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBackground(rosa);

        btnRegistrar = new JButton("Registrar gasto");
        btnConsultar = new JButton("Consultar gastos");
        btnCalcular = new JButton("Calcular saldo");

        panelBotones.add(btnPresupuesto);
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnCalcular);

        modeloTabla = new DefaultTableModel(
            new Object[]{"Descripción", "Categoría", "Monto"}, 0
        );

        tablaGastos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaGastos);

        JPanel panelResultados = new JPanel(new GridLayout(2, 1));
        panelResultados.setBackground(rosa);

        lblTotal = new JLabel("Total gastado: $0.00");
        lblSaldo = new JLabel("Saldo disponible: $0.00");

        panelResultados.add(lblTotal);
        panelResultados.add(lblSaldo);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(rosa);

        panelSuperior.add(panelDatos, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelResultados, BorderLayout.SOUTH);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio1;

/**
 *
 * @author isabr
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio1 extends JFrame {
    private JTextField nombreField1;
    private JTextField diaField1;
    private JTextField horaField1;
    private JTextField nombreField2;
    private JTextField diaField2;
    private JTextField horaField2;
    private JTextArea resultadoArea;

    public Ejercicio1() {
        setTitle("Ingreso al Centro de Labores");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2));

        panel.add(new JLabel("Nombre del empleado 1:"));
        nombreField1 = new JTextField();
        panel.add(nombreField1);

        panel.add(new JLabel("Día del ingreso 1:"));
        diaField1 = new JTextField();
        panel.add(diaField1);

        panel.add(new JLabel("Hora del ingreso 1 (HH.MM):"));
        horaField1 = new JTextField();
        panel.add(horaField1);

        panel.add(new JLabel("Nombre del empleado 2:"));
        nombreField2 = new JTextField();
        panel.add(nombreField2);

        panel.add(new JLabel("Día del ingreso 2:"));
        diaField2 = new JTextField();
        panel.add(diaField2);

        panel.add(new JLabel("Hora del ingreso 2 (HH.MM):"));
        horaField2 = new JTextField();
        panel.add(horaField2);

        JButton calcularButton = new JButton("Calcular");
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularIngreso();
            }
        });
        
        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);

        add(panel, BorderLayout.CENTER);
        add(calcularButton, BorderLayout.SOUTH);
        add(new JScrollPane(resultadoArea), BorderLayout.EAST);
    }

    private void calcularIngreso() {
        String nombre1 = nombreField1.getText();
        String dia1 = diaField1.getText();
        double hora1 = Double.parseDouble(horaField1.getText());

        String nombre2 = nombreField2.getText();
        String dia2 = diaField2.getText();
        double hora2 = Double.parseDouble(horaField2.getText());

        Thread usuario1 = new IngresoUsuario(nombre1, dia1, hora1);
        Thread usuario2 = new IngresoUsuario(nombre2, dia2, hora2);

        usuario1.start();
        usuario2.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ejercicio1().setVisible(true);
            }
        });
    }

    class IngresoUsuario extends Thread {
        private String nombre;
        private String dia;
        private double hora;

        public IngresoUsuario(String nombre, String dia, double hora) {
            this.nombre = nombre;
            this.dia = dia;
            this.hora = hora;
        }

        @Override
        public void run() {
            if (hora > 8.00) {
                mostrarResultado(nombre + " llegó tarde el día " + dia);
            } else {
                mostrarResultado(nombre + " llegó temprano el día " + dia);
            }
        }

        private void mostrarResultado(String resultado) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    resultadoArea.append(resultado + "\n");
                }
            });
        }
    }
}

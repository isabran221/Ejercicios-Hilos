/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio6;

/**
 *
 * @author isabr
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio6 extends JFrame {
    private JComboBox<String> estacionesComboBox;
    private JTextArea displayArea;
    private JButton mostrarButton;

    public Ejercicio6() {
        setTitle("Mostrar Estaciones del Año");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] estaciones = {"Primavera", "Verano", "Otoño", "Invierno"};
        estacionesComboBox = new JComboBox<>(estaciones);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 16));

        mostrarButton = new JButton("Mostrar Meses");
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarMeses();
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(estacionesComboBox, BorderLayout.NORTH);
        panel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
        panel.add(mostrarButton, BorderLayout.SOUTH);

        add(panel);
    }

    private void mostrarMeses() {
        String estacion = (String) estacionesComboBox.getSelectedItem();
        new Thread(new MostrarMesesRunnable(estacion)).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ejercicio6().setVisible(true);
            }
        });
    }

    class MostrarMesesRunnable implements Runnable {
        private String estacion;

        public MostrarMesesRunnable(String estacion) {
            this.estacion = estacion;
        }

        @Override
        public void run() {
            try {
                displayArea.setText(""); // Clear previous text
                switch (estacion) {
                    case "Primavera":
                        mostrarMes("Marzo");
                        mostrarMes("Abril");
                        mostrarMes("Mayo");
                        break;
                    case "Verano":
                        mostrarMes("Junio");
                        mostrarMes("Julio");
                        mostrarMes("Agosto");
                        break;
                    case "Otoño":
                        mostrarMes("Septiembre");
                        mostrarMes("Octubre");
                        mostrarMes("Noviembre");
                        break;
                    case "Invierno":
                        mostrarMes("Diciembre");
                        mostrarMes("Enero");
                        mostrarMes("Febrero");
                        break;
                    default:
                        displayArea.append("Estación no válida.\n");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void mostrarMes(String mes) throws InterruptedException {
            displayArea.append(mes + "\n");
            Thread.sleep(1000);
        }
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio3;

/**
 *
 * @author isabr
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio3 extends JFrame {
    private JTextField nombreField;
    private JTextArea displayArea;
    private JButton startButton;

    public Ejercicio3() {
        setTitle("Simulación de Carrera de Atleta");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2));

        panel.add(new JLabel("Nombre del atleta:"));
        nombreField = new JTextField();
        panel.add(nombreField);

        startButton = new JButton("Iniciar Carrera");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarCarrera();
            }
        });
        panel.add(startButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 16));

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
    }

    private void iniciarCarrera() {
        String nombre = nombreField.getText();
        Thread carrera = new CarreraAtleta(nombre);
        carrera.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ejercicio3().setVisible(true);
            }
        });
    }

    class CarreraAtleta extends Thread {
        private String nombre;

        public CarreraAtleta(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public void run() {
            for (int km = 1; km <= 30; km++) {
                mostrarProgreso(km);
                try {
                    Thread.sleep(3500); // Simulación de tiempo corriendo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mostrarResultado("¡" + nombre + " ha llegado a la meta!");
        }

        private void mostrarProgreso(int km) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    displayArea.append("Kilómetro " + km + "\n");
                }
            });
        }

        private void mostrarResultado(String resultado) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    displayArea.append(resultado + "\n");
                }
            });
        }
    }
}

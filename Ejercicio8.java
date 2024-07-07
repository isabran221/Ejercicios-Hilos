/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio8;

/**
 *
 * @author isabr
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio8 extends JFrame {
    private JTextArea displayArea;
    private JButton startButton;

    public Ejercicio8() {
        setTitle("Simulación de Ascensor");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 16));

        startButton = new JButton("Iniciar");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSimulacion();
            }
        });

        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);
    }

    private void iniciarSimulacion() {
        Thread ascensor = new Thread(new Ascensor());
        ascensor.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ejercicio8().setVisible(true);
            }
        });
    }

    class Ascensor implements Runnable {
        private int pisoActual = 0;

        @Override
        public void run() {
            try {
                // Simular el ascensor subiendo y bajando
                for (int i = 1; i <= 10; i++) {
                    subir();
                    bajar();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void subir() throws InterruptedException {
            for (int i = pisoActual; i <= 5; i++) {
                pisoActual = i;
                mostrarProgreso("Ascensor subiendo al piso: " + pisoActual);
                Thread.sleep(1000); // Simulación del tiempo que tarda en subir
            }
        }

        private void bajar() throws InterruptedException {
            for (int i = pisoActual; i >= 0; i--) {
                pisoActual = i;
                mostrarProgreso("Ascensor bajando al piso: " + pisoActual);
                Thread.sleep(1000); // Simulación del tiempo que tarda en bajar
            }
        }

        private void mostrarProgreso(String mensaje) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    displayArea.append(mensaje + "\n");
                }
            });
        }
    }
}

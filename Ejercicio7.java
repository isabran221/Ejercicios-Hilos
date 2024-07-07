/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio7;

/**
 *
 * @author isabr
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio7 extends JFrame {
    private JTextArea displayArea;
    private JButton startButton;

    public Ejercicio7() {
        setTitle("Pitufos Comiendo Pan con Queso");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 16));

        startButton = new JButton("Iniciar");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarHilos();
            }
        });

        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);
    }

    private void iniciarHilos() {
        String[] nombres = {
            "Papa Pitufo", "Pitufina", "Filósofo", "Pintor", "Gruñón", 
            "Bromista", "Dormilón", "Tontín", "Bonachón", "Romántico"
        };

        for (String nombre : nombres) {
            Thread hilo = new Thread(new Pitufo(nombre));
            hilo.start();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ejercicio7().setVisible(true);
            }
        });
    }

    class Pitufo implements Runnable {
        private String nombre;

        public Pitufo(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                mostrarProgreso(nombre + " come un " + i + "° pan con queso.");
                try {
                    Thread.sleep((int) (Math.random() * 1000) + 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mostrarResultado(nombre + " terminó.");
        }

        private void mostrarProgreso(String mensaje) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    displayArea.append(mensaje + "\n");
                }
            });
        }

        private void mostrarResultado(String mensaje) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    displayArea.append(mensaje + "\n");
                }
            });
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio10;

/**
 *
 * @author isabr
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio10 extends JFrame {
    private JTextArea displayArea;
    private JButton startButton;

    public Ejercicio10() {
        setTitle("Simulación de Pitufos y Azrael");
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
                iniciarSimulacion();
            }
        });

        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);
    }

    private void iniciarSimulacion() {
        String[] pitufos = {
            "Papa Pitufo", "Pitufina", "Filósofo", "Pintor", "Gruñón", 
            "Bromista", "Dormilón", "Tontín", "Bonachón", "Romántico"
        };

        for (String pitufo : pitufos) {
            Thread hiloPitufo = new Thread(new Pitufo(pitufo), pitufo);
            hiloPitufo.start();
        }

        Thread hiloAzrael = new Thread(new Azrael(pitufos));
        hiloAzrael.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ejercicio10().setVisible(true);
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
            try {
                while (true) {
                    mostrarProgreso(nombre + " está corriendo.");
                    Thread.sleep((int) (Math.random() * 3000) + 1000);
                }
            } catch (InterruptedException e) {
                mostrarProgreso(nombre + " ha sido atrapado por Azrael.");
            }
        }
    }

    class Azrael implements Runnable {
        private String[] pitufos;
        private boolean[] atrapados;

        public Azrael(String[] pitufos) {
            this.pitufos = pitufos;
            this.atrapados = new boolean[pitufos.length];
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < pitufos.length; i++) {
                    Thread.sleep((int) (Math.random() * 5000) + 2000);
                    atraparPitufo(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void atraparPitufo(int index) {
            if (!atrapados[index]) {
                atrapados[index] = true;
                mostrarProgreso("Azrael ha atrapado a " + pitufos[index] + ".");
                for (Thread thread : Thread.getAllStackTraces().keySet()) {
                    if (thread.getName().equals(pitufos[index])) {
                        thread.interrupt();
                    }
                }
            }
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio4;

/**
 *
 * @author isabr
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio4 extends JFrame {
    private JTextField mascotaField1;
    private JTextField mascotaField2;
    private JTextField mascotaField3;
    private JTextArea displayArea;
    private JButton startButton;

    public Ejercicio4() {
        setTitle("Simulación de Carrera de Mascotas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2));

        panel.add(new JLabel("Nombre de la mascota 1:"));
        mascotaField1 = new JTextField();
        panel.add(mascotaField1);

        panel.add(new JLabel("Nombre de la mascota 2:"));
        mascotaField2 = new JTextField();
        panel.add(mascotaField2);

        panel.add(new JLabel("Nombre de la mascota 3:"));
        mascotaField3 = new JTextField();
        panel.add(mascotaField3);

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
        String mascota1 = mascotaField1.getText();
        String mascota2 = mascotaField2.getText();
        String mascota3 = mascotaField3.getText();

        Thread carrera1 = new CarreraMascota(mascota1, 4);
        Thread carrera2 = new CarreraMascota(mascota2, 5);
        Thread carrera3 = new CarreraMascota(mascota3, 6);

        carrera1.start();
        carrera2.start();
        carrera3.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ejercicio4().setVisible(true);
            }
        });
    }

    class CarreraMascota extends Thread {
        private String nombre;
        private int prioridad;

        public CarreraMascota(String nombre, int prioridad) {
            this.nombre = nombre;
            this.prioridad = prioridad;
            setPriority(prioridad);
        }

        @Override
        public void run() {
            for (int mt = 1; mt <= 30; mt++) {
                mostrarProgreso(mt, nombre);
                try {
                    Thread.sleep((int) (Math.random() * 500) + 100); // Simulación de tiempo corriendo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mostrarResultado("¡" + nombre + " ha llegado a la meta!");
        }

        private void mostrarProgreso(int mt, String nombre) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    displayArea.append(nombre + " ha recorrido " + mt + " metros\n");
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

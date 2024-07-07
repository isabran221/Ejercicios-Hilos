/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio2;

/**
 *
 * @author isabr
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio2 extends JFrame {
    private JTextArea displayArea;
    private JButton startButton;

    public Ejercicio2() {
        setTitle("Mostrar Números con Retardo");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 16));

        startButton = new JButton("Iniciar");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarConteo();
            }
        });

        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);
    }

    private void iniciarConteo() {
        Thread contarNumeros = new ContarNumeros();
        contarNumeros.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ejercicio2().setVisible(true);
            }
        });
    }

    class ContarNumeros extends Thread {
        @Override
        public void run() {
            for (int num = 1; num <= 20; num++) {
                mostrarNumero(num);
                try {
                    Thread.sleep(1500); // Retardo de 1500 ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void mostrarNumero(int num) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    displayArea.append(num + "\n");
                }
            });
        }
    }
}

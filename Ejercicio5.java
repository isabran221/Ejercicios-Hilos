/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio5;

/**
 *
 * @author isabr
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio5 extends JFrame {
    private JTextArea displayArea;
    private JButton startButton;

    public Ejercicio5() {
        setTitle("Imprimir Números Pares e Impares");
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
        Thread pares1 = new HiloPares();
        Thread pares2 = new HiloPares();
        Thread impares1 = new HiloImpares();
        Thread impares2 = new HiloImpares();

        pares1.start();
        pares2.start();
        impares1.start();
        impares2.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ejercicio5().setVisible(true);
            }
        });
    }

    class HiloPares extends Thread {
        @Override
        public void run() {
            int contador = 0;
            StringBuilder resultado = new StringBuilder();
            for (int n = 1; n <= 10; n++) {
                if (n % 2 == 0) {
                    resultado.append(n).append("\n");
                    contador += n;
                }
            }
            resultado.append("La suma de los pares es: ").append(contador).append("\n");
            resultado.append("***********************\n");
            mostrarResultado(resultado.toString());
        }
    }

    class HiloImpares extends Thread {
        @Override
        public void run() {
            int contador = 0;
            StringBuilder resultado = new StringBuilder();
            for (int n = 1; n <= 10; n++) {
                if (n % 2 != 0) {
                    resultado.append(n).append("\n");
                    contador += n;
                }
            }
            resultado.append("La suma de los impares es: ").append(contador).append("\n");
            mostrarResultado(resultado.toString());
        }
    }

    private void mostrarResultado(String resultado) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                displayArea.append(resultado);
            }
        });
    }
}

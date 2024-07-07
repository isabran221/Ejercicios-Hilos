/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio9;

/**
 *
 * @author isabr
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Ejercicio9 extends JFrame {
    private JTextField nombreField;
    private JTextArea displayArea;
    private JButton iniciarButton;
    private Map<String, Thread> usuariosActivos;

    public Ejercicio9() {
        setTitle("Sesiones de Usuarios");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        nombreField = new JTextField();
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 16));

        iniciarButton = new JButton("Iniciar Sesión");
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Nombre del Usuario:"), BorderLayout.WEST);
        panel.add(nombreField, BorderLayout.CENTER);
        panel.add(iniciarButton, BorderLayout.EAST);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        usuariosActivos = new HashMap<>();
    }

    private void iniciarSesion() {
        String nombre = nombreField.getText();
        if (!nombre.isEmpty() && !usuariosActivos.containsKey(nombre)) {
            SesionUsuario sesion = new SesionUsuario(nombre);
            Thread hilo = new Thread(sesion);
            usuariosActivos.put(nombre, hilo);
            hilo.start();
        } else if (usuariosActivos.containsKey(nombre)) {
            mostrarMensaje("El usuario " + nombre + " ya tiene una sesión activa.\n");
        } else {
            mostrarMensaje("El nombre de usuario no puede estar vacío.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ejercicio9().setVisible(true);
            }
        });
    }

    class SesionUsuario implements Runnable {
        private String nombre;
        private int tiempoActivo;

        public SesionUsuario(String nombre) {
            this.nombre = nombre;
            this.tiempoActivo = 0;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    tiempoActivo++;
                    mostrarMensaje("Usuario: " + nombre + " - Tiempo de sesión activa: " + tiempoActivo + " segundos\n");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                mostrarMensaje("La sesión del usuario " + nombre + " ha terminado.\n");
            }
        }
    }

    private void mostrarMensaje(String mensaje) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                displayArea.append(mensaje);
            }
        });
    }
}

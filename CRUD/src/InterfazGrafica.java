import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfazGrafica extends JFrame {

    private JTextField nombreField, numeroField;
    private JTextArea outputArea;

    public InterfazGrafica() {
        setTitle("CRUD de Amigos");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // Componentes de la interfaz
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField();
        JLabel numeroLabel = new JLabel("Número:");
        numeroField = new JTextField();
        JButton agregarBtn = new JButton("Agregar");
        JButton mostrarBtn = new JButton("Mostrar");
        JButton actualizarBtn = new JButton("Actualizar");
        JButton eliminarBtn = new JButton("Eliminar");
        outputArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Añadir los componentes al Frame
        add(nombreLabel);
        add(nombreField);
        add(numeroLabel);
        add(numeroField);
        add(agregarBtn);
        add(mostrarBtn);
        add(actualizarBtn);
        add(eliminarBtn);
        add(scrollPane);

        // Configuración de acciones para los botones
        agregarBtn.addActionListener(e -> agregarAmigo());
        mostrarBtn.addActionListener(e -> mostrarAmigos());
        actualizarBtn.addActionListener(e -> actualizarAmigo());
        eliminarBtn.addActionListener(e -> eliminarAmigo());
    }

    // Método para agregar un amigo
    private void agregarAmigo() {
        String nombre = nombreField.getText();
        String numeroStr = numeroField.getText();

        if (!nombre.isEmpty() && !numeroStr.isEmpty()) {
            try {
                long numero = Long.parseLong(numeroStr);
                String[] datos = {nombre, numeroStr};
                AgregarAmigo.main(datos);
                outputArea.setText("Amigo agregado: " + nombre + " - " + numero);
            } catch (NumberFormatException ex) {
                outputArea.setText("Número inválido.");
            }
        } else {
            outputArea.setText("Por favor, ingresa nombre y número.");
        }
    }

    // Método para mostrar todos los amigos
    private void mostrarAmigos() {
        try {
            MostrarAmigos.main(new String[]{});
            outputArea.setText("Mostrando contactos en la consola.");
        } catch (Exception e) {
            outputArea.setText("Error mostrando amigos.");
        }
    }

    // Método para actualizar un amigo
    private void actualizarAmigo() {
        String nombre = nombreField.getText();
        String numeroStr = numeroField.getText();

        if (!nombre.isEmpty() && !numeroStr.isEmpty()) {
            try {
                long numero = Long.parseLong(numeroStr);
                String[] datos = {nombre, numeroStr};
                ActualizarAmigo.main(datos);
                outputArea.setText("Amigo actualizado: " + nombre + " - " + numero);
            } catch (NumberFormatException ex) {
                outputArea.setText("Número inválido.");
            }
        } else {
            outputArea.setText("Por favor, ingresa nombre y número.");
        }
    }

    // Método para eliminar un amigo
    private void eliminarAmigo() {
        String nombre = nombreField.getText();

        if (!nombre.isEmpty()) {
            String[] datos = {nombre};
            EliminarAmigo.main(datos);
            outputArea.setText("Amigo eliminado: " + nombre);
        } else {
            outputArea.setText("Por favor, ingresa el nombre.");
        }
    }
}

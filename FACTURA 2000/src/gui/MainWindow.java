package gui;

import controller.Controller;
import javax.swing.*;
import javax.swing.JFrame;


public class MainWindow extends JFrame {

    public MainWindow(Controller controller) {
        JFrame frame = new JFrame("Trabajo Practico POO GN05");

        JMenuBar menuBar = new JMenuBar();

        // Crear las barras de menú
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        MenuCuentasCorrientes menuCuentasCorrientes = new MenuCuentasCorrientes();
        MenuLibroIVA menuLibroIVA = new MenuLibroIVA(controller);
        VerDatos verDatos = new VerDatos(controller);

        // Agregar las barras de menú al menú principal
        menuBar.add(menuPrincipal);
        menuBar.add(menuCuentasCorrientes);
        menuBar.add(menuLibroIVA);
        menuBar.add(verDatos);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }
}
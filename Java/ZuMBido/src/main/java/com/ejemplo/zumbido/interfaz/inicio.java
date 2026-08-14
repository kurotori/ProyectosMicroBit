/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejemplo.zumbido.interfaz;

import com.fazecast.jSerialComm.SerialPort;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author sebastian
 */
public class inicio extends JFrame{
    
    private JPanel pnlBarraHerramientas;
    private JPanel pnlPrincipal;

    private JComboBox<String> cmbListaPuertos;
    
    private SerialPort[] puertos;

    public inicio(){
        configurar();
        
    }
    
    private void configurar(){
        setSize(640,480);
        setTitle("Listado De Placas");
        setVisible(true);
        getContentPane().setLayout( new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        listaDePuertos();
        
        cmbListaPuertos = new JComboBox<String>();
        for (SerialPort puerto : puertos) {
            cmbListaPuertos.addItem(puerto.getSystemPortName());
        }
        getContentPane().add(cmbListaPuertos, BorderLayout.NORTH);
        
        getContentPane().validate();
        getContentPane().repaint();
        
    }
    
    
    private void listaDePuertos(){
        this.puertos = SerialPort.getCommPorts();
        
        if (puertos.length<1) {
            System.out.println("No hay puertos disponibles");
        }
        else{
            System.out.println("Puertos Disponibles:");
            for (SerialPort puerto : puertos) {
                System.out.println("----------------------------------------");
                System.out.println("System Port Name: " + puerto.getSystemPortName());  // e.g., COM3, ttyUSB0
                System.out.println("Descriptive Name: " + puerto.getDescriptivePortName()); // e.g., USB Serial Port
                System.out.println("Port Description: " + puerto.getPortDescription());
            }
        }
    }
    
    public static void main(String[] args) {
        new inicio();
    }
    
}

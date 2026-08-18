/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejemplo.zumbido;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author sebastian
 */
public class Fuentes {
    
    public Font CONSOLA;
    public Font BARRA_ESTADO;

    public Fuentes() {
        this.CONSOLA = cargarFuente("/fuentes/Roboto_Mono/RobotoMono-VariableFont_wght.ttf", 18);
    }
    
    
    private Font cargarFuente(String ruta, float tamanio){
        Font fuente;
        try {
            InputStream archivoFuente = getClass().getResourceAsStream(ruta);
            if (archivoFuente == null) {
                throw new IOException("No se encontró el recurso '" + ruta + "'");
            }
            
            fuente = Font.createFont(Font.TRUETYPE_FONT, archivoFuente);
            fuente = fuente.deriveFont(tamanio);
            
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            fuente =  new Font("Arial", Font.PLAIN, (int)tamanio);
        }
        
        return fuente;
    }
    
}

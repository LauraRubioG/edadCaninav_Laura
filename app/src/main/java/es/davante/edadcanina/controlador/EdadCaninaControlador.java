package es.davante.edadcanina.controlador;

import es.davante.edadcanina.modelo.EdadCaninaModelo;

// PASO 6
// Recibe las acciones de la vista y se las pasa al modelo (sin tocar ninguna vista directamente)
public class EdadCaninaControlador {

    private final EdadCaninaModelo modelo;

    public EdadCaninaControlador(EdadCaninaModelo modelo) {
        this.modelo = modelo;
    }

    public void alPulsarCalcular() {
        modelo.calcular();
    }

    public void alPulsarLimpiar() {
        modelo.limpiar();
    }
}
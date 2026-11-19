package es.davante.edadcanina.modelo;

/**
 * Representa todos los resultados posibles al validar y calcular la edad canina.
 * Usamos un enum en lugar de textos para que el modelo no dependa de Android ni del idioma.
 */
public enum Validacion {
    SIN_CALCULAR,
    OK,
    VACIO,
    NO_ES_NUMERO,
    FUERA_DE_RANGO
}

package es.davante.edadcanina.modelo;

/**
 * Modelo de la aplicación: guarda el estado, valida la entrada y calcula la edad humana.
 * No contiene referencias a vistas ni textos para el usuario para poder probarlo con JUnit sin emulador.
 */
public class EdadCaninaModelo {

    // Constantes de negocio centralizadas en el modelo para que cualquier cambio afecte a un único lugar
    public static final int FACTOR_CANINO = 7;
    public static final int EDAD_MINIMA = 1;
    public static final int EDAD_MAXIMA = 30;

    private String edadTexto = "";
    private int edadHumana = 0;
    private Validacion validacion = Validacion.SIN_CALCULAR;

    public String getEdadTexto() {
        return edadTexto;
    }

    public int getEdadHumana() {
        return edadHumana;
    }

    public Validacion getValidacion() {
        return validacion;
    }

    /** METODO 1
     * Este metodo actualiza el texto introducido por el usuario.
     * Regla de negocio: si el texto cambia, el resultado anterior deja de ser válido y caduca.
     */
    public void setEdadTexto(String nuevoTexto) {
        if (nuevoTexto == null) {
            nuevoTexto = "";
        }
        // Evitamos trabajo innecesario si el valor no ha cambiado realmente
        if (nuevoTexto.equals(this.edadTexto)) {
            return;
        }
        this.edadTexto = nuevoTexto;
        this.edadHumana = 0;
        this.validacion = Validacion.SIN_CALCULAR;
    }

    /**METODO 2
     * Este metodo valida la entrada actual y calcula la edad humana si es correcta.
     * @return El estado de validación resultante.
     */
    public Validacion calcular() {
        String textoLimpio = edadTexto.trim();

        if (textoLimpio.isEmpty()) {
            edadHumana = 0;
            validacion = Validacion.VACIO;
            return validacion;
        }

        int edadPerro;
        try {
            edadPerro = Integer.parseInt(textoLimpio);
        } catch (NumberFormatException e) {
            edadHumana = 0;
            validacion = Validacion.NO_ES_NUMERO;
            return validacion;
        }

        if (edadPerro < EDAD_MINIMA || edadPerro > EDAD_MAXIMA) {
            edadHumana = 0;
            validacion = Validacion.FUERA_DE_RANGO;
            return validacion;
        }

        edadHumana = edadPerro * FACTOR_CANINO;
        validacion = Validacion.OK;
        return validacion;
    }
}
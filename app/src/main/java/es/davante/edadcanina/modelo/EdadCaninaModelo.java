package es.davante.edadcanina.modelo;

/**
 * Modelo de la aplicación: guarda el estado, valida la entrada y calcula la edad humana.
 * No contiene referencias a vistas ni textos para el usuario para poder probarlo con JUnit sin emulador.
 */
/*importamos la clase BR y la usamos para que cada vez uqe cambie una propiedad, el modelo nos avise usando el identificador
  de esa propiedad en la clase generada de BR
* */
import es.davante.edadcanina.BR;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

//Usamos la extendion BaseObservable para heredar la clase y asi poder avisar a la vista ciando cambie algún dato
public class EdadCaninaModelo extends BaseObservable {

    // Constantes centralizadas aquía para si en un fututo hay que cambiar alfo se cambien aqui directamente
    public static final int FACTOR_CANINO = 7;
    public static final int EDAD_MINIMA = 1;
    public static final int EDAD_MAXIMA = 30;

    // Variables del estado interno del modelo, lo que escribe el usuario, el resultado y si es valido
    private String edadTexto = "";
    private int edadHumana = 0;
    private Validacion validacion = Validacion.SIN_CALCULAR;

    // @Binable nos genera la ocnstate en BR para que DataBinding pueda escuchar esta propiedad.
    @Bindable
    public String getEdadTexto() {
        return edadTexto;
    }
    @Bindable
    public int getEdadHumana() {
        return edadHumana;
    }
    @Bindable
    public Validacion getValidacion() {
        return validacion;
    }

    /** METODO 1 paso 3
     * Este metodo actualiza el texto introducido por el usuario.
     * Regla de negocio: si el texto cambia, el resultado anterior deja de ser válido y caduca.
     */
    /*PASO 4
     Actualiza el texto evitando nulos y cortando posibles bucles infinitos del enlace bidireccional (@={...}).
    */
    public void setEdadTexto(String nuevoTexto) {
        if (nuevoTexto == null) {
            nuevoTexto = "";
        }
        // Evitamos trabajo innecesario si el valor no ha cambiado realmente
        // Si el texto es igual al que ya había, no avisamos para evitar repintados innecesarios.
        if (nuevoTexto.equals(this.edadTexto)) {
            return;
        }
        this.edadTexto = nuevoTexto;
        notifyPropertyChanged(BR.edadTexto);

        setEdadHumana(0);
        setValidacion(Validacion.SIN_CALCULAR);
    }

    // Setter privado para que nadie de fuera pueda alterar el cálculo; solo avisa si el valor cambia.
    private void setEdadHumana(int nuevaEdadHumana) {
        if (nuevaEdadHumana == this.edadHumana) {
            return;
        }
        this.edadHumana = nuevaEdadHumana;
        notifyPropertyChanged(BR.edadHumana);
    }

    // Setter privado para que solo el modelo controle el estado de validación y avise a la vista.
    private void setValidacion(Validacion nuevaValidacion) {
        if (nuevaValidacion == this.validacion) {
            return;
        }
        this.validacion = nuevaValidacion;
        notifyPropertyChanged(BR.validacion);
    }


    /**
     * METODO 2
     * Este metodo valida la entrada actual y calcula la edad humana si es correcta.
     * @return El estado de validación resultante.
     */
    public Validacion calcular() {
        String textoLimpio = edadTexto.trim();

        // 1. Comprobamos si está vacío.
        if (textoLimpio.isEmpty()) {
            /* LO CAMBIAMOS Y SUSTITUIMOS POR LOS SETTER
            edadHumana = 0;
            validacion = Validacion.VACIO;
             */
            setEdadHumana(0);
            setValidacion(Validacion.VACIO);
            return validacion;
        }

        // 2. Comprobamos que sea un número entero válido sin que la app se cierre
        int edadPerro;
        try {
            edadPerro = Integer.parseInt(textoLimpio);
        } catch (NumberFormatException e) {
            /* LO CAMBIAMOS Y SUSTITUIMOS POR LOS SETTER
            edadHumana = 0;
            validacion = Validacion.NO_ES_NUMERO;
             */
            setEdadHumana(0);
            setValidacion(Validacion.NO_ES_NUMERO);
            return validacion;
        }
        // 3. Comprobamos que esté dentro del rango lógico de vida de un perro
        if (edadPerro < EDAD_MINIMA || edadPerro > EDAD_MAXIMA) {
            /* LO CAMBIAMOS Y SUSTITUIMOS POR LOS SETTER
            edadHumana = 0;
            validacion = Validacion.FUERA_DE_RANGO;
            */
            setEdadHumana(0);
            setValidacion(Validacion.FUERA_DE_RANGO);
            return validacion;
        }

        // 4. Si pasa todas las validaciones, calculamos y marcamos como listo
        /* LO CAMBIAMOS Y SUSTITUIMOS POR LOS SETTER
        edadHumana = edadPerro * FACTOR_CANINO;
        validacion = Validacion.OK;
        */
        setEdadHumana(edadPerro * FACTOR_CANINO);
        setValidacion(Validacion.OK);
        return validacion;
    }
}
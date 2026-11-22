package es.davante.edadcanina.vista;

import android.content.Context;
import android.widget.EditText;

import androidx.databinding.BindingAdapter;

import es.davante.edadcanina.R;
import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;

// Clase de utilidades de la vista para traducir estados del modelo a mensajes de Android
public final class AdaptadoresVista {

    // Constructor privado porque esta clase solo tiene métodos estáticos
    private AdaptadoresVista() {
    }

    // Creamos el atributo propio app:errorValidacion para pintar el error directamente desde el XML
    @BindingAdapter("errorValidacion")
    public static void mostrarErrorValidacion(EditText campo, Validacion validacion) {
        String mensaje = mensajeDeError(campo.getContext(), validacion);
        // Si mensaje es null, Android quita el icono y el texto de error automáticamente
        campo.setError(mensaje);

        // Si hay error, le damos el foco al campo para que el usuario pueda corregirlo rápido
        if (mensaje != null) {
            campo.requestFocus();
        }
    }

    // Traducimos el enum del modelo al texto en el idioma correspondiente usando el Context de la vista
    private static String mensajeDeError(Context context, Validacion validacion) {
        if (validacion == null) {
            return null;
        }
        switch (validacion) {
            case VACIO:
                return context.getString(R.string.error_vacio);
            case NO_ES_NUMERO:
                return context.getString(R.string.error_no_numero);
            case FUERA_DE_RANGO:
                return context.getString(
                        R.string.error_rango,
                        EdadCaninaModelo.EDAD_MINIMA,
                        EdadCaninaModelo.EDAD_MAXIMA
                );
            case OK:
            case SIN_CALCULAR:
            default:
                return null;
        }
    }
}
package es.davante.edadcanina.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import es.davante.edadcanina.BR;

import androidx.databinding.Observable;

import org.junit.Before;
import org.junit.Test;

// Pruebas unitarias del modelo (se ejecutan sin emulador al no depender de Android).
public class EdadCaninaModeloTest {

    private EdadCaninaModelo modelo;

    // Creamos un modelo limpio antes de cada test para que no se afecten entre sí.
    @Before
    public void setUp() {
        modelo = new EdadCaninaModelo();
    }

    // Test 1: comprueba que una edad válida (6) devuelve OK y calcula 42.
    @Test
    public void edadValida_calculaPorSiete() {
        modelo.setEdadTexto("6");
        assertEquals(Validacion.OK, modelo.calcular());
        assertEquals(42, modelo.getEdadHumana());
    }

    // Test 2: comprueba que si solo hay espacios (vacío) devuelve VACIO.
    @Test
    public void textoVacio_devuelveVacio() {
        modelo.setEdadTexto("   ");
        assertEquals(Validacion.VACIO, modelo.calcular());
        assertEquals(0, modelo.getEdadHumana());
    }

    // Test 3: comprueba que al meter letras devuelve NO_ES_NUMERO sin cerrar la app.
    @Test
    public void textoNoNumerico_devuelveNoEsNumero() {
        modelo.setEdadTexto("abc");
        assertEquals(Validacion.NO_ES_NUMERO, modelo.calcular());
        assertEquals(0, modelo.getEdadHumana());
    }

    // Test 4: comprueba que el límite inferior (0) devuelve FUERA_DE_RANGO.
    @Test
    public void edadCero_devuelveFueraDeRango() {
        modelo.setEdadTexto("0");
        assertEquals(Validacion.FUERA_DE_RANGO, modelo.calcular());
        assertEquals(0, modelo.getEdadHumana());
    }

    // Test 5: comprueba que el límite superior (31) devuelve FUERA_DE_RANGO.
    @Test
    public void edadMayorQueMaxima_devuelveFueraDeRango() {
        modelo.setEdadTexto("31");
        assertEquals(Validacion.FUERA_DE_RANGO, modelo.calcular());
        assertEquals(0, modelo.getEdadHumana());
    }

    // Test 6: comprueba que al cambiar el texto caduca el resultado anterior.
    @Test
    public void cambiarTextoDespuesDeCalcular_caducaResultadoAnterior() {
        modelo.setEdadTexto("6");
        modelo.calcular();
        assertEquals(Validacion.OK, modelo.getValidacion());

        modelo.setEdadTexto("7");
        assertEquals(Validacion.SIN_CALCULAR, modelo.getValidacion());
        assertEquals(0, modelo.getEdadHumana());
    }

    //PASO 4: añadimos el test 7
    // Test 7: comprueba que el modelo avisa a los observadores al calcular.
    @Test
    public void calcular_avisaALosObservadores() {
        final boolean[] avisoEdadHumana = {false};
        final boolean[] avisoValidacion = {false};

        modelo.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (propertyId == BR.edadHumana) avisoEdadHumana[0] = true;
                if (propertyId == BR.validacion) avisoValidacion[0] = true;
            }
        });

        modelo.setEdadTexto("6");
        modelo.calcular();

        assertTrue(avisoEdadHumana[0]);
        assertTrue(avisoValidacion[0]);
    }

}
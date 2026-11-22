package es.davante.edadcanina;

import android.os.Bundle;
//-------------------------------------------------------------------------------------------
/*NO BORRAMOS COMENTAMOS PASO 2
estas clases ya lo has usamos, y dejarlas sería ensuciar el código
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
*/
//---------------------------------------------------------------------------------------------
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import es.davante.edadcanina.databinding.ActivityMainBinding;
//IMPORTAMOS LAS CLASES QUE ACABAMOS DE CREAR PARA USARLAS AQUI EN EL MAINACTIVITY
import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;

public class MainActivity extends AppCompatActivity {
    //--------------------------------------------------------------------------------------------------------
    /*NO BORRAMOS COMENTAMOS PASO 3
    estas variables ya no las necesitamos en el MainActivity porque la tenemos en
    la clase de EdadCaninaModelo, que hemos creado para guarda el estado, valida la entrada y calcula la edad humana.
    private static final int FACTOR_CANINO = 7;
    private static final int EDAD_MINIMA = 1;
    private static final int EDAD_MAXIMA = 30; // ningún perro supera esta edad
    */
    /* EN VEZ DE BORRAR COMENTAMOS
    estas variables ya no nos hace falta porque borramos los findViewById
    private EditText ageEdit;
    private TextView resultText;
    */
    //-----------------------------------------------------------------------------------------------------------
    private ActivityMainBinding binding;
    private EdadCaninaModelo modelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //-------------------------------------------------------------------------------------------------------
        /*EN VEZ DE BORRAR COMENTAMOS PASO 3
        en vez de llamarlo por findViewById los llamamos por binding
        // Declaramos variables
        ageEdit = findViewById(R.id.age_input);
        resultText = findViewById(R.id.result_text);
        Button button1 = findViewById(R.id.btn_calc);
        */
        //----------------------------------------------------------------------------------------

        //Creamos la instancia del modelo que gestionará los datos y reglas
        modelo = new EdadCaninaModelo();
        // Conectamos el modelo con el layout para que los enlaces @{...} y @={...} funcionen.
        binding.setModelo(modelo);
        // Implementar el boton (setOnClickListener)
        binding.btnCalc.setOnClickListener(view -> modelo.calcular());
    }


    /*PASO 5: LIMPIAMOS EL MAINACTIVITY BORRANDO LOS METODOS CALCULAReDAD() Y MOSTRARERROR()
    private void calcularEdad() {
        //-------------------------------------------------------------------------------------------
        /* NO BORRAMOS COMENTAMOS PASO 3
        // 1. Leer el texto del EditText quitando espacios
        String ageString = binding.ageInput.getText().toString().trim();
        // 2. ¿Está vacío?
        if (ageString.isEmpty()) {
            mostrarError(getString(R.string.error_vacio));
            return;
        }
        int age;
        // 3. Convertir a int dentro de un try/catch
        try {
            age = Integer.parseInt(ageString);
        } catch (NumberFormatException e) {
            mostrarError(getString(R.string.error_no_numero));
            return;
        }
        // 4. ¿Está en rango?
        if (age < EDAD_MINIMA || age > EDAD_MAXIMA) {
            mostrarError(getString(R.string.error_rango, EDAD_MINIMA, EDAD_MAXIMA));
            return;
        }
        // 5. Todo correcto
        binding.ageInput.setError(null);
        int dogAge = age * FACTOR_CANINO;
        binding.resultText.setText(getString(R.string.tu_edad_format, dogAge));
         */
        //-----------------------------------------------------------------------------------------------

        /*PASO 4 NO BORRAMOS COMENTAMOS
        quitamos el modelo.setEdadTexto(binding.ageInput.getText().toString());
        // Pasamos la entrada al modelo y delegamos en él la validación y el cálculo
        modelo.setEdadTexto(binding.ageInput.getText().toString());
        */
    /*
        Validacion resultado = modelo.calcular();

        // La Activity solo traduce el resultado del modelo a elementos visuales
        switch (resultado) {
            case OK:
                binding.ageInput.setError(null);
                //binding.resultText.setText(getString(R.string.tu_edad_format, modelo.getEdadHumana()));
                break;
            case VACIO:
                mostrarError(getString(R.string.error_vacio));
                //mostrarError(getString(R.string.error_vacio));
                break;
            case NO_ES_NUMERO:
                mostrarError(getString(R.string.error_no_numero));
                //mostrarError(getString(R.string.error_no_numero));
                break;
            case FUERA_DE_RANGO:
                mostrarError(getString(R.string.error_rango,
                        EdadCaninaModelo.EDAD_MINIMA,
                        EdadCaninaModelo.EDAD_MAXIMA));
                //mostrarError(getString(R.string.error_rango,
                break;
            default:
                break;
        }
    }

    private void mostrarError(String mensaje) {
        // 1. Mostrar el mensaje en el EditText (setError)
        binding.ageInput.setError(mensaje);

        // 2. Poner el foco en el EditText (requestFocus)
        binding.ageInput.requestFocus();
        /*PASO 4: NO BORRAMOS COMENTAMOS
        // 3. Borrar el resultado anterior del TextView
        binding.resultText.setText("");
        */
    //}

}
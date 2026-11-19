package es.davante.edadcanina;

import android.os.Bundle;
/*NO BORRAMOS COMENTAMOS
estas clases ya lo has usamos, y dejarlas sería ensuciar el código
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
*/
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import es.davante.edadcanina.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final int FACTOR_CANINO = 7;
    private static final int EDAD_MINIMA = 1;
    private static final int EDAD_MAXIMA = 30; // ningún perro supera esta edad

    /* EN VEZ DE BORRAR COMENTAMOS
    estas variables ya no nos hace falta porque borramos los findViewById
    private EditText ageEdit;
    private TextView resultText;
    */
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        /*EN VEZ DE BORRAR COMENTAMOS
        en vez de llamarlo por findViewById los llamamos por binding
        // Declaramos variables
        ageEdit = findViewById(R.id.age_input);
        resultText = findViewById(R.id.result_text);
        Button button1 = findViewById(R.id.btn_calc);
        */

        // Implementar el boton (setOnClickListener)
        binding.btnCalc.setOnClickListener(view -> calcularEdad());


    }

    private void calcularEdad() {
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
    }

    private void mostrarError(String mensaje) {
        // 1. Mostrar el mensaje en el EditText (setError)
        binding.ageInput.setError(mensaje);

        // 2. Poner el foco en el EditText (requestFocus)
        binding.ageInput.requestFocus();

        // 3. Borrar el resultado anterior del TextView
        binding.resultText.setText("");
    }

}
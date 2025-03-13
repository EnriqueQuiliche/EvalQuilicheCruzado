package pe.edu.unc.evalquilichecruzado;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultadoEjercicio4 extends AppCompatActivity {

    TextView tvResultadoDetalle, can1, can2, can3, blancos, nulos, grado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_resultado_ejercicio4);

        tvResultadoDetalle = findViewById(R.id.tvResultadoDetalle);
        can1 = findViewById(R.id.tvCandidato1);
        can2 = findViewById(R.id.tvCandidato2);
        can3 = findViewById(R.id.tvCandidato3);
        blancos = findViewById(R.id.tvBlanco);
        nulos = findViewById(R.id.tvNulos);
        grado = findViewById(R.id.tvGrado);

        //recogemos todos los putExtra de la anterior actividad
        int[] votosCandidatos = getIntent().getIntArrayExtra("votosCandidatos");
        int votosBlanco = getIntent().getIntExtra("votosBlanco", 0);
        int votosNulos = getIntent().getIntExtra("votosViciados", 0);
        int[] votosPorGrado = getIntent().getIntArrayExtra("votosPorGrado");
        String resultado = getIntent().getStringExtra("resultado");

        tvResultadoDetalle.setText(resultado);
        can1.setText("Candidato 1: "+votosCandidatos[0]);
        can2.setText("Candidato 2: "+votosCandidatos[1]);
        can3.setText("Candidato 3: "+votosCandidatos[2]);
        blancos.setText("Votos Blancos: "+votosBlanco);
        nulos.setText("Votos Nulos: "+votosNulos);

        StringBuilder gradoText = new StringBuilder();
        gradoText.append("Votos por Grado:\n\n");
        for (int i = 0; i < votosPorGrado.length; i++) {
            gradoText.append(i + 1).append("º Grado:").append(votosPorGrado[i]).append("\n");
        }

        grado.setText(gradoText.toString());
    }
}
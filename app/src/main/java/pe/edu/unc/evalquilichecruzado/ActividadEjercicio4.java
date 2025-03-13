package pe.edu.unc.evalquilichecruzado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class ActividadEjercicio4 extends AppCompatActivity {

    private TextView tvResultado;
    private Button btnVerDetalles;
    private int[] votosCandidatos = new int[3]; // Candidatos 0, 1, 2
    private int votosBlanco = 0;
    private int votosViciados = 0;
    private int[] votosPorGrado = new int[5]; // Grados 1-5

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_ejercicio4);

        tvResultado = findViewById(R.id.tvResultado);
        btnVerDetalles = findViewById(R.id.btnDetalle);

        simularVotacion();

        btnVerDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActividadEjercicio4.this, ResultadoEjercicio4.class);
                intent.putExtra("votosCandidatos", votosCandidatos);
                intent.putExtra("votosBlanco", votosBlanco);
                intent.putExtra("votosViciados", votosViciados);
                intent.putExtra("votosPorGrado", votosPorGrado);
                intent.putExtra("resultado",tvResultado.getText());
                startActivity(intent);
            }
        });
    }

    private void simularVotacion() {
        Random random = new Random();
        //Generamos aca los 160 votos aleatorios
        for (int i = 1; i <= 160; i++) {
            int grado = random.nextInt(5) + 1; // Grado entre 1 y 5
            int voto = generarVoto(random);

            if (voto >= 0 && voto < 3) {
                votosCandidatos[voto]++;
            } else if (voto == 3) {
                votosBlanco++;
            } else {
                votosViciados++;
            }

            votosPorGrado[grado - 1]++;
        }

        determinarGanador();
    }

    private int generarVoto(Random random) {
        double probabilidad = random.nextDouble();
        if (probabilidad < 0.7) {
            return random.nextInt(3); // Voto por candidato 0, 1 o 2
        } else if (probabilidad < 0.9) {
            return 3; // Voto en blanco
        } else {
            return 4; // Voto viciado
        }
    }

    private void determinarGanador() {
        int maxVotos = -1;
        int candidatoGanador = -1;
        boolean empate = false;

        for (int i = 0; i < votosCandidatos.length; i++) {
            if (votosCandidatos[i] > maxVotos) {
                maxVotos = votosCandidatos[i];
                candidatoGanador = i;
                empate = false;
            } else if (votosCandidatos[i] == maxVotos) {
                empate = true;
            }
        }

        if (empate) {
            tvResultado.setText("Hubo un empate entre los candidatos.");
        } else {
            tvResultado.setText("El candidato ganador es: Candidato " + (candidatoGanador + 1));
        }
    }
}
package com.example.npi;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResultado = findViewById(R.id.textViewResultado); // Obtén la referencia al TextView

        APIService apiService = RetrofitClient.getApiService();

        Call<List<Pregunta>> call = apiService.getPreguntas();
        call.enqueue(new Callback<List<Pregunta>>() {
            @Override
            public void onResponse(Call<List<Pregunta>> call, Response<List<Pregunta>> response) {
                if (response.isSuccessful()) {
                    List<Pregunta> preguntas = response.body();
                    if (preguntas != null) {
                        Log.d("TAG", "va");

                        // Actualiza el contenido del TextView con los datos recibidos
                        StringBuilder stringBuilder = new StringBuilder();
                        for (Pregunta pregunta : preguntas) {
                            stringBuilder.append("ID: ").append(pregunta.getId()).append("\n");
                            stringBuilder.append("Texto: ").append(pregunta.getPregunta()).append("\n\n");

                            // Obtén las respuestas para esta pregunta
                            List<Respuesta> respuestas = pregunta.getRespostes();
                            if (respuestas != null) {
                                for (Respuesta respuesta : respuestas) {
                                    stringBuilder.append("Respuesta: ").append(respuesta.getEtiqueta()).append("\n");
                                }
                            }

                            stringBuilder.append("\n");

                        }
                        final String resultText = stringBuilder.toString();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textViewResultado.setText(resultText);
                            }
                        });
                    } else {
                        // Manejar el caso donde preguntas es nulo
                        Log.d("TAG", "La lista de preguntas es nula");
                    }
                } else {
                    // Manejar errores
                    Log.d("TAG", "No se pudo obtener la lista de preguntas");
                }
            }

            @Override
            public void onFailure(Call<List<Pregunta>> call, Throwable t) {
                // Manejar errores de conexión
                Log.d("TAG", "Error de conexión: " + t.getMessage());
            }
        });
    }
}



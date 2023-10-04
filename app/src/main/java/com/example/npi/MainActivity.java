package com.example.npi;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// ...

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private QuestionAdapter questionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        APIService apiService = RetrofitClient.getApiService();

        Call<List<Pregunta>> call = apiService.getPreguntas();
        call.enqueue(new Callback<List<Pregunta>>() {
            @Override
            public void onResponse(@NonNull Call<List<Pregunta>> call, @NonNull Response<List<Pregunta>> response) {
                if (response.isSuccessful()) {
                    List<Pregunta> preguntas = response.body();
                    if (preguntas != null) {
                        questionAdapter = new QuestionAdapter(preguntas);
                        recyclerView.setAdapter(questionAdapter);
                    } else {
                        Log.d("TAG", "La lista de preguntas es nula");
                    }
                } else {
                    Log.d("TAG", "No se pudo obtener la lista de preguntas");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Pregunta>> call, @NonNull Throwable t) {
                Log.d("TAG", "Error de conexión: " + t.getMessage());
            }
        });
    }
}




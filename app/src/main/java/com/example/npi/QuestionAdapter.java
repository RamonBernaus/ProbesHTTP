package com.example.npi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private final List<Pregunta> preguntaList;
    private Context context;

    public QuestionAdapter(Context context, List<Pregunta> preguntaList) {
        this.context = context; // Asigna correctamente el contexto
        this.preguntaList = preguntaList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pregunta pregunta = preguntaList.get(position);
        holder.textViewQuestionText.setText("Pregunta: " + pregunta.getPregunta());

        // Mostrar respuestas como botones
        List<Respuesta> respuestas = pregunta.getRespostes();
        LinearLayout answersContainer = holder.answersContainer;
        answersContainer.removeAllViews(); // Limpia los botones anteriores si los hubiera

        for (Respuesta respuesta : respuestas) {
            Button buttonRespuesta = new Button(context);
            buttonRespuesta.setText(respuesta.getEtiqueta());
            buttonRespuesta.setTag(respuesta.getId()); // Guarda el ID de la respuesta en el botón
            buttonRespuesta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int respuestaId = (int) view.getTag();
                    // Aquí puedes realizar acciones con el ID de la respuesta seleccionada
                    // Por ejemplo, mostrar un mensaje o llevar a otra actividad
                }
            });
            answersContainer.addView(buttonRespuesta);
        }

        // Cargar la imagen de la pregunta (usando Picasso)
        String urlPregunta = pregunta.getImatge();
        if (urlPregunta != null && !urlPregunta.isEmpty()) {
            Picasso.get().load(urlPregunta).into(holder.imageViewPregunta);
        }
    }


    @Override
    public int getItemCount() {
        return preguntaList.size();
    }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView textViewQuestionId;
            TextView textViewQuestionText;
            LinearLayout answersContainer; // Agrega esta línea
            ImageView imageViewPregunta;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewQuestionText = itemView.findViewById(R.id.textViewQuestionText);
                answersContainer = itemView.findViewById(R.id.answersContainer); // Encuentra el LinearLayout
                imageViewPregunta = itemView.findViewById(R.id.imageViewPregunta);
            }
        }
    }




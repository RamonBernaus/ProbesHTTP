package com.example.npi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private final List<Pregunta> preguntaList;
    private Context context;

    public QuestionAdapter(List<Pregunta> preguntaList) {
        this.context = context;
        this.preguntaList = preguntaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pregunta pregunta = preguntaList.get(position);
        //holder.textViewQuestionId.setText("ID: " + pregunta.getId());
        holder.textViewQuestionText.setText("Pregunta: " + pregunta.getPregunta());

        // Mostrar respuestas
        List<Respuesta> respuestas = pregunta.getRespostes();
        StringBuilder respuestasText = new StringBuilder("Respuestas:\n");
        for (Respuesta respuesta : respuestas) {
            respuestasText.append(" - ").append(respuesta.getEtiqueta()).append("\n");
        }
        holder.textViewRespuestas.setText(respuestasText.toString());

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
        TextView textViewRespuestas;
        ImageView imageViewPregunta;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewQuestionText = itemView.findViewById(R.id.textViewQuestionText);
            textViewRespuestas = itemView.findViewById(R.id.textViewRespuestas);
            imageViewPregunta = itemView.findViewById(R.id.imageViewPregunta);
        }
    }
}



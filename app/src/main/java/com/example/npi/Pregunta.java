package com.example.npi;

import java.util.List;

public class Pregunta {
    private int id;
    private String pregunta;
    private List<Respuesta> respostes;
    private int resposta_correcta;
    private String imatge;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public List<Respuesta> getRespostes() {
        return respostes;
    }

    public void setRespostes(List<Respuesta> respostes) {
        this.respostes = respostes;
    }

    public int getResposta_correcta() {
        return resposta_correcta;
    }

    public void setResposta_correcta(int resposta_correcta) {
        this.resposta_correcta = resposta_correcta;
    }

    public String getImatge() {
        return imatge;
    }

    public void setImatge(String imatge) {
        this.imatge = imatge;
    }
}




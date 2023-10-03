package com.example.npi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("http://192.168.205.68:3000/api/GetPreguntas/")
    Call<List<Pregunta>> getPreguntas();
}


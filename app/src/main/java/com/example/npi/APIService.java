package com.example.npi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("http://10.2.2.83:3000/api/GetPreguntas/")
    Call<List<Pregunta>> getPreguntas();
}


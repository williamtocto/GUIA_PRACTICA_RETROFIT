package com.example.guia_practica_retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceRetro {
    @GET("usersFake")
    Call<List<Modelo>> getPosts();
}

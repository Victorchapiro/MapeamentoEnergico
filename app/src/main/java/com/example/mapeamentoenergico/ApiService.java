package com.example.mapeamentoenergico;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("/api/dica-energia")
    Call<EnergyTipResponse> getEnergyTip(@Header("Authorization") String token);
}


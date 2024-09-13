package com.example.mapeamentoenergico;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiService {

    @GET("/energy-tip")
    Call<EnergyTipResponse> getEnergyTip(@Header("Authorization") String token);
}


package com.example.windows.myapplication.manager;

import com.example.windows.myapplication.dao.DailyAPOD;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("apod")
    Call<DailyAPOD> getDailyAPOD(@Query("api_key") String api_key);

}

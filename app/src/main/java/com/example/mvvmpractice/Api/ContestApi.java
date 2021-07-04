package com.example.mvvmpractice.Api;


import com.example.mvvmpractice.Model.Contest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ContestApi {

    @GET("all")
    Call<List<Contest>> getAllContests();

    @GET("{site}")
    Call<List<Contest>> getContests(@Path("site") String platform);

}

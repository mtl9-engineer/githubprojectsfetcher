package com.example.appprojectsfetcher.network;

import com.example.appprojectsfetcher.model.Repository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetRepositoryDataService {
    @GET("repositories?q=created:>2021-06-23&sort=stars&order=desc")
    Call<Repository> getRepositoyData (@Query("page") int page);
}

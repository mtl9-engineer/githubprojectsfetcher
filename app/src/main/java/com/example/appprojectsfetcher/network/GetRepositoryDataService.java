package com.example.appprojectsfetcher.network;

import com.example.appprojectsfetcher.model.Repository;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryName;


public interface GetRepositoryDataService {

    @GET("repositories?q=sort=stars&order=desc")
    Call<Repository> getRepositoyData (@Query("page") int page, @QueryName String date );
}

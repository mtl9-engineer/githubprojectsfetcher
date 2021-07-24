package com.example.appprojectsfetcher.network;

import com.example.appprojectsfetcher.model.RepositoriesList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetRepositoryDataService {
    @GET("&page={page_num}")
    Call<RepositoriesList> getRepositoyData (@Query("id") long id , @Path("page_num") int pageNum);
}

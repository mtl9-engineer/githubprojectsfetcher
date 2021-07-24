package com.example.appprojectsfetcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.appprojectsfetcher.adapter.RepositoryAdapter;
import com.example.appprojectsfetcher.model.Repository;
import com.example.appprojectsfetcher.network.GetRepositoryDataService;
import com.example.appprojectsfetcher.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private RepositoryAdapter adapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetRepositoryDataService service = RetrofitInstance.getRetrofitInstance().create(GetRepositoryDataService.class);

        Call<Repository> call = service.getRepositoyData(0);

        Log.i("URL Called", call.request().url() + "");

        call.enqueue(new Callback<Repository>() {
            @Override
            public void onResponse(Call<Repository>call, Response<Repository> response) {
                Log.i("Response", response.body() + "");

                generateRepositoryList(response.body());
            }

            @Override
            public void onFailure(Call<Repository> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void generateRepositoryList(Repository repository) {
        recyclerView = findViewById(R.id.recycler_view_repository_list);

        adapter = new RepositoryAdapter(repository.getItems(), MainActivity.this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}
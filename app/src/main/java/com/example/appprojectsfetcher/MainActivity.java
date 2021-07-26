package com.example.appprojectsfetcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.appprojectsfetcher.adapter.RepositoryAdapter;
import com.example.appprojectsfetcher.model.Item;
import com.example.appprojectsfetcher.model.Repository;
import com.example.appprojectsfetcher.network.GetRepositoryDataService;
import com.example.appprojectsfetcher.network.RetrofitInstance;
import com.example.appprojectsfetcher.pagination.PaginationScrollListener;
import com.example.appprojectsfetcher.utils.DateConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private RepositoryAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar progressBar;

    private static List<Item> items= new ArrayList<>();
    private boolean isLoading = false;

    private static final int  PAGE_NUMBER = 0;
    private int TOTAL_PAGES = 33;
    private boolean isLastPage = false;
    private int currentPage= PAGE_NUMBER;
    private String formattedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DateConverter converter = new DateConverter();
        Date date = converter.subtractDays();
        formattedDate = converter.formatDate(date);
        Repository repository = new Repository();
        adapter = new RepositoryAdapter(repository.getItems(), MainActivity.this);

        new Handler().postDelayed(() -> loadPages(currentPage), 5000);


    }
    private void generateRepositoryList(Repository repository) {
        recyclerView = findViewById(R.id.recycler_view_repository_list);
        int lastItemPosition = items.size()-1;
        items.addAll(repository.getItems());
        adapter = new RepositoryAdapter(items, MainActivity.this);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);
        if(currentPage != 0){
            recyclerView.scrollToPosition(lastItemPosition);
        }
        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage +=1;
                new Handler().postDelayed(() -> loadPages(currentPage), 5000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

        });


    }

    private void loadPages(int page) {
        Log.i("I enter this function ",String.valueOf(page));

        progressBar = findViewById(R.id.progress_bar);
        GetRepositoryDataService service = RetrofitInstance.getRetrofitInstance().create(GetRepositoryDataService.class);

        Call<Repository> call = service.getRepositoyData(currentPage,"created:>"+formattedDate);
        if(page == 0){
            progressBar.setVisibility(View.GONE);
        }
        else{
            adapter.removeLoadingFooter();
            isLoading = false;
        }


        Log.i("URL Called", call.request().url() + "");

        call.enqueue(new Callback<Repository>() {
            @Override
            public void onResponse(Call<Repository>call, Response<Repository> response) {
                generateRepositoryList(response.body());
            }

            @Override
            public void onFailure(Call<Repository> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
        if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
        else isLastPage = true;

    }


}
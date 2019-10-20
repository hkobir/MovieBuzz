package com.hk.demorestapimovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hk.demorestapimovie.adapter.AdapterMovie;
import com.hk.demorestapimovie.databinding.ActivityMainBinding;
import com.hk.demorestapimovie.movie.ApiClient;
import com.hk.demorestapimovie.movie.RetrofitInterface;
import com.hk.demorestapimovie.pojo.Movie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
private List<Movie> movieList;
private AdapterMovie adapterMovie;
private RetrofitInterface retrofitInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        getSupportActionBar().setTitle("Marvel's Movie List");
        binding.progressBar.setVisibility(View.VISIBLE);
        init();
        getData();

    }

    private void getData() {

        retrofitInterface = ApiClient.getInstance().getApi();
        Call<List<Movie>> call = retrofitInterface.getData();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                movieList = response.body();
                binding.progressBar.setVisibility(View.GONE);  //when data response
                adapterMovie = new AdapterMovie(movieList,MainActivity.this);
                binding.movieRV.setAdapter(adapterMovie);

            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void init() {
        movieList = new ArrayList<>();
        binding.movieRV.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }
}

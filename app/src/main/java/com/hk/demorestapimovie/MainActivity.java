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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
private List<Movie> movieList;
private AdapterMovie adapterMovie;
private RetrofitInterface retrofitInterface,movieName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        getSupportActionBar().setTitle("Marvel's Movie List");
        binding.progressBar.setVisibility(View.VISIBLE);
        init();
        getData();
        //getMovie();


        binding.insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBody> call = retrofitInterface.insertData("Venom","Hk","Hkobir","2001","hk","Hk","https://hjhhfdjk.com","wonderfull");
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(MainActivity.this, "Successfully inserted data", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });

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


    //response when filter data with query
    private void getMovie() {
        movieName = ApiClient.getInstance().getApi();
        Call<List<Movie>> movieCall = movieName.getMovieName("Thor");
        movieCall.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Code: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                movieList = response.body();
                binding.progressBar.setVisibility(View.GONE);  //when data response
                adapterMovie = new AdapterMovie(movieList,MainActivity.this);
                binding.movieRV.setAdapter(adapterMovie);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    private void init() {
        movieList = new ArrayList<>();
        binding.movieRV.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }
}

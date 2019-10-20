package com.hk.demorestapimovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hk.demorestapimovie.R;
import com.hk.demorestapimovie.pojo.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.ViewHolder> {
    private List<Movie> movieList;
    private Context context;

    public AdapterMovie(List<Movie> movieList,Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Movie cMovie = movieList.get(position);
        //load Image by picasso
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.build().load(cMovie.getImageurl()).placeholder(R.drawable.place_image).into(holder.movieImage);

        holder.name.setText(cMovie.getName());
        holder.team.setText(cMovie.getTeam());
        holder.fRealese.setText(cMovie.getFirstappearance());
        holder.producer.setText(cMovie.getCreatedby());
        holder.publisher.setText(cMovie.getPublisher());


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView movieImage;
        private TextView name, team, fRealese, producer, publisher;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movieIV);
            name = itemView.findViewById(R.id.movieNameTV);
            team = itemView.findViewById(R.id.teamNameTv);
            fRealese = itemView.findViewById(R.id.firstRealeseTV);
            producer = itemView.findViewById(R.id.createdByTV);
            publisher = itemView.findViewById(R.id.publisherTV);
        }
    }
}

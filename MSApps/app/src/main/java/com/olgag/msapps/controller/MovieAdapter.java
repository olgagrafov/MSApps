package com.olgag.msapps.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.olgag.msapps.R;
import com.olgag.msapps.db.DbProvider;
import com.olgag.msapps.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private Context context;
    private ArrayList<Movie> arrListMovie = new ArrayList<>();
    private int position;
    private OnMovieClickListener listener;

    public MovieAdapter(Context context) {
        this.context = context;
        this.listener = (OnMovieClickListener) context;
    }

    public void add(Movie movie){
        arrListMovie.add(movie);
        notifyItemInserted(arrListMovie.size()-1);
    }
    public void addAll(ArrayList<Movie> movie){
        this.arrListMovie.addAll(movie);
    }
    public void del(Movie movie){
        arrListMovie.remove(movie);
        notifyItemRemoved(position);
    }
    public void delAll(){
        int size = arrListMovie.size();
        arrListMovie.clear();
        notifyItemRangeRemoved(0, size);
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieHolder(LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieHolder holder, int position) {
        holder.bind(arrListMovie.get(position));
    }

    @Override
    public int getItemCount() {
        return arrListMovie.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txtTitle,txtYear;
        private ImageButton btnRemove;
        private Movie curentMovie;
        private LinearLayout layoutMovie;
        private RatingBar ratingMovie;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);

            this.txtTitle = itemView.findViewById(R.id.txtMovieTitle);
            txtTitle.setOnClickListener(this);
            this.txtYear = itemView.findViewById(R.id.txtYear);
            txtYear.setOnClickListener(this);
            this.layoutMovie= itemView.findViewById(R.id.rowMovie);
            layoutMovie.setOnClickListener(this);
            this.btnRemove = itemView.findViewById(R.id.btnRemove);
            this.ratingMovie = itemView.findViewById(R.id.ratingMovieList);
            btnRemove.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnRemove:
                    position = getAdapterPosition();
                    itemView.getContext().getContentResolver().
                            delete(DbProvider.MOVIE_TBL_URL, "movie_id == ?", new String[] { String.valueOf(curentMovie.getIdMovie())});
                    del(curentMovie);
                    break;
                default:
                   // Toast.makeText(context, curentMovie.getTitle(), Toast.LENGTH_SHORT).show();
                    listener.setMovie(curentMovie);

            }

        }

        public void bind(Movie movie) {
            curentMovie = movie;
            txtTitle.setText(curentMovie.getTitle());
            txtYear.setText(curentMovie.getReleaseYear() + " ");
            ratingMovie.setRating(curentMovie.getRating()/2f);
        }
    }

    public interface OnMovieClickListener{
        void setMovie(Movie curentMovie);
    }
}

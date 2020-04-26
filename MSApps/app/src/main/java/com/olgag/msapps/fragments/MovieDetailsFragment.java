package com.olgag.msapps.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import com.olgag.msapps.R;
import com.olgag.msapps.model.Movie;

public class MovieDetailsFragment extends Fragment {

    private TextView txtTitle,txtReleaseYear, txtGenre;
    private ImageView imgMovie;
    private RatingBar ratingMovie;
    private View vw;
    public MovieDetailsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vw = inflater.inflate(R.layout.fragment_movie_details, container, false);
        txtTitle= vw.findViewById(R.id.txtTitle);
        txtReleaseYear = vw.findViewById(R.id.txtReleaseYear);
        txtGenre = vw.findViewById(R.id.txtGenre);
        imgMovie = vw.findViewById(R.id.imgMovie);
        ratingMovie = vw.findViewById(R.id.ratingMovie);
        return vw;
    }

    public void showMovie(Movie movie){
        txtTitle.setText(movie.getTitle());
        ratingMovie.setRating(movie.getRating()/2f);
        txtReleaseYear.setText(movie.getReleaseYear() + "");
        txtGenre.setText(movie.getGenre());
        Picasso.with(vw.getContext()).load(movie.getImageAddress()).into(imgMovie);

    }

}

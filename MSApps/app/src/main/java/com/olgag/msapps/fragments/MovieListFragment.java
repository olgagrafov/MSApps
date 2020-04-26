package com.olgag.msapps.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olgag.msapps.R;
import com.olgag.msapps.controller.MovieAdapter;
import com.olgag.msapps.db.DbHelper;
import com.olgag.msapps.db.DbProvider;
import com.olgag.msapps.model.Movie;

import java.util.ArrayList;


public class MovieListFragment extends Fragment {
    private MovieAdapter movieAdapter;
    private OnChosenMovieClickListener listener;

    public MovieListFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (OnChosenMovieClickListener) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vw =  inflater.inflate(R.layout.fragment_movie_list, container, false);
        RecyclerView rccMovieList=vw.findViewById(R.id.rccMovieList);
        rccMovieList.setLayoutManager(new GridLayoutManager(vw.getContext(),1));

        movieAdapter = new MovieAdapter(vw.getContext());
        rccMovieList.setAdapter(movieAdapter);
        Cursor c = getContext().getContentResolver().query(DbProvider.MOVIE_TBL_URL, null, null, null, "year DESC");
        ArrayList<Movie> listMovies=new ArrayList<>();
        while (c.moveToNext()) {
            listMovies.add(new Movie(
                        c.getLong(c.getColumnIndex(DbHelper.TBL_MOVIE_ID)),
                        c.getString(c.getColumnIndex(DbHelper.MOVIE_TITLE)),
                        c.getString(c.getColumnIndex(DbHelper.MOVIE_URL_IMAGE)),
                        c.getFloat(c.getColumnIndex(DbHelper.MOVIE_RATING)),
                        c.getInt(c.getColumnIndex(DbHelper.MOVIE_RELEASE_YEAR)),
                        c.getString(c.getColumnIndex(DbHelper.MOVIE_GENRE))));
            }
        movieAdapter.addAll(listMovies);
        return vw;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public interface OnChosenMovieClickListener{
        void  setMovie(Movie curentMovie);
    }
}

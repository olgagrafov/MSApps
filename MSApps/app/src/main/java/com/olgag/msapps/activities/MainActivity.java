package com.olgag.msapps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.olgag.msapps.R;
import com.olgag.msapps.controller.MovieAdapter;
import com.olgag.msapps.fragments.MovieDetailsFragment;
import com.olgag.msapps.fragments.MovieListFragment;
import com.olgag.msapps.model.Movie;


public class MainActivity extends AppCompatActivity implements MovieListFragment.OnChosenMovieClickListener, MovieAdapter.OnMovieClickListener, View.OnClickListener {
    private MovieListFragment movieListFrg;
    private MovieDetailsFragment movieDetailsFrg;
    private Button btnAdd;


    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        qrScan = new IntentIntegrator(this);
        movieListFrg = (MovieListFragment) getSupportFragmentManager().findFragmentByTag("movieList");
        movieDetailsFrg = (MovieDetailsFragment) getSupportFragmentManager().findFragmentByTag("movieDetails");

        if(savedInstanceState == null){
            movieListFrg = new MovieListFragment();
            movieDetailsFrg = new MovieDetailsFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, movieListFrg, "movieList")
                    .add(R.id.fragmentContainer, movieDetailsFrg, "movieDetails")
                    .hide(movieDetailsFrg)
                    .show(movieListFrg)
                    .commit();
        }
    }

    @Override
    public void setMovie(Movie curentMovie) {
         //Toast.makeText(this, curentMovie.getTitle(), Toast.LENGTH_SHORT).show();

        movieDetailsFrg.showMovie(curentMovie);

        getSupportFragmentManager().beginTransaction()
                .hide(movieListFrg)
                .show(movieDetailsFrg)
                .addToBackStack(null)
                .commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                String strScan= result.getContents();
                if(strScan.compareTo("https://api.androidhive.info/json/movies.json")==0){
                    Intent intent = new Intent(this,SplashActivity.class);
                    intent.putExtra("isQR", true);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                    finish();
                }
                else
                 Toast.makeText(this, "It is wrong address", Toast.LENGTH_LONG).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public void onClick(View view) {
        qrScan.initiateScan();
    }
}

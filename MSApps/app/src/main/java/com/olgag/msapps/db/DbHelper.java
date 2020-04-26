package com.olgag.msapps.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String TBL_MOVIE = "tbl_movie";
    public static final String TBL_MOVIE_ID = "movie_id";
    public static final String MOVIE_TITLE = "title";
    public static final String MOVIE_URL_IMAGE = "image";
    public static final String MOVIE_RELEASE_YEAR = "year";
    public static final String MOVIE_RATING = "rating";
    public static final String MOVIE_GENRE = "genre";

    public DbHelper(@Nullable Context context)    {
        super(context,  "movie.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE %s ( %s  INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT , %s TEXT , %s  INTEGER, %s REAL , %s TEXT )",
                TBL_MOVIE, TBL_MOVIE_ID, MOVIE_TITLE, MOVIE_URL_IMAGE, MOVIE_RELEASE_YEAR, MOVIE_RATING,MOVIE_GENRE));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}


package com.olgag.msapps.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements  Parcelable {
    private long idMovie;
    private String title, imageAddress,genre;
    private float rating;
    private int releaseYear;

    public Movie(long idMovie, String title, String imageAddress, float rating, int releaseYear, String genre) {
        this.idMovie = idMovie;
        this.title = title;
        this.imageAddress = imageAddress;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }
    public Movie( String title, String imageAddress, float rating, int releaseYear, String genre) {
        this.title = title;
        this.imageAddress = imageAddress;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }

    protected Movie(Parcel in) {
        idMovie = in.readLong();
        rating = in.readFloat();
        releaseYear = in.readInt();
        title = in.readString();
        imageAddress = in.readString();
        genre = in.readString();
    }
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }
        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(long idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idMovie);
        dest.writeFloat(rating);
        dest.writeInt(releaseYear);
        dest.writeString(title);
        dest.writeString(imageAddress);
        dest.writeString(genre);
    }
}

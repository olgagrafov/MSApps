package com.olgag.msapps.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbProvider extends ContentProvider {
    private DbHelper helper;
    private static final String AUTHORIZATION = "ccom.olgag.msapps.controller";
    public static final Uri MOVIE_TBL_URL = Uri.parse("content://" + AUTHORIZATION + "/" + DbHelper.TBL_MOVIE);

    public DbProvider() {
    }
    @Override
    public boolean onCreate() {
        helper=new DbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] columns, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query(uri.getLastPathSegment(), columns, selection, selectionArgs, null, null, sortOrder);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = helper.getWritableDatabase();
        long rowNum = db.insert(uri.getLastPathSegment(), null, values);
        db.close();
        return Uri.withAppendedPath(uri, rowNum+"");
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int count = db.delete(uri.getLastPathSegment(), selection, selectionArgs);
        db.close();
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int count = db.update(uri.getLastPathSegment(), values, selection, selectionArgs);
        db.close();
        return count;
    }
}

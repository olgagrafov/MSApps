package com.olgag.msapps.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.olgag.msapps.R;
import com.olgag.msapps.db.DbProvider;
import com.olgag.msapps.services.ReceiverMovie;
import com.olgag.msapps.services.ServicesMovie;

public class SplashActivity extends AppCompatActivity implements ReceiverMovie.OnReceivedListener {
    private ReceiverMovie movieReceiver;
    private TextView txtWait;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        txtWait = findViewById(R.id.txtWait);
        startAnimator();

        Boolean isQR = getIntent().getBooleanExtra("isQR", false);

        c =   getContentResolver().query(DbProvider.MOVIE_TBL_URL, null, null, null, null);
        if(c.getCount()>0 && !isQR)
            showMovieList("");
        else {
              Intent intC = new Intent(this, ServicesMovie.class);
              this.startService(intC);
              movieReceiver = new ReceiverMovie(this);
              LocalBroadcastManager.getInstance(this).registerReceiver(movieReceiver, new IntentFilter(ServicesMovie.MOVIE_SERVICE));
        }
        c.close();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(c.getCount() == 0)
            LocalBroadcastManager.getInstance(this).unregisterReceiver(movieReceiver);
    }

    @Override
    public void showMovieList(String recStr) {
      if(recStr.length()>0) {
            recStr = "Current movies already exist in the Database: " + recStr;
            View contextView = findViewById(android.R.id.content);
            Snackbar.make(contextView, recStr, Snackbar.LENGTH_LONG).show();
     }

      Intent intent = new Intent(this,MainActivity.class);
      startActivity(intent);
      finish();
    }

    private void startAnimator(){
        ObjectAnimator animatorUp = ObjectAnimator.ofFloat(txtWait, "translationY", -500);
        animatorUp.setDuration(2500);

        ObjectAnimator animatorRotate = ObjectAnimator.ofFloat(txtWait, "rotation", 0, 1080);
        animatorRotate.setDuration(2500);
        ObjectAnimator animatorRotate2 = ObjectAnimator.ofFloat(txtWait, "rotation", 1080, 0);
        animatorRotate2.setDuration(3000);

        ObjectAnimator animatorDown = ObjectAnimator.ofFloat(txtWait, "translationY", 0);
        animatorDown.setDuration(3000);

        AnimatorSet set = new AnimatorSet();
        set.play(animatorUp).with(animatorRotate).before(animatorDown).before(animatorRotate2);
        set.start();
    }
}

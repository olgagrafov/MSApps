package com.olgag.msapps.services;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ReceiverMovie extends BroadcastReceiver {
    private Context cont;
    private OnReceivedListener listener;

    public ReceiverMovie(Context cont) {
       this.cont = cont;
       this.listener = (OnReceivedListener) cont;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
         String recStr =  intent.getStringExtra("existingMovie");
         listener.showMovieList(recStr);
    }

    public interface OnReceivedListener{
        void showMovieList(String recStr);
    }
}

package com.webdesarrollador.servicestutorial1;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by raulrevillas on 16/01/2017.
 */

public class Servicio1 extends Service {

    static long TIME_LIMIT = 60000;
    CountDownTimer Count;
    @Override
    public void onCreate() {
        Log.d("estado","onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        super.onStartCommand(intent, flags, startId);

        Count = new CountDownTimer(TIME_LIMIT, 1000) {

            public void onTick(long millisUntilFinished) {

                String time = String.valueOf((TIME_LIMIT - millisUntilFinished + 1000) / 1000);
                Log.d("tiempo",time);

                Intent i = new Intent("COUNTUP_UPDATED");
                i.putExtra("countup",time);

                sendBroadcast(i);
            }

            public void onFinish() {

                //Log.d("estado","onFinish");
                Intent i = new Intent("COUNTUP_UPDATED");
                i.putExtra("countdown","FIN");

                sendBroadcast(i);

                //Detenemos el servicio
                stopSelf();
            }
        };

        Count.start();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Cancelamos el CountDownTimer
        Count.cancel();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

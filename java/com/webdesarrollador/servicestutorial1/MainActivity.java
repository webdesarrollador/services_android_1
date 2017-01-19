package com.webdesarrollador.servicestutorial1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver segundos_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void iniciarServicio (View view){

        startService(new Intent(this, Servicio1.class));
        final TextView segundos = (TextView)findViewById(R.id.segundos);

        segundos_update = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {

                //Actualizo el TextView con los segundos que vamos
                segundos.setText(intent.getExtras().getString("countup"));

            }
        };

        registerReceiver(segundos_update, new IntentFilter("COUNTUP_UPDATED"));

    }
    public void pararServicio (View view){
        //Llama al onDestroy del Service
        stopService(new Intent(this,Servicio1.class));

    }
}

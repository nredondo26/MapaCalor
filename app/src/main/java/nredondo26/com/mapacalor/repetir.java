package nredondo26.com.mapacalor;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class repetir extends Service {

    private Handler festHandler;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.e("TAG","El Servicio Inicio la programacion");
        periodicallunistall();
        return START_STICKY;
    }

    private void periodicallunistall() {
        if (festHandler == null) {
            festHandler = new Handler();
            festHandler.post(myRunnable);
        }
    }

    private Runnable myRunnable = new Runnable(){
        @SuppressLint("NewApi")
        @Override
        public void run(){
            if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                Log.e("TAG","IMPRIMIENDO PRUEBA CADA MINUTO EN ANDROID O ");
            }else {
                Log.e("TAG","IMPRIMIENDO PRUEBA CADA MINUTO EN ANDROID -0");
                Intent intent = new Intent(getApplicationContext(),Service_ubicacion.class);
                startService(intent);
            }
            festHandler.postDelayed(this, 60000);
        }
    };

}

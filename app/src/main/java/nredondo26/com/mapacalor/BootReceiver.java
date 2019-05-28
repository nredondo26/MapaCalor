package nredondo26.com.mapacalor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Servicio iniciado nuevamente", Toast.LENGTH_SHORT).show();
    //    context.startService(new Intent(context, Temporizador.class));
    }

}
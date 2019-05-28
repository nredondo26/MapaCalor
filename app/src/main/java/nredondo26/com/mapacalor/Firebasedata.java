package nredondo26.com.mapacalor;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class Firebasedata {

    static void guarda_ubicacion(double lat, double longi,String user,int contrato){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> coordenadas = new HashMap<>();
        coordenadas.put("latitud", lat);
        coordenadas.put("longitud", longi);
        coordenadas.put("user", user);
        coordenadas.put("contrato", contrato);

        db.collection("coordenadas").document()
                .set(coordenadas)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error writing document", e);
                    }
                });
    }


}

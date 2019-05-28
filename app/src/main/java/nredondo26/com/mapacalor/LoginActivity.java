package nredondo26.com.mapacalor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText email,pass,contrato;
    FirebaseAuth mAuth;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email= findViewById(R.id.email);
        pass = findViewById(R.id.password);
        contrato = findViewById(R.id.contato);
        login= findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm()){
                    signIn(email.getText().toString(),pass.getText().toString(), Integer.parseInt(contrato.getText().toString()));
                }else{
                    Toast.makeText(getApplicationContext(),"Todos los campos son obligatorios",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean validateForm() {
        boolean valid = true;
        String emailv = email.getText().toString();
        if (TextUtils.isEmpty(emailv)) {
            email.setError("Email requerido");
            valid = false;
        } else {
            email.setError(null);
        }
        String password = pass.getText().toString();
        if (TextUtils.isEmpty(password)) {
            pass.setError("Password Requerido");
            valid = false;
        } else {
            pass.setError(null);
        }
        String contatov = contrato.getText().toString();
        if (TextUtils.isEmpty(contatov)) {
            contrato.setError("Contrato Obligarorio");
            valid = false;
        } else {
            contrato.setError(null);
        }
        return valid;
    }

    private void signIn(String email, String password, final int contra) {
      // dialogo();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();
                            assert user != null;
                            Log.e("mensaje", user.getEmail());
                            guardar_preferecias(user.getEmail(),contra);
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);

                        } else {
                            Log.w("mesaje", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authenticacion fallida.", Toast.LENGTH_SHORT).show();
                           // progressDoalog.dismiss();
                        }
                    }
                });
    }

   /* private void dialogo(){
        progressDoalog = new ProgressDialog(this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Validando datos");
        progressDoalog.setTitle("validando");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
    }*/

    public void guardar_preferecias(String emaill, int contrato){
        SharedPreferences userDetails = getSharedPreferences("preferenciauser", MODE_PRIVATE);
        SharedPreferences.Editor editor = userDetails.edit();
        editor.putString("email",emaill);
        editor.putInt("contrato",contrato);
        editor.apply();
        Log.e("preferencias","guardadas");
    }

}

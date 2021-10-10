package com.example.iniciosesion;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "GoogleActivity";
    private GoogleSignInClient mGoogleSignInClient;
    private Button BtnIniciar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        BtnIniciar = (Button)findViewById(R.id.btnIniciar);
        BtnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("Success", "nombre:" + account.getDisplayName());
                Log.d("Success", "correo:" + account.getEmail());
                Log.d("Success", "foto:" + account.getPhotoUrl());
                String correo= String.valueOf(account.getEmail());
                //firebaseAuthWithGoogle(account.getIdToken());
                validarCorreo(correo);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("Error", "Google sign in failed", e);
                //Log.w(TAG, "Google sign in failed", e);
            }
        }
    }






    private void iniciarSesion() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void cerrarSesion(){
        FirebaseAuth.getInstance().signOut();

    }




    private void llamadoClase() {
        Intent i=new Intent(this, profileAc.class);
        startActivity(i);
        finish();
    }


    public void validarCorreo(String correo){
        //String correo="dominguezyankke@gmail.com";
        //String correo="franklin.dominguez.est@tecazuay.edu.ec";
        String busca="est@tecazuay.edu.ec";
        boolean resultado=correo.contains(busca);
        if (resultado==true){
            Toast.makeText(this,"Inicio sesión con exito",Toast.LENGTH_SHORT).show();
            llamadoClase();
        }else{
            Toast.makeText(this,"Inicie sesión con su correo institucional",Toast.LENGTH_SHORT).show();
           // cerrarSesion();
        }
    }
}
package com.example.iniciosesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class profileAc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
    }

    public void cerrarSesion(View view){
        FirebaseAuth.getInstance().signOut();
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
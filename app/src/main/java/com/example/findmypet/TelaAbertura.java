package com.example.findmypet;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.findmypet.Formularios.Login;

public class TelaAbertura extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_abertura);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarHome();
            }
        }, 2000);
    }

    private void mostrarHome() {
        Intent intent = new Intent(this,
                Login.class);
        startActivity(intent);
        finish();
    }
}

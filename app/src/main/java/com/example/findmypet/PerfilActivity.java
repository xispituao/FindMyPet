package com.example.findmypet;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findmypet.Adapters.AdapterAnimalPerdido;
import com.example.findmypet.Adapters.AdapterPublicacoesPerfil;
import com.example.findmypet.Modelos.Publicacao;

import java.util.ArrayList;

public class PerfilActivity extends AppCompatActivity {
    private TextView nome;
    private TextView email;
    private TextView telefone;
    private TextView sexo;
    private ArrayList<Publicacao> publicacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        nome = (TextView)findViewById(R.id.nome);
        email = (TextView)findViewById(R.id.email);
        telefone = (TextView)findViewById(R.id.telefone);
        sexo = (TextView)findViewById(R.id.sexo);
        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);

        /*pegarNoBdPublicacoes();
        LinearLayoutManager linearlayout = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearlayout);
        AdapterPublicacoesPerfil adapter = new AdapterPublicacoesPerfil(this, publicacoes);
        recyclerview.setAdapter(adapter);*/
    }


}

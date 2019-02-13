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
import com.example.findmypet.Modelos.Publicacao_;
import com.example.findmypet.Modelos.Usuario;
import com.example.findmypet.Modelos.Usuario_;
import com.example.findmypet.dal.App;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.QueryBuilder;

public class PerfilActivity extends AppCompatActivity {
    private TextView nome;
    private TextView email;
    private TextView telefone;
    private TextView sexo;
    private List<Publicacao> publicacoes;
    private Box<Publicacao> dataBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        BoxStore store = ((App) getApplication()).getBoxStore();
        dataBox = store.boxFor(Publicacao.class);

        nome = (TextView)findViewById(R.id.nome);
        email = (TextView)findViewById(R.id.email);
        telefone = (TextView)findViewById(R.id.telefone);
        sexo = (TextView)findViewById(R.id.sexo);
        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);

        pegarNoBdPublicacoes();
        LinearLayoutManager linearlayout = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearlayout);
        AdapterPublicacoesPerfil adapter = new AdapterPublicacoesPerfil(this, publicacoes, dataBox);
        recyclerview.setAdapter(adapter);
    }

    private void pegarNoBdPublicacoes(){
        QueryBuilder<Publicacao> builder = dataBox.query();
        builder.link(Publicacao_.usuario).equal(Usuario_.email, Usuario.getUsuarioLogado().getEmail());
        publicacoes = builder.build().find();
    }


}

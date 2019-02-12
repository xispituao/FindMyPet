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
import com.example.findmypet.DAO.ConfiguracaoFirebase;
import com.example.findmypet.Modelos.Publicacao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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

    public void pegarNoBdPublicacoes() {
        CollectionReference publicacoesReferencia = ConfiguracaoFirebase.BancoDeDados().collection("publicacoes");
        //Query query = publicacoesReferencia.where
        publicacoesReferencia.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Publicacao publicacao = document.toObject(Publicacao.class);
                        publicacoes.add(publicacao);
                    }
                } else {
                    Toast.makeText(PerfilActivity.this, "ERRO AO PUXAR OS DADOS DO BD PUBLICACOES", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}

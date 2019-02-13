package com.example.findmypet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findmypet.Adapters.AdapterComentarios;
import com.example.findmypet.Modelos.Comentario;
import com.example.findmypet.Modelos.Publicacao;
import com.example.findmypet.dal.App;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.relation.ToMany;

public class DetalheAnimal extends AppCompatActivity {
    Box<Publicacao> dataBox;
   ToMany<Comentario> comentarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_animal);
        BoxStore store = ((App) getApplication()).getBoxStore();
        dataBox = store.boxFor(Publicacao.class);

        final long id = getIntent().getLongExtra("IdPublicacao",0);
        Publicacao publicacao = dataBox.get(id);
        comentarios = publicacao.getComentarios();

        Log.e("comentarios", "" + comentarios);
        Toast.makeText(DetalheAnimal.this, "" + comentarios, Toast.LENGTH_LONG).show();

        //ImageView imagem = (ImageView) findViewById(R.id.imagemAnimal);
        TextView nome = (TextView) findViewById(R.id.nome);
        TextView raca = (TextView) findViewById(R.id.raca);
        TextView especie = (TextView) findViewById(R.id.especie);
        TextView cor_pelo = (TextView) findViewById(R.id.cor_pelo);
        TextView cor_olhos= (TextView) findViewById(R.id.cor_olhos);
        TextView descricao = (TextView) findViewById(R.id.descricao);
        TextView usuario = (TextView) findViewById(R.id.usuario);
        Button adicionarComentario = (Button) findViewById(R.id.adicionarComentario);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        //imagem.setImageResource(getIntent().getIntExtra("Imagem",0));
        nome.setText(getIntent().getStringExtra("Nome"));
        especie.setText(getIntent().getStringExtra("Especie"));
        raca.setText(getIntent().getStringExtra("Raca"));
        cor_pelo.setText(getIntent().getStringExtra("Cor_pelo"));
        cor_olhos.setText(getIntent().getStringExtra("Cor_olhos"));
        descricao.setText(getIntent().getStringExtra("Descricao"));
        usuario.setText(getIntent().getStringExtra("Nome_usuario"));



        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearlayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearlayout);
        AdapterComentarios adapter = new AdapterComentarios(comentarios);
        recyclerView.setAdapter(adapter);
        adicionarComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalheAnimal.this, AdicionarComentarioActivity.class);
                intent.putExtra("publicacaoId",id);
                startActivity(intent);
            }
        });
    }


}

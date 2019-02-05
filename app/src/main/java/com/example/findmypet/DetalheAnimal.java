package com.example.findmypet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalheAnimal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_animal);
        //ImageView imagem = (ImageView) findViewById(R.id.imagemAnimal);
        TextView nome = (TextView) findViewById(R.id.nome);
        TextView raca = (TextView) findViewById(R.id.raca);
        TextView especie = (TextView) findViewById(R.id.especie);
        TextView cor = (TextView) findViewById(R.id.cor);
        TextView descricao = (TextView) findViewById(R.id.descricao);

        //imagem.setImageResource(getIntent().getIntExtra("Imagem",0));
        nome.setText(getIntent().getStringExtra("Nome"));
        especie.setText(getIntent().getStringExtra("Especie"));
        raca.setText(getIntent().getStringExtra("Raca"));
        cor.setText(getIntent().getStringExtra("Cor"));
        descricao.setText(getIntent().getStringExtra("Descricao"));
    }


    public void voltar(View view) {
        finish();
    }
}

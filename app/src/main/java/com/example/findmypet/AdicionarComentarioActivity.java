package com.example.findmypet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findmypet.Modelos.Comentario;
import com.example.findmypet.Modelos.Publicacao;
import com.example.findmypet.Modelos.Usuario;
import com.example.findmypet.dal.App;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class AdicionarComentarioActivity extends AppCompatActivity {
    private Box<Publicacao> dataBoxPublicacao;
    private Box<Comentario> dataBoxComentario;
    private EditText comentario;
    private Button salvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_comentario);

        BoxStore store = ((App) getApplication()).getBoxStore();
        dataBoxPublicacao = store.boxFor(Publicacao.class);
        dataBoxComentario = store.boxFor(Comentario.class);

        long id = getIntent().getLongExtra("publicacaoId", 0);
        final Publicacao publicacao = dataBoxPublicacao.get(id);
        comentario = (EditText)findViewById(R.id.Comentario);
        salvar = (Button)findViewById(R.id.Salvar);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comentarioString = comentario.getText().toString();
                Comentario newcomentario = new Comentario();
                newcomentario.setConteudo(comentarioString);
                newcomentario.usuario.setTarget(Usuario.getUsuarioLogado());
                dataBoxComentario.put(newcomentario);
                publicacao.getComentarios().add(newcomentario);
                Toast.makeText(AdicionarComentarioActivity.this,"Comentario adicionado", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}

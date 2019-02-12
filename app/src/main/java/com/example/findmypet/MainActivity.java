package com.example.findmypet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findmypet.Adapters.AdapterAnimalPerdido;
import com.example.findmypet.DAO.ConfiguracaoFirebase;
import com.example.findmypet.Formularios.FormularioPost;
import com.example.findmypet.Modelos.Animal;
import com.example.findmypet.Modelos.Publicacao;
import com.example.findmypet.Modelos.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private FirebaseUser usuarioLogado;
    private ArrayList<Publicacao> publicacoes = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        View headerView = nav_view.getHeaderView(0);
        TextView usuarioLogadoTexView = (TextView)headerView.findViewById(R.id.UsuarioNavview);
        TextView emailUsuarioLogado = (TextView)headerView.findViewById(R.id.emailNavview) ;


        usuarioLogadoTexView.setText(Usuario.obterOUsuarioLogado().getNome());
        emailUsuarioLogado.setText(Usuario.obterOUsuarioLogado().getEmail());

        drawerLayout = (DrawerLayout) findViewById(R.id.dl);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.Open, R.string.Close );
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.perfil){
                    abrirTelaDePerfil();
                }else if(id == R.id.criarPublicao){
                    abrirTelaDeFormularioDePublicacao();
                } else if(id == R.id.teste3){
                    Toast.makeText(MainActivity.this, "TESTE3!",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        pegarNoBdPublicacoes();

        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);

        LinearLayoutManager linearlayout = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearlayout);
        AdapterAnimalPerdido adapter = new AdapterAnimalPerdido(this, publicacoes);
        recyclerview.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private void abrirTelaDeFormularioDePublicacao(){
        Intent intent = new Intent(MainActivity.this, FormularioPost.class);
        startActivity(intent);
    }
    private void abrirTelaDePerfil(){
        Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
        startActivity(intent);
    }

    public void pegarNoBdPublicacoes() {
        CollectionReference publicacoesReferencia = ConfiguracaoFirebase.BancoDeDados().collection("publicacoes");
        publicacoesReferencia.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Publicacao publicacao = document.toObject(Publicacao.class);
                        publicacoes.add(publicacao);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "ERRO AO PUXAR OS DADOS DO BD PUBLICACOES", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}

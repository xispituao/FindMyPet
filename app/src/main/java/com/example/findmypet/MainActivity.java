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

import com.example.findmypet.Formularios.FormularioPost;
import com.example.findmypet.Formularios.Login;
import com.example.findmypet.Modelos.Animal;
import com.example.findmypet.Modelos.Publicacao;
import com.example.findmypet.Modelos.Usuario;
import com.example.findmypet.dal.App;


import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    Box<Publicacao> dataBox;

    private ArrayList<Publicacao> publicacoes = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BoxStore store = ((App) getApplication()).getBoxStore();
        dataBox = store.boxFor(Publicacao.class);

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
                } else if(id == R.id.deslogar){
                    deslogar();
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

    private void pegarNoBdPublicacoes() {
        List<Publicacao> publicacoesBox = dataBox.getAll();
        publicacoes.addAll(publicacoesBox);
    }

    private void deslogar(){
        Usuario.setUsuarioLogado(null);
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
        finish();
    }


}

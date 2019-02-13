package com.example.findmypet.Formularios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.findmypet.MainActivity;
import com.example.findmypet.Modelos.Usuario;
import com.example.findmypet.R;
import com.example.findmypet.dal.App;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class Login extends AppCompatActivity {
    private EditText email;
    private EditText senha;
    private Button logar;
    private TextView cadastrar;
    private Box<Usuario> dataBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BoxStore store = ((App) getApplication()).getBoxStore();
        dataBox = store.boxFor(Usuario.class);

        email = (EditText)findViewById(R.id.email);
        senha = (EditText)findViewById(R.id.senha);
        logar = (Button)findViewById(R.id.logar);
        cadastrar = (TextView)findViewById(R.id.cadastrar);

        Spanned text = Html.fromHtml("<u>Não tem cadastro? Clique aqui!</u>");
        cadastrar.setText(text);

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailUsuario = email.getText().toString();
                String senhaUsuario = senha.getText().toString();
                if (!emailUsuario.equals("") && !senhaUsuario.equals("")){
                    int auxiliar = 1;
                    List<Usuario> usuarios = dataBox.getAll();
                    for(Usuario usuario:usuarios){
                        if(usuario.getEmail().equals(emailUsuario) && usuario.getSenha().equals(senhaUsuario)){
                            Toast.makeText(Login.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                            Usuario.setUsuarioLogado(usuario);
                            auxiliar = 2;
                            redirecionarParaTelaPrincipal();
                            break;
                        }
                    }
                    if(auxiliar == 1) {
                        Toast.makeText(Login.this, "Usuario e/ou senha errada(s)!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

       cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirecionarParaCadastro();
            }
        });
    }

    private void redirecionarParaTelaPrincipal(){
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void redirecionarParaCadastro(){
        Intent intent = new Intent(Login.this, CadastroActivity.class);
        startActivity(intent);
    }



}

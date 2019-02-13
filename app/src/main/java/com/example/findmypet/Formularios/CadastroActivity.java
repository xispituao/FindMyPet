package com.example.findmypet.Formularios;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


import com.example.findmypet.MainActivity;
import com.example.findmypet.Modelos.Publicacao;
import com.example.findmypet.Modelos.Usuario;
import com.example.findmypet.R;
import com.example.findmypet.dal.App;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class CadastroActivity extends AppCompatActivity {
    private EditText edtCadEmail;
    private EditText edtCadNome;
    private EditText edtCadSenha;
    private EditText edtCadConfirmaSenha;
    private RadioButton rbMasculino;
    private RadioButton rbFeminino;
    private Button btnCadastrar;
    private EditText edttelefone;
    BoxStore store;
    Box<Usuario> dataBox;

    private List<Publicacao> publicacoesDados = new ArrayList<>();

    private static final String TAG = "CADASTRO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        store = ((App) getApplication()).getBoxStore();
        dataBox = store.boxFor(Usuario.class);


        edtCadEmail = (EditText) findViewById(R.id.edtCadEmail);
        edtCadNome = (EditText) findViewById(R.id.edtCadNome);
        edtCadSenha = (EditText) findViewById(R.id.edtCadSenha);
        edtCadConfirmaSenha = (EditText) findViewById(R.id.edtCadConfirmarSenha);
        rbFeminino = (RadioButton) findViewById(R.id.rbFeminino);
        rbMasculino = (RadioButton) findViewById(R.id.rbMasculino);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        edttelefone = (EditText)findViewById(R.id.edtTelefone);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtCadSenha.getText().toString().equals(edtCadConfirmaSenha.getText().toString())) {
                    final String nome = edtCadNome.getText().toString();
                    final String telefone = edttelefone.getText().toString();
                    final String email = edtCadEmail.getText().toString();
                    final String senha = edtCadSenha.getText().toString();
                    final String sexo;
                    if (rbFeminino.isChecked()) {
                        sexo = "feminino";
                    } else {
                        sexo = "masculino";
                    }

                    final Usuario usuario = new Usuario();
                    usuario.setNome(nome);
                    usuario.setEmail(email);
                    usuario.setSenha(senha);
                    usuario.setSexo(sexo);
                    usuario.setTelefone(telefone);
                    dataBox.put(usuario);
                    Toast.makeText(CadastroActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                    irPraPaginaDeLogin();

                }
            }
        });

    }

    private void irPraPaginaDeLogin(){
        Intent intent = new Intent(CadastroActivity.this, Login.class);
        startActivity(intent);
        finish();
    }


}

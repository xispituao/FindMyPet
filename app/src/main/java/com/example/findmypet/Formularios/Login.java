package com.example.findmypet.Formularios;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findmypet.DAO.ConfiguracaoFirebase;
import com.example.findmypet.MainActivity;
import com.example.findmypet.Modelos.Usuario;
import com.example.findmypet.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

public class Login extends AppCompatActivity {
    private EditText email;
    private EditText senha;
    private Button logar;
    private TextView cadastrar;
    private FirebaseAuth autenticacao;
    private DocumentReference usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText)findViewById(R.id.email);
        senha = (EditText)findViewById(R.id.senha);
        logar = (Button)findViewById(R.id.logar);
        cadastrar = (TextView)findViewById(R.id.cadastrar);

        Spanned text = Html.fromHtml("<u>Não tem cadastro? Clique aqui!</u>");
        cadastrar.setText(text);

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!email.getText().toString().equals("") && !senha.getText().toString().equals("")){
                    autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
                    autenticacao.signInWithEmailAndPassword(email.getText().toString(),senha.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Login.this, "Login feito com sucesso", Toast.LENGTH_SHORT).show();
                                usuario = ConfiguracaoFirebase.BancoDeDados().collection("usuarios").document(email.getText().toString());
                                usuario.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        Usuario usuarioLogado = documentSnapshot.toObject(Usuario.class);
                                        Usuario.logar(usuarioLogado);
                                        redirecionarParaTelaPrincipal();
                                    }
                                });

                            }else {
                                Toast.makeText(Login.this, "Email ou/e senha errados!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            }}
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

package com.example.findmypet.Formularios;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.findmypet.DAO.ConfiguracaoFirebase;
import com.example.findmypet.Modelos.Usuario;
import com.example.findmypet.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadastroActivity extends AppCompatActivity {
    private EditText edtCadEmail;
    private EditText edtCadNome;
    private EditText edtCadSenha;
    private EditText edtCadConfirmaSenha;
    private RadioButton rbMasculino;
    private RadioButton rbFeminino;
    private Button btnCadastrar;
    private FirebaseFirestore bd;
    private FirebaseAuth autenticacao;

    private static final String TAG = "CADASTRO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        bd = ConfiguracaoFirebase.BancoDeDados();

        edtCadEmail = (EditText) findViewById(R.id.edtCadEmail);
        edtCadNome = (EditText) findViewById(R.id.edtCadNome);
        edtCadSenha = (EditText) findViewById(R.id.edtCadSenha);
        edtCadConfirmaSenha = (EditText) findViewById(R.id.edtCadConfirmarSenha);
        rbFeminino = (RadioButton) findViewById(R.id.rbFeminino);
        rbMasculino = (RadioButton) findViewById(R.id.rbMasculino);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtCadSenha.getText().toString().equals(edtCadConfirmaSenha.getText().toString())) {
                    final String nome = edtCadNome.getText().toString();
                    final String email = edtCadEmail.getText().toString();
                    final String senha = edtCadSenha.getText().toString();
                    final String sexo;
                    if (rbFeminino.isChecked()) {
                       sexo = "feminino";
                    } else {
                        sexo = "masculino";;
                    }

                    final Usuario usuario = new Usuario();
                    usuario.setNome(nome);
                    usuario.setEmail(email);
                    usuario.setSenha(senha);
                    usuario.setSexo(sexo);

                    autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
                    autenticacao.createUserWithEmailAndPassword(
                            usuario.getEmail(),
                            usuario.getSenha()
                    ).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(CadastroActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                                Map<String, Object> newusuario = new HashMap<>();
                                newusuario.put("email", email);
                                newusuario.put("nome", nome);
                                newusuario.put("senha", senha);
                                newusuario.put("sexo", sexo);

                                Usuario.logar(usuario);

                                bd.collection("usuarios").document(email)
                                        .set(newusuario)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d(TAG, "Cadastro com sucesso");
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "ERRO no cadastro!");
                                    }
                                });
                                finish();
                            } else {
                                String erroExcecao = "";

                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthWeakPasswordException e) {
                                    erroExcecao = " Digite uma senha mais forte, contendo no mínimo 8 caracteres de letras e números";
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    erroExcecao = " O e-mail digitado é inválido, digite um novo e-mail";
                                } catch (FirebaseAuthUserCollisionException e) {
                                    erroExcecao = "Esse e-mail já está cadastrado no sistema";
                                } catch (Exception e) {
                                    erroExcecao = "Erro ao efetuar o cadastro!";
                                    e.printStackTrace();
                                }
                                Toast.makeText(CadastroActivity.this, "Erro: " + erroExcecao, Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }else {
                    Toast.makeText(CadastroActivity.this, "Senhas não coincidem!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

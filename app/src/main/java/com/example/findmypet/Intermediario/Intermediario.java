package com.example.findmypet.Intermediario;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.findmypet.DAO.ConfiguracaoFirebase;
import com.example.findmypet.Formularios.Login;
import com.example.findmypet.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Intermediario {
    private static FirebaseAuth autenticacao;
    private static FirebaseUser usuario;
    private static boolean situacao;

    public Intermediario() {
        pegarUsuarioAtualLogado();
    }

    public static FirebaseUser pegarUsuarioAtualLogado(){
        usuario = autenticacao.getCurrentUser();
        return usuario;
    }


}

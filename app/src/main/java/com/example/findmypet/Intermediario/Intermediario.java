package com.example.findmypet.Intermediario;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.findmypet.DAO.ConfiguracaoFirebase;
import com.example.findmypet.Formularios.Login;
import com.example.findmypet.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Map;

public class Intermediario {
    private static FirebaseAuth autenticacao;
    private static FirebaseUser usuario;
    private static boolean situacao;
    private static  boolean cadastrarNovaPublicacao;


    public static boolean cadastrarNovaPublicacao(Map<String,String> dadosDoAnimal){
        cadastrarNovaPublicacao = false;
        FirebaseFirestore bd = ConfiguracaoFirebase.BancoDeDados();
        bd.collection("publicacoes").add(dadosDoAnimal).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                cadastrarNovaPublicacao = true;
            }
        });
        return cadastrarNovaPublicacao;
    }




}

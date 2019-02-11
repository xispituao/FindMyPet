package com.example.findmypet.DAO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class ConfiguracaoFirebase {
    private static DatabaseReference refereciaFirebase;
    private static FirebaseAuth autenticacao;
    private static FirebaseFirestore bancodedados;


    public static DatabaseReference getFirebase(){
        if (refereciaFirebase == null){
            refereciaFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return refereciaFirebase;
    }

    public static FirebaseAuth getFirebaseAutenticacao(){
        if (autenticacao == null){
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }

    public static  FirebaseFirestore BancoDeDados(){
        if (bancodedados== null){
            bancodedados = FirebaseFirestore.getInstance();
        }
        return bancodedados;
    }

}

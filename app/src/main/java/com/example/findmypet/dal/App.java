package com.example.findmypet.dal;

import android.app.Application;

import com.example.findmypet.Modelos.MyObjectBox;

import io.objectbox.BoxStore;

public class App extends Application {
    private BoxStore boxStore;

    @Override
    public void onCreate() {
        inicializarObjectBox();
        super.onCreate();
    }

    private void inicializarObjectBox(){
        // OBS: Deve ser criada uma classe e executar o app antes de escrever o código abaixo
        // Instancia a classe BoxStore do MyObjectBox na variavel boxStore o builder facilita a
        // criação do boxStore.
        boxStore = MyObjectBox.builder().androidContext(this).build();
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }
}

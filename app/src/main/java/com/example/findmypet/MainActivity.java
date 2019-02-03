package com.example.findmypet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.findmypet.Adapters.AdapterAnimalPerdido;
import com.example.findmypet.Models.Animal;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerview;
    Animal[] animais = {new Animal("Cachorro", "Preta", "Labrador"),
            new Animal("Gato", "Preta", "Siames"),new Animal("Gato", "Branca", "Siames")
    ,new Animal("Gato", "Preta", "Seila")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);

        LinearLayoutManager linearlayout = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearlayout);
        AdapterAnimalPerdido adapter = new AdapterAnimalPerdido(animais);
        recyclerview.setAdapter(adapter);

    }
}

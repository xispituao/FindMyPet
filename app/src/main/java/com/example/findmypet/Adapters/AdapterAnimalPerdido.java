package com.example.findmypet.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findmypet.Models.Animal;
import com.example.findmypet.R;

import java.util.List;

public class AdapterAnimalPerdido extends RecyclerView.Adapter<AdapterAnimalPerdido.AnimalViewHolder> {
    private Animal[] animais;

    public AdapterAnimalPerdido(Animal[] animais){
        this.animais = animais;
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
        AnimalViewHolder pvh = new AnimalViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder animalViewHolder, int i) {
        animalViewHolder.animalEspecie.setText(animais[i].getEspecie());
        animalViewHolder.animalCor.setText(animais[i].getCor());
        animalViewHolder.animalRaca.setText(animais[i].getRaca());
    }

    @Override
    public int getItemCount() {
        return animais.length;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public static class AnimalViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView animalEspecie;
        TextView animalRaca;
        TextView animalCor;

        AnimalViewHolder(View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardview);
            animalEspecie = (TextView) itemView.findViewById((R.id.especie));
            animalCor = (TextView) itemView.findViewById(R.id.cor);
            animalRaca = (TextView) itemView.findViewById(R.id.raca);
        }
    }
}

package com.example.findmypet.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.findmypet.DetalheAnimal;
import com.example.findmypet.Modelos.Animal;
import com.example.findmypet.R;

public class AdapterAnimalPerdido extends RecyclerView.Adapter<AdapterAnimalPerdido.AnimalViewHolder> {
    private Animal[] animais;
    private Context mContext;

    public AdapterAnimalPerdido(Context context, Animal[] animais){
        this.mContext = context;
        this.animais = animais;
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_card_view , viewGroup, false);
        AnimalViewHolder pvh = new AnimalViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder animalViewHolder, int i) {
        final Animal animal = animais[i];
        animalViewHolder.animalNome.setText(animal.getNome());
        animalViewHolder.animalCor.setText(animal.getCor_pelo());
        animalViewHolder.animalRaca.setText(animal.getRaca());
        /*animalViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,DetalheAnimal.class);
                //intent.putExtra("Imagem", animal.getFoto());
                intent.putExtra("Nome", animal.getNome());
                intent.putExtra("Especie", animal.getEspecie());
                intent.putExtra("Raca", animal.getRaca());
                intent.putExtra("Cor", animal.getCor());
                intent.putExtra("Descricao", animal.getDescricao());
                mContext.startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return animais.length;
    }





    public class AnimalViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView animalNome;
        TextView animalRaca;
        TextView animalCor;

        AnimalViewHolder(View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardview);
            animalNome = (TextView) itemView.findViewById((R.id.nome));
            animalCor = (TextView) itemView.findViewById(R.id.cor);
            animalRaca = (TextView) itemView.findViewById(R.id.raca);
            clicarCard(itemView);

        }

        private void clicarCard(View view){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int posicao = getAdapterPosition();
                    Animal animal = animais[posicao];
                    Intent intent = new Intent(mContext,DetalheAnimal.class);
                    //intent.putExtra("Imagem", animal.getFoto());
                    intent.putExtra("Nome", animal.getNome());
                    intent.putExtra("Especie", animal.getEspecie());
                    intent.putExtra("Raca", animal.getRaca());
                    intent.putExtra("Cor", animal.getCor_pelo());
                    intent.putExtra("Descricao", animal.getCaracteristicasAdicionais());
                    mContext.startActivity(intent);
                }
            });

        }
    }

}

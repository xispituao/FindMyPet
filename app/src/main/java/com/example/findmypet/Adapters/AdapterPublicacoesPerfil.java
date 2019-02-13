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
import com.example.findmypet.Modelos.Publicacao;
import com.example.findmypet.R;

import java.util.ArrayList;

import io.objectbox.relation.ToOne;

public class AdapterPublicacoesPerfil extends RecyclerView.Adapter<AdapterPublicacoesPerfil.AnimalPerfilViewHolder> {
    private ArrayList<Publicacao> publicacoesDados;
    private Context mContext;

    public AdapterPublicacoesPerfil(Context context, ArrayList<Publicacao> publicacoes){
        this.mContext = context;
        this.publicacoesDados = publicacoes;
    }

    @NonNull
    @Override
    public AdapterPublicacoesPerfil.AnimalPerfilViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_perfil, viewGroup, false);
        AdapterPublicacoesPerfil.AnimalPerfilViewHolder pvh = new AdapterPublicacoesPerfil.AnimalPerfilViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPublicacoesPerfil.AnimalPerfilViewHolder animalViewHolder, int i) {
        final ToOne<Animal> animal1 = publicacoesDados.get(i).getAnimal();
        Animal animal = animal1.getTarget();
        animalViewHolder.animalNome.setText(animal.getNome());
        animalViewHolder.animalCor.setText(animal.getCor_pelo());
        animalViewHolder.animalRaca.setText(animal.getRaca());

    }

    @Override
    public int getItemCount() {
        return publicacoesDados.size();
    }





    public class AnimalPerfilViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView animalNome;
        TextView animalRaca;
        TextView animalCor;

        AnimalPerfilViewHolder(View itemView){
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
                    /*final int posicao = getAdapterPosition();
                    Publicacao publicacao = publicacoesDados.get(posicao);
                    Intent intent = new Intent(mContext, DetalheAnimal.class);
                    //intent.putExtra("Imagem", animal.getFoto());
                    intent.putExtra("Nome", publicacao.getAnimal().getNome());
                    intent.putExtra("Especie", publicacao.getAnimal().getEspecie());
                    intent.putExtra("Raca", publicacao.getAnimal().getRaca());
                    intent.putExtra("Cor", publicacao.getAnimal().getCor_pelo());
                    intent.putExtra("Descricao", publicacao.getAnimal().getCaracteristicasAdicionais());
                    intent.putExtra("Nome_usuario", publicacao.getUsuario().getNome());
                    intent.putExtra("Email", publicacao.getUsuario().getEmail());
                    mContext.startActivity(intent);*/
                }
            });

        }
    }

}

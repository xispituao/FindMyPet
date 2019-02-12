package com.example.findmypet.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findmypet.DAO.ConfiguracaoFirebase;
import com.example.findmypet.DetalheAnimal;
import com.example.findmypet.Modelos.Animal;
import com.example.findmypet.Modelos.Publicacao;
import com.example.findmypet.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdapterAnimalPerdido extends RecyclerView.Adapter<AdapterAnimalPerdido.AnimalViewHolder> {
    private ArrayList<Publicacao> publicacoesDados;
    private Context mContext;

    public AdapterAnimalPerdido(Context context, ArrayList<Publicacao> publicacoes){
        this.mContext = context;
        this.publicacoesDados = publicacoes;
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
        final Animal animal = publicacoesDados.get(i).getAnimal();
        animalViewHolder.animalNome.setText(animal.getNome());
        animalViewHolder.animalCor.setText(animal.getCor_pelo());
        animalViewHolder.animalRaca.setText(animal.getRaca());
        if(animal.getEspecie().equals("Cachorro")){
            animalViewHolder.foto.setImageResource(R.drawable.dog);
        }else if(animal.getEspecie().equals("Gato")){
            animalViewHolder.foto.setImageResource(R.drawable.gato);
        }

    }

    @Override
    public int getItemCount() {
        return publicacoesDados.size();
    }





    public class AnimalViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView animalNome;
        TextView animalRaca;
        TextView animalCor;
        ImageView foto;

        AnimalViewHolder(View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardview);
            animalNome = (TextView) itemView.findViewById((R.id.nome));
            animalCor = (TextView) itemView.findViewById(R.id.cor);
            animalRaca = (TextView) itemView.findViewById(R.id.raca);
            foto =(ImageView) itemView.findViewById(R.id.animal_photo);
            clicarCard(itemView);

        }

        private void clicarCard(View view){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int posicao = getAdapterPosition();
                    Publicacao publicacao = publicacoesDados.get(posicao);
                    Intent intent = new Intent(mContext,DetalheAnimal.class);
                    //intent.putExtra("Imagem", animal.getFoto());
                    intent.putExtra("Nome", publicacao.getAnimal().getNome());
                    intent.putExtra("Especie", publicacao.getAnimal().getEspecie());
                    intent.putExtra("Raca", publicacao.getAnimal().getRaca());
                    intent.putExtra("Cor", publicacao.getAnimal().getCor_pelo());
                    intent.putExtra("Descricao", publicacao.getAnimal().getCaracteristicasAdicionais());
                    intent.putExtra("Nome_usuario", publicacao.getUsuario().getNome());
                    intent.putExtra("Email", publicacao.getUsuario().getEmail());
                    mContext.startActivity(intent);
                }
            });

        }
    }



}

package com.example.findmypet.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findmypet.DetalheAnimal;
import com.example.findmypet.Modelos.Animal;
import com.example.findmypet.Modelos.Publicacao;
import com.example.findmypet.R;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.relation.ToOne;

public class AdapterPublicacoesPerfil extends RecyclerView.Adapter<AdapterPublicacoesPerfil.AnimalPerfilViewHolder> {
    private List<Publicacao> publicacoesDados;
    private Context mContext;
    private Box<Publicacao> publicacaoBox;

    public AdapterPublicacoesPerfil(Context context, List<Publicacao> publicacoes, Box<Publicacao> publicacoesBox){
        this.mContext = context;
        this.publicacoesDados = publicacoes;
        this.publicacaoBox = publicacoesBox;
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
        Button apagarPublicacao;

        AnimalPerfilViewHolder(View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardview);
            animalNome = (TextView) itemView.findViewById((R.id.nome));
            animalCor = (TextView) itemView.findViewById(R.id.cor);
            animalRaca = (TextView) itemView.findViewById(R.id.raca);
            apagarPublicacao = (Button)itemView.findViewById(R.id.apagarpublicacao);
            clicarCard(itemView);
            apagarPublicacao(apagarPublicacao);

        }

        private void apagarPublicacao(Button apagar){
            apagar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //atributo da classe.
                    AlertDialog alerta;
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    //define o titulo
                    builder.setTitle("Titulo");
                    //define a mensagem
                    builder.setMessage("Qualifique este software");
                    //define um botão como positivo
                    builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            publicacaoBox.remove(publicacoesDados.get(getAdapterPosition()));
                            Toast.makeText(mContext, "Apagado", Toast.LENGTH_SHORT).show();
                        }
                    });
                    //define um botão como negativo.
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(mContext, "Cancelado" , Toast.LENGTH_SHORT).show();
                        }
                    });
                    //cria o AlertDialog
                    alerta = builder.create();
                    //Exibe
                    alerta.show();

                }
            });
        }
        private void clicarCard(View view){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int posicao = getAdapterPosition();
                    Publicacao publicacao = publicacoesDados.get(posicao);
                    Intent intent = new Intent(mContext, DetalheAnimal.class);
                    //intent.putExtra("Imagem", animal.getFoto());
                    intent.putExtra("Nome", publicacao.getAnimal().getTarget().getNome());
                    intent.putExtra("Especie", publicacao.getAnimal().getTarget().getEspecie());
                    intent.putExtra("Raca", publicacao.getAnimal().getTarget().getRaca());
                    intent.putExtra("Cor", publicacao.getAnimal().getTarget().getCor_pelo());
                    intent.putExtra("Descricao", publicacao.getAnimal().getTarget().getCaracteristicasAdicionais());
                    intent.putExtra("Nome_usuario", publicacao.getUsuario().getTarget().getNome());
                    intent.putExtra("Email", publicacao.getUsuario().getTarget().getEmail());
                    mContext.startActivity(intent);
                }
            });

        }
    }

}

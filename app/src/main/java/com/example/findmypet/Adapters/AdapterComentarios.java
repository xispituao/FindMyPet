package com.example.findmypet.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.findmypet.Modelos.Comentario;
import com.example.findmypet.Modelos.Usuario;
import com.example.findmypet.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

import io.objectbox.relation.ToMany;

public class AdapterComentarios extends RecyclerView.Adapter<AdapterComentarios.ComentarioViewHolder> {
    ToMany<Comentario> comentarios;

    public AdapterComentarios(ToMany<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @NonNull
    @Override
    public ComentarioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comentarios,viewGroup,false);
        ComentarioViewHolder pvh = new ComentarioViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ComentarioViewHolder comentarioViewHolder, int i) {
        Comentario comentario = comentarios.get(i);
        Usuario usuario = comentario.getUsuario().getTarget();
        String text = "Criado por: " + usuario.getNome();
        comentarioViewHolder.usuario.setText(text);
        comentarioViewHolder.conteudo.setText(comentario.getConteudo());
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }

    public class ComentarioViewHolder extends RecyclerView.ViewHolder{
        TextView usuario;
        TextView conteudo;

        public ComentarioViewHolder(@NonNull View itemView) {
            super(itemView);
            usuario = (TextView)itemView.findViewById(R.id.usuario);
            conteudo = (TextView)itemView.findViewById((R.id.conteudo));
        }
    }
}

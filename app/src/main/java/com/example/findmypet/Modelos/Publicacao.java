package com.example.findmypet.Modelos;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

@Entity
public class Publicacao {
    @Id
    public long id;
    public ToOne<Usuario> usuario;
    public ToOne<Animal> animal;
    public ToMany<Comentario> comentarios;


    public Publicacao() {

    }

    public Publicacao(ToOne<Usuario> usuario, ToOne<Animal> animal) {
        this.usuario = usuario;
        this.animal = animal;
    }

    public ToOne<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(ToOne<Usuario> usuario) {
        this.usuario = usuario;
    }

    public ToOne<Animal> getAnimal() {
        return animal;
    }

    public void setAnimal(ToOne<Animal> animal) {
        this.animal = animal;
    }

    public ToMany<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ToMany<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}

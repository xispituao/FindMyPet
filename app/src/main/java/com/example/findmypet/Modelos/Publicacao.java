package com.example.findmypet.Modelos;

public class Publicacao {
    private Usuario usuario;
    private Animal animal;

    public Publicacao() {

    }
    public Publicacao(Usuario usuario, Animal animal){
        this.usuario = usuario;
        this.animal = animal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}

package com.example.findmypet.Modelos;


public class Animal {
    //private int foto;
    private String nome;
    private String especie;
    private String cor;
    private String raca;
    private String sexo;
    private String descricao;

    //Nao esquece de colocar int foto dentro dos paramentros novamente
    public Animal(String nome, String especie, String cor, String raca, String descricao) {
        //this.foto = foto;
        this.nome = nome;
        this.especie = especie;
        this.cor = cor;
        this.raca = raca;
        this.descricao = descricao;
        //this.sexo = sexo;
    }


    /*public int getFoto() {
        return foto;
    }*/

    public String getEspecie() {
        return especie;
    }

    public String getCor() {
        return cor;
    }

    public String getRaca() {
        return raca;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}

package com.example.findmypet.Models;


public class Animal {
    private int foto;
    private String especie;
    private String cor;
    private String raca;

    public Animal(String especie, String cor, String raca) {
        this.especie = especie;
        this.cor = cor;
        this.raca = raca;
    }


    public int getFoto() {
        return foto;
    }

    public String getEspecie() {
        return especie;
    }

    public String getCor() {
        return cor;
    }

    public String getRaca() {
        return raca;
    }

}

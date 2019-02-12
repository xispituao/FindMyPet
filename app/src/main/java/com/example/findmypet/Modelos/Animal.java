package com.example.findmypet.Modelos;


public class Animal {
    //private String url_foto;
    private String nome;
    private String especie;
    private String cor_pelo;
    private String cor_olhos;
    private String raca;
    private String sexo;
    private String caracteristicasAdicionais;

    public Animal(){

    }

    //Nao esquece de colocar int foto dentro dos paramentros novamente
    public Animal(/*String url_foto,*/String nome, String especie, String cor_pelo,String cor_olhos, String raca,String sexo, String caracteristicasAdicionais) {
        //this.url_foto = url_foto;
        this.nome = nome;
        this.especie = especie;
        this.cor_pelo = cor_pelo;
        this.cor_olhos = cor_olhos;
        this.raca = raca;
        this.caracteristicasAdicionais = caracteristicasAdicionais;
        this.sexo = sexo;
    }

    /*public String getUrl_foto() {
        return url_foto;
    }*/



    public String getEspecie() {
        return especie;
    }

    public String getCor_pelo() {
        return cor_pelo;
    }

    public String getCor_olhos() {
        return cor_olhos;
    }

    public String getSexo() {
        return sexo;
    }


    public String getRaca() {
        return raca;
    }

    public String getNome() {
        return nome;
    }

    public String getCaracteristicasAdicionais() {
        return caracteristicasAdicionais;
    }
}

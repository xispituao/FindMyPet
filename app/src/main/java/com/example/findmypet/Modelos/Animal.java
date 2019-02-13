package com.example.findmypet.Modelos;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Animal {
    @Id
    public long id;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getCor_pelo() {
        return cor_pelo;
    }

    public void setCor_pelo(String cor_pelo) {
        this.cor_pelo = cor_pelo;
    }

    public String getCor_olhos() {
        return cor_olhos;
    }

    public void setCor_olhos(String cor_olhos) {
        this.cor_olhos = cor_olhos;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCaracteristicasAdicionais() {
        return caracteristicasAdicionais;
    }

    public void setCaracteristicasAdicionais(String caracteristicasAdicionais) {
        this.caracteristicasAdicionais = caracteristicasAdicionais;
    }
}

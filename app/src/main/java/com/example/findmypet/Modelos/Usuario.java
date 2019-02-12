package com.example.findmypet.Modelos;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private String sexo;
    private static Usuario usuarioLogado;

    public Usuario() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static void logar(Usuario usuariologado){
        usuarioLogado = usuariologado;
    }

    public static Usuario obterOUsuarioLogado(){
        if (usuarioLogado != null) {
            return usuarioLogado;
        }else {
            return null;
        }
    }

    public static void deslogarUsuario(){
        usuarioLogado = null;
    }
}
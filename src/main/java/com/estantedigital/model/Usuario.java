package com.estantedigital.model;

public class Usuario {
    private long id; // deve ser único
    private String nomeCompleto;
    private String cpf; // deve ser único
    private String email;
    private String senha;
    private String livroEmPosse;
    private boolean bloqueado; // true ou false
    private String dataDeDesbloqueio; // Formato ISO: "2026-04-08" ou null

    public Usuario(String nomeCompleto, String cpf, String email, String senha) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.bloqueado = false; // todos os usuários começam desbloqueados
        this.dataDeDesbloqueio = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getPrimeiroNome() {
        if (this.nomeCompleto == null) { return " "; }

        String[] nomes = this.nomeCompleto.split(" ");
        String primeiroNome = nomes[0].toLowerCase(); // pega o primeiro nome minusculo
        return primeiroNome.substring(0, 1).toUpperCase() +
                primeiroNome.substring(1).toLowerCase(); // tranforma a primeira letra em maiusulo
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLivroEmPosse() {
        return livroEmPosse;
    }

    public void setLivroEmPosse(String livroEmPosse) {
        this.livroEmPosse = livroEmPosse;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getDataDeDesbloqueio() {
        return dataDeDesbloqueio;
    }

    public void setDataDeDesbloqueio(String dataDeDesbloqueio) {
        this.dataDeDesbloqueio = dataDeDesbloqueio;
    }

    @Override
    public String toString() {
        return "Usuario: " +
                this.nomeCompleto + " (" +
                this.cpf + ")";
    }
}

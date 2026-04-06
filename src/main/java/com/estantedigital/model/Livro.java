package com.estantedigital.model;

public class Livro extends ItemAcervo{

    private int ano_publicacao;

    public Livro(String titulo, String autor, String genero, int ano_publicacao) {
        super(titulo, autor);
        this.setGenero(genero);
        this.ano_publicacao = ano_publicacao;
    }
}

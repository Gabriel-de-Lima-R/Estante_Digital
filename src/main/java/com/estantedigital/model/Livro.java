package com.estantedigital.model;

public class Livro extends ItemAcervo{

    private String autor;
    private String genero;
    private int ano_publicacao;

    public Livro(String titulo, String autor, String genero, int ano_publicacao) {
        super(titulo);
        this.autor = autor;
        this.genero = genero;
        this.ano_publicacao = ano_publicacao;
        setTipo("livro");
    }


    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public int getAnoPublicacao() {
        return ano_publicacao;
    }

}

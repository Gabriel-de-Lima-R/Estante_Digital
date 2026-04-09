package com.estantedigital.model;

public class Revista extends ItemAcervo {

    private String editora_responsavel;
    private String genero;
    private int ano_publicacao;

    public Revista(String titulo, String editora, String genero, int ano_publicacao) {
        super(titulo);
        this.editora_responsavel = editora;
        this.genero = genero;
        this.ano_publicacao = ano_publicacao;
        setTipo("revista");
    }
}

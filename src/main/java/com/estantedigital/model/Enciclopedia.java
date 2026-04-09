package com.estantedigital.model;

public class Enciclopedia extends ItemAcervo {
    private String editora_responsavel;
    private String idioma;
    private int volumes;
    private int ano_edicao;

    public Enciclopedia(String titulo, String editora, String idioma, int volumes, int ano_edicao) {
        super(titulo);
        this.editora_responsavel = editora;
        this.idioma = idioma;
        this.volumes = volumes;
        this.ano_edicao = ano_edicao;
        setTipo("enciclopedia");
    }
}

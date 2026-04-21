package com.estantedigital.dto;

import com.estantedigital.model.ItemAcervo;

// Esta classe é apenas para RECEBER os dados do JSON (Por isso, DTO - Data Tranfer Object)
public class ItemAcervoDTO {
    // Campos comuns
    private long id;
    private String titulo;
    private String tipo;
    private String status;

    // Campos de LIVRO
    private String autor;
    private String genero;
    private int ano_publicacao;

    // Campos de REVISTA
    private String editora_responsavel;

    // Campos de ENCICLOPEDIA
    private String idioma;
    private int volumes;
    private int ano_edicao;

    // Getters e Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public int getAno_publicacao() { return ano_publicacao; }
    public void setAno_publicacao(int ano_publicacao) { this.ano_publicacao = ano_publicacao; }

    public String getEditora_responsavel() { return editora_responsavel; }
    public void setEditora_responsavel(String editora_responsavel) { this.editora_responsavel = editora_responsavel; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public int getVolumes() { return volumes; }
    public void setVolumes(int volumes) { this.volumes = volumes; }

    public int getAno_edicao() { return ano_edicao; }
    public void setAno_edicao(int ano_edicao) { this.ano_edicao = ano_edicao; }
}

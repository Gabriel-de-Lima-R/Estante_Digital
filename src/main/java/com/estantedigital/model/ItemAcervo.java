package com.estantedigital.model;

public class ItemAcervo {
    private long id;
    private String titulo;
    private String autor;
    private TipoItem tipo;
    private String genero;
    private StatusItem status;

    // Enum para itens e status (por serem fixos)
    public enum TipoItem {
        LIVRO,
        REVISTA,
        ENCICLOPEDIA
    }

    public enum StatusItem {
        DISPONIVEL,
        EMPRESTADO
    }


    public ItemAcervo(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.status = StatusItem.DISPONIVEL;
    }

    @Override
    public String toString() {
        return "ItemAcervo{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", tipo=" + tipo +
                ", genero='" + genero + '\'' +
                ", status=" + status +
                '}';
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}

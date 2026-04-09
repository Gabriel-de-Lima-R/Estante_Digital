package com.estantedigital.model;

public class ItemAcervo {
    private long id;
    private String titulo;
    private TipoItem tipo;
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

    public ItemAcervo(String titulo) {
        this.titulo = titulo;
        this.status = StatusItem.DISPONIVEL;
    }

    public void setTipo(String tipoEscolhido) {
        tipoEscolhido = tipoEscolhido.toLowerCase();
        switch (tipoEscolhido) {
            case "livro" -> this.tipo = TipoItem.LIVRO;
            case "revista" -> this.tipo = TipoItem.REVISTA;
            case "enciclopedia" -> this.tipo = TipoItem.ENCICLOPEDIA;
            default -> System.out.println("Tipo inválido: " + tipoEscolhido);
        }
    }

    public String getTipo() {
        if (this.tipo != null) {
            return this.tipo.name();
        } else {
            return "DESCONHECIDO";
        }
    }

    @Override
    public String toString() {
        return "ItemAcervo{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", tipo=" + tipo +
                ", status=" + status +
                '}';
    }
}

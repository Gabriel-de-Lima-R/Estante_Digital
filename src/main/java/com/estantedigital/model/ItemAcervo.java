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

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public StatusItem getStatus() {
        return status;
    }

    @Override
    public String toString() {
        if (this instanceof Livro livro) {
            return String.format("📚 ID: %d | %s | Autor: %s | Gênero: %s | Ano: %d | Status: %s",
                    livro.getId(), livro.getTitulo(), livro.getAutor(),
                    livro.getGenero(), livro.getAnoPublicacao(), livro.getStatus()
            );
        } else if (this instanceof Revista revista) {
            return String.format("📰 ID: %d | %s | Editora: %s | Gênero: %s | Ano: %d | Status: %s",
                    revista.getId(), revista.getTitulo(), revista.getEditoraResponsavel(),
                    revista.getGenero(), revista.getAnoPublicacao(), revista.getStatus()
            );
        } else if (this instanceof Enciclopedia enc) {
            return String.format("📖 ID: %d | %s | Editora: %s | Idioma: %s | Volumes: %d | Edição: %d | Status: %s",
                    enc.getId(), enc.getTitulo(), enc.getEditoraResponsavel(),
                    enc.getIdioma(), enc.getVolumes(), enc.getAnoEdicao(), enc.getStatus()
            );
        }

        // Fallback
        return String.format("ID: %d | %s | Status: %s", id, titulo, status);

    }
}

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

    public void setId(long id) {
        this.id = id;
    }

    public void setStatus(String statusStr) {
        if ("DISPONIVEL".equalsIgnoreCase(statusStr)) {
            this.status = StatusItem.DISPONIVEL;
        } else if ("EMPRESTADO".equalsIgnoreCase(statusStr)) {
            this.status = StatusItem.EMPRESTADO;
        }
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

    public String disponivelTexto() {
        return this.status.name().equals("DISPONIVEL") ? "✅ DISPONÍVEL" : "❌ EMPRESTADO";
    }

    @Override
    public String toString() {
        if (this.tipo.name().equals("LIVRO")) {
            Livro livro = (Livro) this;
            return String.format("📚 ID: %d | %s | Autor: %s | Gênero: %s | Ano: %d | %s",
                    livro.getId(), livro.getTitulo(), livro.getAutor(),
                    livro.getGenero(), livro.getAnoPublicacao(), livro.disponivelTexto()
            );
        } else if (this.tipo.name().equals("REVISTA")) {
            Revista revista = (Revista) this;
            return String.format("📰 ID: %d | %s | Editora: %s | Gênero: %s | Ano: %d | %s",
                    revista.getId(), revista.getTitulo(), revista.getEditoraResponsavel(),
                    revista.getGenero(), revista.getAnoPublicacao(), revista.disponivelTexto()
            );
        } else if (this.tipo.name().equals("ENCICLOPEDIA")) {
            Enciclopedia enc = (Enciclopedia) this;
            return String.format("📖 ID: %d | %s | Editora: %s | Idioma: %s | Volumes: %d | Edição: %d | %s",
                    enc.getId(), enc.getTitulo(), enc.getEditoraResponsavel(),
                    enc.getIdioma(), enc.getVolumes(), enc.getAnoEdicao(), enc.disponivelTexto()
            );
        }

        // Fallback
        return String.format("ID: %d | %s | Status: %s", id, titulo, disponivelTexto());

    }
}

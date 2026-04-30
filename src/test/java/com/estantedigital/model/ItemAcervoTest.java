package com.estantedigital.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemAcervoTest {

    // ========== TESTES DOS TEXTOS DE DISPONIVEL ==========

    @Test
    void deveRetornaDisponivelQuandoEstiver() {
        ItemAcervo item = new ItemAcervo("teste");

        assertEquals("✅ DISPONÍVEL", item.disponivelTexto());
        assertEquals("DISPONIVEL", item.getStatus().name());
    }

    @Test
    void deveRetornaEmprestadoQuandoEstiver() {
        ItemAcervo item = new ItemAcervo("teste");
        item.setStatus("EMPRESTADO");

        assertEquals("❌ EMPRESTADO", item.disponivelTexto());
        assertEquals("EMPRESTADO", item.getStatus().name());
    }

    // ========== TESTES DE GETTIPO ==========

    @Test
    void deveRetornarLIVROQuandoTipoForLIVRO() {
        Livro livro = new Livro("Dom Casmurro", "Machado de Assis", "Romance", 1899);
        assertEquals("LIVRO", livro.getTipo());
    }

    @Test
    void deveRetornarREVISTAQuandoTipoForREVISTA() {
        Revista revista = new Revista("Veja", "Editora Abril", "Notícias", 2024);
        assertEquals("REVISTA", revista.getTipo());
    }

    @Test
    void deveRetornarENCICLOPEDIAQuandoTipoForENCICLOPEDIA() {
        Enciclopedia enc = new Enciclopedia("Barsa", "Britannica", "Português", 18, 2010);
        assertEquals("ENCICLOPEDIA", enc.getTipo());
    }

    // ========== TESTES DOS CONSTRUTORES ==========

    @Test
    void deveCriarLivroComTipoLIVRO() {
        Livro livro = new Livro("Dom Casmurro", "Machado de Assis", "Romance", 1899);
        assertEquals("LIVRO", livro.getTipo());
        assertEquals("Dom Casmurro", livro.getTitulo());
        assertEquals("Machado de Assis", livro.getAutor());
        assertEquals("Romance", livro.getGenero());
        assertEquals(1899, livro.getAnoPublicacao());
    }

    @Test
    void deveCriarRevistaComTipoREVISTA() {
        Revista revista = new Revista("Veja", "Editora Abril", "Notícias", 2024);
        assertEquals("REVISTA", revista.getTipo());
        assertEquals("Veja", revista.getTitulo());
        assertEquals("Editora Abril", revista.getEditoraResponsavel());
        assertEquals("Notícias", revista.getGenero());
        assertEquals(2024, revista.getAnoPublicacao());
    }

    @Test
    void deveCriarEnciclopediaComTipoENCICLOPEDIA() {
        Enciclopedia enc = new Enciclopedia("Barsa", "Britannica", "Português", 18, 2010);
        assertEquals("ENCICLOPEDIA", enc.getTipo());
        assertEquals("Barsa", enc.getTitulo());
        assertEquals("Britannica", enc.getEditoraResponsavel());
        assertEquals("Português", enc.getIdioma());
        assertEquals(18, enc.getVolumes());
        assertEquals(2010, enc.getAnoEdicao());
    }
}

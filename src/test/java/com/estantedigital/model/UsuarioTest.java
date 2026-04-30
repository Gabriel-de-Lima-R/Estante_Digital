package com.estantedigital.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    // ========== TESTES DO GET PRIMEIRO NOME ==========

    @Test
    void deveRetornarPrimeiroNomeQuandoNomeCompletoTemEspacos() {
        Usuario usuario = new Usuario("João Silva Souza", "12345678901", "joao@email.com", "senha123");

        String resultado = usuario.getPrimeiroNome();

        assertEquals("João", resultado);
    }

    @Test
    void deveRetornarNomeCompletoQuandoApenasUmNome() {
        Usuario usuario = new Usuario("Ana", "12345678901", "ana@email.com", "senha123");

        String resultado = usuario.getPrimeiroNome();

        assertEquals("Ana", resultado);
    }

    @Test
    void deveIgnorarEspacosExtrasNoNome() {
        Usuario usuario = new Usuario("  Maria  Clara  ", "12345678901", "maria@email.com", "senha123");

        String resultado = usuario.getPrimeiroNome();

        assertEquals("Maria", resultado);
    }

    @Test
    void deveRetornarStringVaziaQuandoNomeForNull() {
        Usuario usuario = new Usuario(null, "12345678901", "teste@email.com", "senha123");

        String resultado = usuario.getPrimeiroNome();

        assertEquals(" ", resultado);
    }

    @Test
    void deveRetornarStringVaziaQuandoNomeForVazio() {
        Usuario usuario = new Usuario("", "12345678901", "teste@email.com", "senha123");

        String resultado = usuario.getPrimeiroNome();

        assertEquals(" ", resultado);
    }

    @Test
    void deveRetornarPrimeiroNomeIgnorandoMaiusculasEMinusculas() {
        Usuario usuario = new Usuario("JOSÉ Carlos", "12345678901", "jose@email.com", "senha123");

        String resultado = usuario.getPrimeiroNome();

        assertEquals("José", resultado);
    }

    // ========== TESTES DO GETTERS E SETTERS ==========

    @Test
    void deveSetarEGetarIdCorretamente() {
        Usuario usuario = new Usuario("Teste", "12345678901", "teste@email.com", "senha123");

        usuario.setId(99);

        assertEquals(99, usuario.getId());
    }

    @Test
    void deveSetarEGetarCpfCorretamente() {
        Usuario usuario = new Usuario("Teste", "12345678901", "teste@email.com", "senha123");

        usuario.setCpf("98765432100");

        assertEquals("98765432100", usuario.getCpf());
    }

    @Test
    void deveSetarEGetarEmailCorretamente() {
        Usuario usuario = new Usuario("Teste", "12345678901", "teste@email.com", "senha123");

        usuario.setEmail("novo@email.com");

        assertEquals("novo@email.com", usuario.getEmail());
    }

    @Test
    void deveSetarEGetarNomeCompletoCorretamente() {
        Usuario usuario = new Usuario("Teste", "12345678901", "teste@email.com", "senha123");

        usuario.setNomeCompleto("João Silva");

        assertEquals("João Silva", usuario.getNomeCompleto());
    }

    // ========== TESTES DO TOSTRING ==========

    @Test
    void toStringDeveMostrarNomeECpf() {
        Usuario usuario = new Usuario("Maria Souza", "12345678901", "maria@email.com", "senha123");

        String resultado = usuario.toString();

        assertTrue(resultado.contains("Maria Souza"));
        assertTrue(resultado.contains("12345678901"));
    }
}
package com.estantedigital.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class EmprestimoTest {

    // ========== TESTES DO CONSTRUTOR ==========

    @Test
    void deveCriarEmprestimoComDadosCorretos() {
        Emprestimo emprestimo = new Emprestimo(1, 2, 3);

        assertEquals(1, emprestimo.getId());
        assertEquals(2, emprestimo.getUsuarioId());
        assertEquals(3, emprestimo.getItemId());
        assertNotNull(emprestimo.getDataRetirada());
        assertNotNull(emprestimo.getDataPrevistaDevolucao());
        assertNull(emprestimo.getDataDevolucaoReal());
        assertNull(emprestimo.getJustificativaAtraso());
    }

    @Test
    void deveCalcularDataPrevistaComoSeteDiasAposRetirada() {
        Emprestimo emprestimo = new Emprestimo(1, 2, 3);

        LocalDate dataRetirada = emprestimo.getDataRetirada().plusDays(7);

        assertEquals(dataRetirada, emprestimo.getDataPrevistaDevolucao());
    }

    // ========== TESTES DO ISATRASADO ==========

    @Test
    void deveRetornarFalseQuandoDevolucaoDentroDoPrazo() {
        Emprestimo emprestimo = new Emprestimo(1, 2, 3);
        // Data prevista é hoje + 7 dias, então não está atrasado
        assertFalse(emprestimo.isAtrasado());
    }

    @Test
    void deveRetornarTrueQuandoDevolucaoAposPrazo() {
        Emprestimo emprestimo = new Emprestimo(1, 2, 3);
        // Força a data prevista para ontem
        emprestimo.setDataPrevistaDevolucao(LocalDate.now().minusDays(1));
        assertTrue(emprestimo.isAtrasado());
    }

    @Test
    void deveRetornarFalseQuandoDataPrevistaEhHoje() {
        Emprestimo emprestimo = new Emprestimo(1, 2, 3);
        emprestimo.setDataPrevistaDevolucao(LocalDate.now());
        assertFalse(emprestimo.isAtrasado());
    }


}

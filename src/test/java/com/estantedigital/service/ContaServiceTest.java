package com.estantedigital.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaServiceTest {
    // ========== TESTES DO VALIDA EMAIL ==========

    @Test
    void deveAceitarEmailGmail() {
        assertTrue(ContaService.emailComDominioCerto("usuario@gmail.com"));
    }

    @Test
    void deveAceitarEmailHotmail() {
        assertTrue(ContaService.emailComDominioCerto("usuario@hotmail.com"));
    }

    @Test
    void deveRejeitarEmailComDominioCorporativo() {
        assertFalse(ContaService.emailComDominioCerto("usuario@empresa.com"));
    }

    @Test
    void deveRejeitarEmailSemArroba() {
        assertFalse(ContaService.emailComDominioCerto("usuarioemail.com"));
    }

    @Test
    void deveRejeitarEmailVazio() {
        assertFalse(ContaService.emailComDominioCerto(""));
    }

    @Test
    void deveRejeitarEmailNull() {
        assertFalse(ContaService.validaEmail(null));
    }

    // ========== TESTES DO VALIDA CPF ==========

    @Test
    void deveAceitarCpfCom11Digitos() {
        assertTrue(ContaService.cpfTem11Digitos("12345678901"));
    }

    @Test
    void deveRejeitarCpfComLetras() {
        assertFalse(ContaService.validaCpf("1234567890A"));
    }

    @Test
    void deveRejeitarCpfComMenosDigitos() {
        assertFalse(ContaService.cpfTem11Digitos("123456789"));
    }

    @Test
    void deveRejeitarCpfComMaisDigitos() {
        assertFalse(ContaService.cpfTem11Digitos("123456789012"));
    }

    @Test
    void deveRejeitarCpfVazio() {
        assertFalse(ContaService.validaCpf(""));
    }

    @Test
    void deveRejeitarCpfNull() {
        assertFalse(ContaService.validaCpf(null));
    }

    // ========== TESTES DO VALIDA NOME ==========

    @Test
    void deveAceitarNomeComTresCaracteres() {
        assertTrue(ContaService.validaNome("Ana"));
    }

    @Test
    void deveAceitarNomeComMaisCaracteres() {
        assertTrue(ContaService.validaNome("João Silva"));
    }

    @Test
    void deveRejeitarNomeComDoisCaracteres() {
        assertFalse(ContaService.validaNome("Jo"));
    }

    @Test
    void deveRejeitarNomeVazio() {
        assertFalse(ContaService.validaNome(""));
    }

    @Test
    void deveRejeitarNomeNull() {
        assertFalse(ContaService.validaNome(null));
    }

    // ========== TESTES DO VALIDA SENHA ==========

    @Test
    void deveAceitarSenhaComLetraENumero() {
        assertTrue(ContaService.validaSenha("abc123"));
    }

    @Test
    void deveAceitarSenhaComMaiusculasENumero() {
        assertTrue(ContaService.validaSenha("ABC123"));
    }

    @Test
    void deveRejeitarSenhaApenasLetras() {
        assertFalse(ContaService.validaSenha("apenasletras"));
    }

    @Test
    void deveRejeitarSenhaApenasNumeros() {
        assertFalse(ContaService.validaSenha("123456"));
    }

    @Test
    void deveRejeitarSenhaCurtaMenosQue4() {
        assertFalse(ContaService.validaSenha("a1"));
    }

    @Test
    void deveAceitarSenhaComExatamente4Caracteres() {
        assertTrue(ContaService.validaSenha("a1b2"));
    }

    @Test
    void deveRejeitarSenhaVazia() {
        assertFalse(ContaService.validaSenha(""));
    }

    @Test
    void deveRejeitarSenhaNull() {
        assertFalse(ContaService.validaSenha(null));
    }
}

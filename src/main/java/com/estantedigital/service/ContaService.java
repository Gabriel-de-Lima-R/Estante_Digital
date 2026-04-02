package com.estantedigital.service;

import com.estantedigital.cli.CentralMenus;
import com.estantedigital.repository.UsuarioRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContaService {
    private static Scanner leitor = new Scanner(System.in);
    private static Map<String, String> dados = new HashMap<>();


    public static Map<String, String> criandoNovaConta() {

        System.out.println(CentralMenus.TITULO_CADASTRO + "\n");
        System.out.println("Preencha corretamente os dados a seguir:\n");

        // coleta do e-mail corretamente!
        String email;
        while (true) {
            System.out.print("Email: ");
            email = leitor.nextLine();

            if (validaEmail(email)) {
                dados.put("email", email);
                break;
            }
        }

        // coleta senha corretamente!
        String senha;
        while (true) {
            System.out.println("Senha: ");
            senha = leitor.nextLine();
            if (validaSenha(senha)) {
                dados.put("senha", senha);
                break;
            }
        }

        // coleta de nome correto!
        String nomeCompleto;
        while (true) {
            System.out.print("Nome completo: ");
            nomeCompleto = leitor.nextLine();
            if (validaNome(nomeCompleto)) {
                dados.put("nomeCompleto", nomeCompleto);
                break;
            }
        }


        // coleta o cpf corretamente!
        String cpf;
        while (true) {
            System.out.print("CPF (apenas números): ");
            cpf = leitor.nextLine();

            if (validaCpf(cpf)) {
                dados.put("cpf", cpf);
                break;
            }
        }

        return dados;
    }

    private static boolean validaSenha(String senha) {
        if (senha == null) { return false; }

        if (senha.length() >= 4 && senha.matches(".*[A-Za-z].*") && senha.matches(".*\\d.*")) {
            return true;
        }

        System.out.println("Senha deve ter pelo menos 4 caracteres, contendo letra e número.");
        return false;
    }

    public static boolean validaEmail(String email) {
        if (email == null) { return false; }

        if (UsuarioRepository.emailExiste(email)) {
            System.out.println("Esse email já existe!! Crie uma conta com novo email!!");
            return false;
        }

        if (email.endsWith("@gmail.com") || email.endsWith("@hotmail.com")) {
            dados.put("email", email);
            return true;
        }

        System.out.println("Email inválido! Use @gmail.com ou @hotmail.com");
        return false;
    }

    public static boolean validaNome(String nome) {
        if (nome == null) return false;

        if (nome.trim().length() >= 3) {
            return true;
        }

        System.out.println("Nome deve ter pelo menos 3 caracteres.");
        return false;
    }

    public static boolean validaCpf(String cpf) {
        if (cpf == null) return false;

        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            System.out.println("CPF inválido! Deve conter exatamente 11 números.");
            return false;
        }

        if (UsuarioRepository.cpfExiste(cpf)) {
            System.out.println("Esse cpf já está sendo usado!!");
            return false;
        }

        return true;
    }

}

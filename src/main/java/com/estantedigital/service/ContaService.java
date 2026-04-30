package com.estantedigital.service;

import com.estantedigital.cli.CentralMenus;
import com.estantedigital.model.Usuario;
import com.estantedigital.repository.UsuarioRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContaService {
    private static Scanner leitor = new Scanner(System.in);
    private static Map<String, String> dados = new HashMap<>();


    public static Map<String, String> criandoNovaConta() {

        System.out.println(CentralMenus.TITULO_CADASTRO);
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
            System.out.print("Senha: ");
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

    public static boolean validaSenha(String senha) {
        if (senha == null) { return false; }

        if (senha.length() >= 4 && senha.matches(".*[A-Za-z].*") && senha.matches(".*\\d.*")) {
            return true;
        }

        System.out.println("Senha deve ter pelo menos 4 caracteres, contendo letra e número.");
        return false;
    }

    public static boolean validaEmail(String email) {
        if (email == null) { return false; }

        if (emailJaExiste(email)) { return false;}

        if (emailComDominioCerto(email)) {
            dados.put("email", email);
            return true;
        }

        System.out.println("Email inválido! Use @gmail.com ou @hotmail.com");
        return false;
    }

    public static boolean emailJaExiste(String email) {
        if (UsuarioRepository.emailExiste(email)) {
            System.out.println("Esse email já existe!! Crie uma conta com novo email!!");
            return true;
        }
        return false;
    }

    public static boolean emailComDominioCerto(String email) {
        String[] dominiosAceitos = {"@gmail.com", "@hotmail.com"};

        for (String dominio : dominiosAceitos) {
            if (email.endsWith(dominio)) {
                return true;
            }
        }
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

        if (!cpfTem11Digitos(cpf)) {
            System.out.println("CPF inválido! Deve conter exatamente 11 números.");
            return false;
        }

        if (UsuarioRepository.cpfExiste(cpf)) {
            System.out.println("Esse cpf já está sendo usado!!");
            return false;
        }

        return true;
    }

    public static boolean cpfTem11Digitos(String cpf) {
        return cpf.length() == 11;
    }

    public static Usuario fazendoLogin(UsuarioRepository repository) {
        System.out.println(CentralMenus.TITULO_LOGIN);
        System.out.println("Preencha corretamente as credenciais:\n");
        System.out.print("Email: ");
        String emailDigitado = leitor.nextLine();
        System.out.print("Senha: ");
        String senhaDigitada = leitor.nextLine();

        Usuario usuario = UsuarioRepository.buscarPorEmail(emailDigitado);

        // verifica se o usuário existe e se a senha está correta
        if (usuario != null && usuario.getSenha().equals(senhaDigitada)) {
            System.out.println("\nLogin realizado com sucesso!");
            return usuario;
        } else {
            System.out.println("Credenciais Inválidas!! Por favor, tente novamente!!");
            return null;
        }

    }

}

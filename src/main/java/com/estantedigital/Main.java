package com.estantedigital;

import com.estantedigital.cli.CentralMenus;
import com.estantedigital.model.Usuario;
import com.estantedigital.service.ContaService;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static Scanner leitor = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(CentralMenus.LOGO_ASCII);
        System.out.println("\n" + CentralMenus.SAUDACAO);
        menuInicial();

    }

    public static void menuInicial() {
        boolean ativo = true;

        while (ativo) {
            System.out.print(CentralMenus.MENU_INICIAL + " ");
            String opcao = leitor.nextLine();
            limparTerminal();
            switch (opcao) {
                case "1", "01" ->
                {
                    acervoDisponivel();
                    aguarde();
                    break;
                }
                case "2", "02" -> {
                    menuAutenticacao();
                    aguarde();
                    break;
                }
                case "3", "03" -> {
                    System.out.println(CentralMenus.LIVRO);
                    ativo = false;
                    break;
                }
                default -> {
                    System.out.println(CentralMenus.OPCAO_INVALIDA);
                }
            }
        }
    }

    private static void menuAutenticacao() {
        boolean ativoAuten = true;
        while (ativoAuten) {
            System.out.println(CentralMenus.MENU_AUTENTICACAO);
            String opcaoAuten = leitor.nextLine();
            limparTerminal();
            switch (opcaoAuten) {
                case "1", "01" ->
                {
                    acervoDisponivel();
                    aguarde();
                    break;
                }
                case "2", "02" -> {
                    menuCriarConta();
                    aguarde();
                    break;
                }
                case "3", "03" -> {
                    System.out.println("Precione ENTER Para Voltar!");
                    leitor.nextLine();
                    ativoAuten = false;
                    break;
                }
                default -> {
                    System.out.println(CentralMenus.OPCAO_INVALIDA);
                }
            }
        }

    }

    private static void menuCriarConta() {
        Map<String, String> dadosUsuario = ContaService.criandoNovaConta();
        Usuario novoUsuario = new Usuario(dadosUsuario.get("nomeCompleto"), dadosUsuario.get("cpf"), dadosUsuario.get("email"), dadosUsuario.get("senha"));

        System.out.println(novoUsuario);


    }

    private static void acervoDisponivel() {
        System.out.println(CentralMenus.AGUARDE_IMPLEMENTACAO);
    }

    public static void limparTerminal() {
        // apenas pula linhas para "fingir" a limpeza
        for (int i = 0; i < 35; i++) System.out.println();
        // por favor, no futuro, limpe da forma correta.
    }

    public static void aguarde() {
        System.out.println("Precione ENTER para Continuar!");
        leitor.nextLine();
        limparTerminal();
    }
}

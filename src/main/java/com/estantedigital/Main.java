package com.estantedigital;

import com.estantedigital.cli.CentralMenus;

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
                    menuLoginECriarConta();
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

    private static void menuLoginECriarConta() {
        System.out.println(CentralMenus.AGUARDE_IMPLEMENTACAO);
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

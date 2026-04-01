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
        System.out.print(CentralMenus.MENU_INICIAL);
        String opcao = leitor.nextLine();
    }
}

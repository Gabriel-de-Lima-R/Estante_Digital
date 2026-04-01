package com.estantedigital.service;

import com.estantedigital.cli.CentralMenus;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContaService {
    private static Scanner leitor = new Scanner(System.in);
    private static Map<String, String> dados = new HashMap<>();


    private static Map<String, String> menuCriarConta() {

        System.out.println(CentralMenus.TITULO_CADASTRO + "\n");
        System.out.println("Preencha corretamente os dados a seguir:\n");

        String email = " ";
        while (validaEmail(email)) {
            System.out.print("Email: ");
            email = leitor.nextLine();
        }

        System.out.print("Senha: ");
        dados.put("senha", leitor.nextLine());

        System.out.print("Nome completo: ");
        dados.put("nomeCompleto", leitor.nextLine());

        System.out.print("CPF (apenas números): ");
        dados.put("cpf", leitor.nextLine());

        return dados;
    }

    public static boolean validaEmail(String email) {
        if (email.endsWith("@gmail.com") || email.endsWith("@hotmail.com")) {
            dados.put("email", email);
            return false;
        }
        System.out.println("Email inválido! Use @gmail.com ou @hotmail.com");
        return true;
    }

}

package com.estantedigital;

import com.estantedigital.cli.CentralMenus;
import com.estantedigital.model.ItemAcervo;
import com.estantedigital.model.Usuario;
import com.estantedigital.repository.AcervoRepository;
import com.estantedigital.repository.UsuarioRepository;
import com.estantedigital.service.ContaService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static Scanner leitor = new Scanner(System.in);
    public static UsuarioRepository usuarioDB = new UsuarioRepository();
    public static AcervoRepository acervoDB = new AcervoRepository();

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
                    // Mensagem informando que precisa logar para pegar emprestado
                    System.out.println("\n" + CentralMenus.ACERVO_DICA_NAO_LOGADO);
                    aguarde();
                    break;
                }
                case "2", "02" -> {
                    menuAutenticacao();
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
            System.out.print(CentralMenus.MENU_AUTENTICACAO + " ");
            String opcaoAuten = leitor.nextLine();
            limparTerminal();
            switch (opcaoAuten) {
                case "1", "01" ->
                {
                    ativoAuten = menuLogin();
                    aguarde();
                    break;
                }
                case "2", "02" -> {
                    menuCriarConta();
                    aguarde();
                    break;
                }
                case "3", "03" -> {
                    ativoAuten = false;
                    break;
                }
                default -> {
                    System.out.println(CentralMenus.OPCAO_INVALIDA);
                }
            }
        }

    }

    private static boolean menuLogin() {
        Usuario usuarioLogado = ContaService.fazendoLogin(usuarioDB);

        if (usuarioLogado != null) {
           menuTelaPrincipal(usuarioLogado);

           return false; // depois de sair da tela principal, o usuário retorna a tela inicial, útil pra logouts
        } else { return true; }
    }

    private static void menuCriarConta() {
        Map<String, String> dadosUsuario = ContaService.criandoNovaConta();

        // cria o usuário
        Usuario novoUsuario = new Usuario(
                dadosUsuario.get("nomeCompleto"),
                dadosUsuario.get("cpf"),
                dadosUsuario.get("email"),
                dadosUsuario.get("senha")
        );

        usuarioDB.adicionar(novoUsuario);

    }

    private static void menuTelaPrincipal(Usuario usuarioAtual) {
        System.out.println("Seja Bem-vindo(a) " + usuarioAtual.getPrimeiroNome() + "!");
        boolean ativoPrinc = true;
        while (ativoPrinc) {
            System.out.print(CentralMenus.MENU_PRINCIPAL + " ");
            String opcaoPrinc = leitor.nextLine();
            switch (opcaoPrinc) {
                case "1", "01" -> {
                    acervoDisponivel();
                    // Mensagem informando que precisa logar para pegar emprestado
                    System.out.println("\n" + CentralMenus.ACERVO_DICA_LOGADO);
                    aguarde();
                    break;
                }
                case "5", "05" -> {
                    ativoPrinc = false;
                    break;
                }
                default -> {
                    System.out.println(CentralMenus.OPCAO_INVALIDA);
                }
            }
        }
    }

    private static void acervoDisponivel() {
        List<ItemAcervo> todosItens = acervoDB.listarTodos();

        if (todosItens.isEmpty()) {
            System.out.println("\n❌ Nenhum item cadastrado no acervo.");
        } else {
            System.out.println(CentralMenus.ACERVO_DISPONIVEL_TITULO + "\n");

            for (ItemAcervo item : todosItens) {
                if (item.getStatus().name().equals("DISPONIVEL")) {
                    System.out.println(item);
                    System.out.println(CentralMenus.SEPARADOR);
                }
            }

            System.out.println("\n📊 Total de itens no acervo: " + todosItens.size());
        }
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

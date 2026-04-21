package com.estantedigital.cli;

public class CentralMenus {

    private CentralMenus() {
        throw new UnsupportedOperationException("Classe utilitária não pode ser instanciada");
    }

    // ==================== ARTES E LOGOS ====================

    public static final String LOGO_ASCII = """
              _____     _              _         ____  _       _ _        _\s
             | ____|___| |_ __ _ _ __ | |_ ___  |  _ \\(_) __ _(_) |_ __ _| |
             |  _| / __| __/ _` | '_ \\| __/ _ \\ | | | | |/ _` | | __/ _` | |
             | |___\\__ \\ || (_| | | | | ||  __/ | |_| | | (_| | | || (_| | |
             |_____|___/\\__\\__,_|_| |_|\\__\\___| |____/|_|\\__, |_|\\__\\__,_|_|
            =============================================|___/==============
            ================================================================\s""";

    public static final String LOGO = """
            =========================================
                         ESTANTE DIGITAL
            =========================================""";

    public static final String LIVRO = """
              ,..........   ..........,
          ,..,'  MUITO   '.' SISTEMA  ',..,
         ,' ,'  OBRIGADO  :  ONLINE DA ', ',
        ,' ,'  POR USAR   :  ESTANTE    ', ',
       ,' ,'  O MODERNO   :  DIGITAL :)  ', ',
      ,' ,'............., : ,.............', ',
     ,'  '............   '.'   ............'  ',
      '''''''''''''''''';''';''''''''''''''''''
                         '''""";

    // ==================== SAUDAÇÕES e ESPERA ====================

    public static final String SAUDACAO = "Bem-vindo(a) à Estante Digital — seu acesso ao acervo completo da Biblioteca Municipal.";

    public static final String PRECIONE_ENTER = "Precione ENTER para Continuar!";

    // ==================== MENU INICIAL ====================

    public static final String MENU_INICIAL = """
            
            ============ OPÇÕES ============
            
            [1] - Ver Acervo Disponível
            [2] - Fazer Login / Criar Conta
            [3] - Fechar Programa
            
            Escolha uma opção: """;

    public static final String OPCAO_INVALIDA = "Opção inválida! Tente novamente.";
    public static final String AGUARDE_IMPLEMENTACAO = "Funcionalidade em desenvolvimento. Em breve estará disponível!"; // deverá ser excluido na entrega final

    // ==================== MENSAGENS DO ACERVO ====================

    public static final String ACERVO_DISPONIVEL_TITULO = "\n=== ACERVO DISPONÍVEL ===\n";
    public static final String SEPARADOR = "----------------------------------------";
    public static final String ACERVO_DICA_NAO_LOGADO = """
    ══════════════════════════════════════════════════════════════════
    💡 Dica: Você precisa estar logado para pegar livros emprestados!
    ══════════════════════════════════════════════════════════════════
    """;

    public static final String ACERVO_DICA_LOGADO = """
    ══════════════════════════════════════════════════════════════════
    💡 Dica: Para pegar um livro, utilize a opção 2 do menu principal!
    ══════════════════════════════════════════════════════════════════
    """;

    // ==================== MENU DE AUTENTICAÇÃO ====================

    public static final String MENU_AUTENTICACAO = """
            
            ========== ACESSO ==========
            
            [1] - Fazer Login
            [2] - Criar Conta
            [3] - Voltar
            
            Escolha uma opção: """;

    public static final String TITULO_LOGIN = "\n=== LOGIN ===\n";
    public static final String TITULO_CADASTRO = "\n=== CRIAR CONTA ===\n";


    // ==================== MENU PRINCIPAL (LOGADO) ====================

    public static final String MENU_PRINCIPAL = """
            
            ========== MENU PRINCIPAL ==========
            
            [1] - Ver acervo disponível
            [2] - Pegar livro emprestado
            [3] - Devolver livro
            [4] - Buscar por livro
            [5] - Sair
            
            Escolha uma opção: """;

    // ==================== MENSAGENS DA BUSCA POR LIVRO (RF03.4) ====================

    public static final String BUSCAR_TITULO = """
    ═══════════════════════════════════════════════════════════
                      🔍 BUSCAR POR LIVRO
    ═══════════════════════════════════════════════════════════
    """;

    public static final String RESULTADO_BUSCA_TITULO = """
    ═══════════════════════════════════════════════════════════
                      📋 RESULTADO DA BUSCA
    ═══════════════════════════════════════════════════════════
    """;

    public static final String NENHUM_ITEM_ENCONTRADO = """
    ═══════════════════════════════════════════════════════════
                      ❌ NENHUM ITEM ENCONTRADO
    ═══════════════════════════════════════════════════════════
    """;

    // ==================== MENSAGENS DO FLUXO DE EMPRÉSTIMO ====================

    public static final String EMPRESTIMO_TITULO = """
    ═══════════════════════════════════════════════════════════
                      📖 PEGAR ACERVO EMPRESTADO
    ═══════════════════════════════════════════════════════════
    """;

    public static final String EMPRESTIMO_USUARIO_BLOQUEADO = "\n❌ Usuário bloqueado! Você não pode pegar livros emprestados.";
    public static final String EMPRESTIMO_LIVRO_INDISPONIVEL = "\n❌ Livro indisponível! Aguarde 7 dias até devolverem!";
    public static final String EMPRESTIMO_JA_POSSUI_LIVRO = "\n❌ Você já possui um livro emprestado. Devolva-o antes de pegar outro.";
    public static final String EMPRESTIMO_CANCELADO = "\n❌ Empréstimo cancelado.";


}

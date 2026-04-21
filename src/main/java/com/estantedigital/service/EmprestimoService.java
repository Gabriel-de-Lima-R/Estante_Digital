package com.estantedigital.service;

import com.estantedigital.cli.CentralMenus;
import com.estantedigital.model.Emprestimo;
import com.estantedigital.model.ItemAcervo;
import com.estantedigital.model.Usuario;
import com.estantedigital.repository.AcervoRepository;
import com.estantedigital.repository.EmprestimoRepository;
import com.estantedigital.repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.Scanner;

public class EmprestimoService {

    private EmprestimoRepository emprestimoRepository;
    private UsuarioRepository usuarioRepository;
    private AcervoRepository acervoRepository;
    private Scanner leitor = new Scanner(System.in);

    public EmprestimoService(EmprestimoRepository emprestimoRepository, UsuarioRepository usuarioRepository, AcervoRepository acervoRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
        this.acervoRepository = acervoRepository;
    }

    public void pegarLivroEmprestado(Usuario usuarioAtual, ItemAcervo itemAtual) {
        // 1. Validar: usuário não bloqueado
        if (usuarioAtual.isBloqueado()) {
            System.out.println(CentralMenus.EMPRESTIMO_USUARIO_BLOQUEADO);
            return;
        }

        // 2. Validar: item disponível
        if (itemAtual.getStatus().name().equals("EMPRESTADO")) {
            System.out.println(CentralMenus.EMPRESTIMO_LIVRO_INDISPONIVEL);
            return;
        }

        // 3. Validar: usuário não tem livro em posse
        if (usuarioAtual.getItemEmPosse() != null) {
            System.out.println(CentralMenus.EMPRESTIMO_JA_POSSUI_LIVRO);
            return;
        }

        // 4. Se tudo ok: criar empréstimo
        emprestimoRepository.adicionar(usuarioAtual, itemAtual);

        // 5. Atualizar status do item para EMPRESTADO
        itemAtual.setStatus("EMPRESTADO");
        acervoRepository.atualizar(itemAtual);

        // 6. Atualizar campo itemEmPosse do usuário
        usuarioAtual.setItemEmPosse(itemAtual);
        usuarioRepository.atualizar(usuarioAtual);

        // 7. Avisos finais
        System.out.println("\n✅ Empréstimo realizado com sucesso!");
        System.out.println("📅 Data de devolução (em 7 dias): " + java.time.LocalDate.now().plusDays(7));

    }

    public void devolverLivro(Usuario usuarioAtual) {
        // 1. verifica se existe algum emprestimo na conta daquele usuário
        Emprestimo itemEmprestado = emprestimoRepository.buscarEmprestimoAtivoPorUsuario(usuarioAtual.getId());
        if (itemEmprestado == null) {
            System.out.println("❌ Você não possui nenhum livro emprestado no momento.\n");
            System.out.println(CentralMenus.ACERVO_DICA_LOGADO);
            return;
        }

        // 2. Buscar o item emprestado
        ItemAcervo itemDevolver = acervoRepository.buscarPorId(itemEmprestado.getItemId());

        // 3. Exibir informações do livro
        System.out.println("📖 LIVRO A SER DEVOLVIDO:");
        System.out.println("   Título: " + itemDevolver.getTitulo());
        System.out.println("   Data de retirada: " + itemEmprestado.getDataRetirada());
        System.out.println("   Data prevista: " + itemEmprestado.getDataPrevistaDevolucao());

        // 4. Verificar atraso
        boolean atrasado = itemEmprestado.isAtrasado();

        // 5. Confirmar devolução
        System.out.print("\n✅ Confirmar devolução? (S/N): ");
        String confirmacao = leitor.nextLine();

        if (!confirmacao.equalsIgnoreCase("S")) {
            System.out.println("\n❌ Devolução cancelada.");
            return;
        }

        // 6. Registrar devolução
        itemEmprestado.setDataDevolucaoReal(LocalDate.now());

        if (atrasado) {
            System.out.println(CentralMenus.DEVOLUCAO_ATRASADA);
            System.out.print("\n📝 Justificativa para o atraso: ");
            String justificativa = leitor.nextLine();
            itemEmprestado.setJustificativaAtraso(justificativa);
            usuarioAtual.setBloqueado(true);
        }

        // 7. Atualizar status do item
        itemDevolver.setStatus("DISPONIVEL");

        // 8. Limpar livro em posse do usuário
        usuarioAtual.setItemEmPosse(null);

        // 9. Salvar todas as alterações
        emprestimoRepository.atualizar(itemEmprestado);
        acervoRepository.atualizar(itemDevolver);
        usuarioRepository.atualizar(usuarioAtual);

        System.out.println(CentralMenus.DEVOLUCAO_SUCESSO);

    }

}

package com.estantedigital.service;

import com.estantedigital.model.ItemAcervo;
import com.estantedigital.model.Usuario;
import com.estantedigital.repository.AcervoRepository;
import com.estantedigital.repository.EmprestimoRepository;
import com.estantedigital.repository.UsuarioRepository;

public class EmprestimoService {

    private EmprestimoRepository emprestimoRepository;
    private UsuarioRepository usuarioRepository;
    private AcervoRepository acervoRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, UsuarioRepository usuarioRepository, AcervoRepository acervoRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
        this.acervoRepository = acervoRepository;
    }

    public void pegarLivroEmprestado(Usuario usuarioAtual, ItemAcervo itemAtual) {
        // 1. Validar: usuário não bloqueado
        if (usuarioAtual.isBloqueado()) {
            System.out.println("Usuário bloqueado!");
            return;
        }

        // 2. Validar: item disponível
        if (itemAtual.getStatus().name().equals("EMPRESTADO")) {
            System.out.println("Livro indisponível!");
            return;
        }

        // 3. Validar: usuário não tem livro em posse
        if (usuarioAtual.getItemEmPosse() != null) {
            System.out.println("Você já possui um livro emprestado. Devolva-o antes de pegar outro.");
        }

        // 4. Se tudo ok: criar empréstimo
        emprestimoRepository.adicionar(usuarioAtual, itemAtual);

        // 5. Atualizar status do item para EMPRESTADO
        itemAtual.setStatus("EMPRESTADO");
        acervoRepository.atualizar(itemAtual);

        // 6. Atualizar campo itemEmPosse do usuário
        usuarioAtual.setItemEmPosse(itemAtual);
        usuarioRepository.atualizar(usuarioAtual);

    }
}

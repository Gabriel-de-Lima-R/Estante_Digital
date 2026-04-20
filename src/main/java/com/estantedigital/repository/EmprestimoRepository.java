package com.estantedigital.repository;

import com.estantedigital.model.Emprestimo;
import com.estantedigital.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {
    private static final String ARQUIVO_EMPRESTIMOS = "src/main/resources/emprestimo.json";
    private Gson gson;
    private List<Emprestimo> listaDeEmprestimos;
    private long proximoId = 1;  // contador independente

    public EmprestimoRepository() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.listaDeEmprestimos = new ArrayList<>();
        carregarDoArquivo();
    }

    private void carregarDoArquivo() {
        File arquivo = new File(ARQUIVO_EMPRESTIMOS);

        if (!arquivo.exists()) {
            System.out.println("Arquivo " + ARQUIVO_EMPRESTIMOS + " não encontrado. Será criado ao salvar.");
            return;
        }

        try (FileReader reader = new FileReader(arquivo)) {
            Type tipoDaLista = new TypeToken<List<Emprestimo>>(){}.getType();
            List<Emprestimo> carregados = gson.fromJson(reader, tipoDaLista);

            if (carregados != null) {
                listaDeEmprestimos = carregados;
                System.out.println("Carregados " + listaDeEmprestimos.size() + " emprestimos(s) do arquivo.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar emprestimos: " + e.getMessage());
        }
    }

    private void salvarNoArquivo() {
        try (FileWriter writer = new FileWriter(ARQUIVO_EMPRESTIMOS)) {
            gson.toJson(listaDeEmprestimos, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }


}

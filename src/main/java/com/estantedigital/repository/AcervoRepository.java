package com.estantedigital.repository;

import com.estantedigital.model.*;
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

public class AcervoRepository {
    private static final String[] ARQUIVOS_ACERVO = {
            "src/main/resources/livros.json",
            "src/main/resources/revistas.json",
            "src/main/resources/enciclopedias.json"
    };
    private Gson gson;
    private static List<ItemAcervo> acervoTotal = new ArrayList<>();
//    private static List<Livro> estanteLivros = new ArrayList<>();
//    private static List<Revista> estanteRevistas = new ArrayList<>();
//    private static List<Enciclopedia> estanteEnciclopedias = new ArrayList<>();

    public AcervoRepository() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.acervoTotal = new ArrayList<>();
        carregarDoArquivo();
    }

    private void carregarDoArquivo() {
        for (String url : ARQUIVOS_ACERVO) {
            File arquivo = new File(url);

            if (!arquivo.exists()) {
                System.out.println("Arquivo " + url + " não encontrado. Será criado ao salvar.");
                continue;
            }

            try (FileReader reader = new FileReader(arquivo)) {
                Type tipoDaLista = new TypeToken<List<ItemAcervo>>(){}.getType();
                List<ItemAcervo> carregados = gson.fromJson(reader, tipoDaLista);

                if (carregados != null && !carregados.isEmpty()) {
                    acervoTotal.addAll(carregados);
                    System.out.println("Carregados " + carregados.size() + " itens de: " + url);
                }
            } catch (IOException e) {
                System.out.println("Erro ao carregar " + url + ": " + e.getMessage());
            }
        }

        System.out.println("Total carregado: " + acervoTotal.size() + " itens no acervo.");
    }

    public void salvarSeparado() {
        List<ItemAcervo> livros = new ArrayList<>();
        List<ItemAcervo> revistas = new ArrayList<>();
        List<ItemAcervo> enciclopedias = new ArrayList<>();

        for (ItemAcervo item : acervoTotal) {
            if (item instanceof Livro) {
                livros.add((Livro) item);
            } else if (item instanceof Revista) {
                revistas.add((Revista) item);
            } else if (item instanceof Enciclopedia) {
                enciclopedias.add((Enciclopedia) item);
            }
        }

        salvarListaEmArquivo(livros, ARQUIVOS_ACERVO[0]);
        salvarListaEmArquivo(revistas, ARQUIVOS_ACERVO[1]);
        salvarListaEmArquivo(enciclopedias, ARQUIVOS_ACERVO[2]);
    }

    private void salvarListaEmArquivo(List<ItemAcervo> itens, String caminho) {
        try (FileWriter writer = new FileWriter(caminho)) {
            gson.toJson(itens, writer);
            System.out.println("Salvos " + itens.size() + " itens em: " + caminho);
        } catch (IOException e) {
            System.err.println("Erro ao salvar " + caminho + ": " + e.getMessage());
        }
    }

    public List<ItemAcervo> listarTodos() {
        return new ArrayList<>(acervoTotal);
    }
}

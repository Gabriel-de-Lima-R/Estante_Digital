package com.estantedigital.repository;

import com.estantedigital.dto.ItemAcervoDTO;
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
    private static List<ItemAcervo> acervoTotal;

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
                Type tipoListaDTO = new TypeToken<List<ItemAcervoDTO>>(){}.getType();
                List<ItemAcervoDTO> dtos = gson.fromJson(reader, tipoListaDTO);

                if (dtos != null && !dtos.isEmpty()) {
                    // Converte cada DTO para o objeto correto (Livro, Revista, Enciclopedia)
                    for (ItemAcervoDTO dto : dtos) {
                        ItemAcervo item = converterDTOparaItem(dto);
                        if (item != null) {
                            acervoTotal.add(item);
                        }
                    }
                    System.out.println("Carregados " + dtos.size() + " itens de: " + url);
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
        List<ItemAcervo> lista = new ArrayList<>(acervoTotal);

        // Bubble Sort por id
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - i - 1; j++) {
                if (lista.get(j).getId() > lista.get(j + 1).getId()) {
                    ItemAcervo temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }

        return lista;
    }

    private ItemAcervo converterDTOparaItem(ItemAcervoDTO dto) {
        String tipo = dto.getTipo();

        switch (tipo) {
            case "LIVRO":
                Livro livro = new Livro(
                        dto.getTitulo(),
                        dto.getAutor(),
                        dto.getGenero(),
                        dto.getAno_publicacao()
                );
                livro.setId(dto.getId());
                livro.setStatus(dto.getStatus());
                return livro;

            case "REVISTA":
                Revista revista = new Revista(
                        dto.getTitulo(),
                        dto.getEditora_responsavel(),
                        dto.getGenero(),
                        dto.getAno_publicacao()
                );
                revista.setId(dto.getId());
                revista.setStatus(dto.getStatus());
                return revista;

            case "ENCICLOPEDIA":
                Enciclopedia enciclopedia = new Enciclopedia(
                        dto.getTitulo(),
                        dto.getEditora_responsavel(),
                        dto.getIdioma(),
                        dto.getVolumes(),
                        dto.getAno_edicao()
                );
                enciclopedia.setId(dto.getId());
                enciclopedia.setStatus(dto.getStatus());
                return enciclopedia;

            default:
                System.err.println("Tipo desconhecido: " + tipo);
                return null;
        }
    }

    public void atualizar(ItemAcervo itemAtualizado) {
        for (int i = 0; i < acervoTotal.size(); i++) {
            if (acervoTotal.get(i).getId() == itemAtualizado.getId()) {
                acervoTotal.set(i, itemAtualizado);
                salvarSeparado();
                System.out.println("Item atualizado com sucesso.");
                return;
            }
        }
        System.out.println("Item com ID " + itemAtualizado.getId() + " não encontrado.");
    }

    public ItemAcervo buscarPorTitulo(String titulo) {
        for (ItemAcervo item : acervoTotal) {
            if (item.getTitulo().equalsIgnoreCase(titulo)) {
                return item;
            }
        }
        return null;
    }
}

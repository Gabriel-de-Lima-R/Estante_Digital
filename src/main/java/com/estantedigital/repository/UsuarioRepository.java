package com.estantedigital.repository;

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

public class UsuarioRepository {
    private static final String ARQUIVO_USUARIOS = "src/main/resources/usuarios.json";
    private Gson gson;
    private static List<Usuario> listaDeUsuarios;
    private long proximoId = 1;  // contador independente

    public UsuarioRepository() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.listaDeUsuarios = new ArrayList<>();
        carregarDoArquivo();
    }

    private void carregarDoArquivo() {
        File arquivo = new File(ARQUIVO_USUARIOS);

        if (!arquivo.exists()) {
            System.out.println("Arquivo " + ARQUIVO_USUARIOS + " não encontrado. Será criado ao salvar.");
            return;
        }

        try (FileReader reader = new FileReader(arquivo)) {
            Type tipoDaLista = new TypeToken<List<Usuario>>(){}.getType();
            List<Usuario> carregados = gson.fromJson(reader, tipoDaLista);

            if (carregados != null) {
                listaDeUsuarios = carregados;
                System.out.println("Carregados " + listaDeUsuarios.size() + " usuário(s) do arquivo.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
        }
    }

    private void salvarNoArquivo() {
        try (FileWriter writer = new FileWriter(ARQUIVO_USUARIOS)) {
            gson.toJson(listaDeUsuarios, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    private long encontrarProximoId() {
        if (listaDeUsuarios.isEmpty()) {
            return 1;
        }

        long maiorId = 0;
        for (Usuario usuario : listaDeUsuarios) {
            if (usuario.getId() > maiorId) {
                maiorId = usuario.getId();
            }
        }
        return maiorId + 1;
    }

    public void adicionar(Usuario usuario) {
        long novoId = encontrarProximoId();
        usuario.setId(novoId);
        listaDeUsuarios.add(usuario);
        salvarNoArquivo();
        System.out.println("Usuário " + usuario.getNomeCompleto() +
                " criado com sucesso " + "(ID #" + novoId + ")");
    }


    public void salvar(Usuario usuario) {
        usuario.setId(proximoId);
        proximoId++;
        listaDeUsuarios.add(usuario);
    }

    public long getProximoId() {
        return proximoId;
    }

    public List<Usuario> listarTodos() {
        return listaDeUsuarios;
    }

    public static Usuario buscarPorEmail(String email) {
        if (email == null) {
            return null;
        }

        for (Usuario usuario : listaDeUsuarios) {
            if (usuario.getEmail() != null && usuario.getEmail().equalsIgnoreCase(email)) {
                return usuario;
            }
        }
        return null;
    }

    public static Usuario buscarPorCpf(String cpf) {
        if (cpf == null) {
            return null;
        }

        for (Usuario usuario : listaDeUsuarios) {
            if (usuario.getCpf() != null && usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        return null;
    }

    public static boolean emailExiste(String email) {
        return buscarPorEmail(email) != null;
    }

    public static boolean cpfExiste(String cpf) {
        return buscarPorCpf(cpf) != null;
    }

    public void atualizar(Usuario usuarioAtualizado) {
        for (int i = 0; i < listaDeUsuarios.size(); i++) {
            if (listaDeUsuarios.get(i).getId() == usuarioAtualizado.getId()) {
                listaDeUsuarios.set(i, usuarioAtualizado);
                salvarNoArquivo();
                System.out.println("Usuário atualizado com sucesso.");
                return;
            }
        }
        System.out.println("Usuário com ID " + usuarioAtualizado.getId() + " não encontrado.");
    }

}

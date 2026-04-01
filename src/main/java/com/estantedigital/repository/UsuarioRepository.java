package com.estantedigital.repository;

import com.estantedigital.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private List<Usuario> listaDeUsuarios = new ArrayList<>();
    private long proximoId = 1;  // contador independente

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

}

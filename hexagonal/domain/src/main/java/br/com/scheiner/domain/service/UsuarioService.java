package br.com.scheiner.domain.service;

import java.util.UUID;

import br.com.scheiner.domain.model.Usuario;

public interface UsuarioService {

    Usuario criarUsuario(Usuario usuario);

    Usuario obterUsuarioPorId(UUID id);
}


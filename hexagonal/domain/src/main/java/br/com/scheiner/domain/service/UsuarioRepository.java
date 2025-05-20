package br.com.scheiner.domain.service;

import java.util.UUID;

import br.com.scheiner.domain.model.Usuario;

public interface UsuarioRepository {

	public Usuario salvar(Usuario usuario);
	public Usuario obterUsuarioPorId(UUID id);
}

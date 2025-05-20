package br.com.scheiner.domain.service;

import java.util.Optional;
import java.util.UUID;

import br.com.scheiner.domain.model.Usuario;

public interface UsuarioRepository {

	public Usuario salvar(Usuario usuario);
	public  Optional<Usuario> obterUsuarioPorId(UUID id);
}

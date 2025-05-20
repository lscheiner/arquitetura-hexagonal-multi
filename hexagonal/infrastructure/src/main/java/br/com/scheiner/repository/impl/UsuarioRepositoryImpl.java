package br.com.scheiner.repository.impl;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.com.scheiner.domain.model.Usuario;
import br.com.scheiner.domain.service.UsuarioRepository;
import br.com.scheiner.repository.UsuarioJpaRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UsuarioRepository{

	private final UsuarioJpaRepository usuarioJpaRepository;
	
	@Override
	public Usuario salvar(Usuario usuario) {
		return null;
	}

	@Override
	public Usuario obterUsuarioPorId(UUID id) {
		return null;
	}

}

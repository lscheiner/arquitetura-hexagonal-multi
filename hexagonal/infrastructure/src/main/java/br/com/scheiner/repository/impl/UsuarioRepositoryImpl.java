package br.com.scheiner.repository.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.com.scheiner.domain.model.Usuario;
import br.com.scheiner.domain.service.UsuarioRepository;
import br.com.scheiner.repository.UsuarioJpaRepository;
import br.com.scheiner.repository.mapper.UsuarioEntityMapper;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UsuarioRepository{

	private final UsuarioJpaRepository usuarioJpaRepository;
	
	@Override
	public Usuario salvar(Usuario usuario) {
		
		var usuarioEntity = UsuarioEntityMapper.INSTANCE.toEntity(usuario);
		return UsuarioEntityMapper.INSTANCE.toDomain(usuarioJpaRepository.save(usuarioEntity));
	}

	@Override
	public Optional<Usuario> obterUsuarioPorId(UUID id) {
		return usuarioJpaRepository.findById(id).map(UsuarioEntityMapper.INSTANCE::toDomain);
	}

}
